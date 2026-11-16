package org.firstinspires.ftc.teamcode.practice;

import static com.pedropathing.api.Paths.line;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.ivy.groups.Groups;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.mechanisms.DriveConfig;
import org.firstinspires.ftc.teamcode.mechanisms.ShootingConfig;
import org.firstinspires.ftc.teamcode.pedro.Constants;

public class PedroPractice extends OpMode {

    DriveConfig drive = new DriveConfig();
    ShootingConfig shooting = new ShootingConfig();
    private Follower follower;
    private final PoseFactory poseFactory = PoseFactory.degrees();

    // Field Coordinate Poses: (X_Inches, Y_Inches, Heading_Degrees)
    private final Pose startPose   = poseFactory.of(82.21, 133.72, 270);
    private final Pose shootPose   = poseFactory.of(83.06, 101.11, 270);
    private final Pose neutralPose = poseFactory.of(118.39, 107.62, 270);
    private final Pose parkPose = poseFactory.of(131.63, 44.48, 270);
    private Path startToShoot() {
        return line(startPose, shootPose).linear(startPose, shootPose);
    }

    private Path shootToNeutral() {
        return line(shootPose, neutralPose).linear(shootPose, neutralPose);
    }

    private Path neutralToPark() {
        return line(neutralPose, parkPose).linear(neutralPose, parkPose);
    }

    Command runAuto() {
        return Groups.sequential(
                follow(follower,startToShoot()),
                follow(follower,shootToNeutral()),
                follow(follower,neutralToPark())
        );
    }

    @Override
    public void init() {
        drive.init(hardwareMap);
        shooting.init(hardwareMap);
        Scheduler.reset();
        follower.setPose(startPose);
        follower.update();
        follower = Constants.create(hardwareMap);
    }

    @Override
    public void start() {
        Scheduler.schedule(runAuto());
    }

    @Override
    public void loop() {

        follower.update();

        Scheduler.execute();

        telemetry.addData("X Position", follower.pose().x());
        telemetry.addData("Y Position", follower.pose().y());
        telemetry.addData("Heading (Deg)", Math.toDegrees(follower.pose().heading()));
    }
}
