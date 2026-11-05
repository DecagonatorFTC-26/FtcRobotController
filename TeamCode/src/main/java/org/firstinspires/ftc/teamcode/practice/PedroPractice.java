package org.firstinspires.ftc.teamcode.practice;

// STATIC IMPORTS: These allow us to call methods like line(), schedule(), and follow()
// directly without typing the full class name (e.g., Paths.line()) every single time.
import static com.pedropathing.api.Paths.*;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous(name = "Pedro Practice v3 (Annotated)", group = "Practice")
public class PedroPractice extends OpMode {

    // The main engine that handles motor power calculations and tracking paths.
    private Follower follower;

    // POSE FACTORY: Pedro Pathing 3 introduces this to solve unit confusion.
    // By initializing with .degrees(), we tell the system to interpret our 3rd value
    // as standard degrees (0-360) instead of forcing us to type Math.toRadians().
    private final PoseFactory p = PoseFactory.degrees();

    // Field Coordinate Poses: (X_Inches, Y_Inches, Heading_Degrees)
    // Note how much cleaner this is without Math.toRadians() wrapped everywhere!
    private final Pose startPose   = p.of(82.21, 133.72, 270);
    private final Pose shootPose   = p.of(83.06, 101.11, 270);
    private final Pose neutralPose = p.of(118.39, 107.62, 270);
    private final Pose parkPose    = p.of(131.63, 44.48, 270);

    /* ========================================================================
     * TRAJECTORY GENERATION METHODS
     * Instead of bloating our init() block, we group paths into isolated methods.
     * ======================================================================== */

    private Path startToShoot() {
        // Paths.line() draws a straight line vector between two poses.
        // .linear() forces the robot to smoothly rotate its face from the start
        // heading to the end heading while it drives in a straight line.
        return line(startPose, shootPose).linear(startPose, shootPose);
    }

    private Path shootToNeutral() {
        return line(shootPose, neutralPose).linear(shootPose, neutralPose);
    }

    private Path neutralToPark() {
        return line(neutralPose, parkPose).linear(neutralPose, parkPose);
    }

    /* ========================================================================
     * THE MASTER AUTONOMOUS ROUTINE
     * This orchestrates our entire 30-second timeline using Command structures.
     * ======================================================================== */
    private Command autoRoutine() {
        // sequential() guarantees that step B will absolutely not execute
        // until step A has fully completed.
        return sequential(
                // step 1: Drive from start to the shooting position
                // follow() translates our Path object into a live command the Ivy scheduler understands.
                follow(follower, startToShoot()),

                // [OPTIONAL AREA] This is exactly where you would inject weapon/mechanism actions.
                // Example: shooter.shootPreloadsCommand(),

                // step 2: Drive from shooting position to the neutral position
                follow(follower, shootToNeutral()),

                // [OPTIONAL AREA] Example: intake.grabSampleCommand(),

                // step 3: Drive from neutral position to final parking spot
                follow(follower, neutralToPark())

                // Note: At the end of a follow() command chain, Pedro Pathing 3 automatically
                // drops into an active "point hold"—meaning motors will fight to stay on parkPose.
        );
    }

    /* ========================================================================
     * OPMODE EXECUTION LIFECYCLE
     * ======================================================================== */

    @Override
    public void init() {
        // CRITICAL STEP: Clears out any old remaining commands left over in the
        // Ivy Runner queue from your previous autonomous run.
        Scheduler.reset();

        // Instantiate your Follower using your team's custom Constants file/class.
        follower = Constants.create(hardwareMap);

        if (follower != null) {
            // Tells the robot's localizer exactly where it is sitting on the field tiles.
            follower.setPose(startPose);

            // Force an initial update calculation before the match countdown begins.
            follower.update();
        }
    }

    @Override
    public void start() {
        // schedule() hands our custom sequential autonomous timeline to Ivy,
        // which places it at the very front of the active operating queue.
        if (follower != null) {
            schedule(autoRoutine());
        }
    }

    @Override
    public void loop() {
        if (follower != null) {
            // MANDATORY: Tells the Follower engine to fetch encoder values, compute
            // current coordinates, and apply power adjustments to the drivetrain motors.
            follower.update();

            // MANDATORY: Tells the Ivy command engine to process the current command step.
            // It checks if the current path is finished. If it is, it advances to the next step.
            Scheduler.execute();

            // Diagnostic Telemetry to monitor progress live on the Driver Station phone.
            telemetry.addData("X Position", follower.pose().x());
            telemetry.addData("Y Position", follower.pose().y());
            telemetry.addData("Heading (Deg)", Math.toDegrees(follower.pose().heading()));
        }
    }
}
