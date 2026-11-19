package org.firstinspires.ftc.teamcode.practice;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.DriveConfig;
import org.firstinspires.ftc.teamcode.mechanisms.ShootingConfig;

@TeleOp(name = "TeleOp Changes")
public class TeleOpChanges extends OpMode {

    DriveConfig drive = new DriveConfig();
    ShootingConfig shooting = new ShootingConfig();
    double forward;
    double strafe;
    double rotate;

    @Override
    public void init() {
        drive.init(hardwareMap);
        telemetry.addLine("Drive Initialized");
        shooting.init(hardwareMap);
        telemetry.addLine("Shooting Initialized");
    }

    @Override
    public void loop() {

        drive.resetYaw();

        telemetry.addData("Shooting State", shooting.shootingState);
        telemetry.addData("Flywheel Running", shooting.flywheelRunning);
        telemetry.addData("Feeder Running", shooting.feederRunning);
        telemetry.addData("Intake Running", shooting.intakeRunning);

        //Adding values to drive code
        forward = gamepad2.left_stick_y;
        strafe = gamepad2.left_stick_x;
        rotate = gamepad2.right_stick_x;

        //Driving
        drive.driveFieldRelative(forward, strafe, rotate);


        //Flywheel
        if (gamepad1.rightTriggerWasReleased() && !gamepad1.right_bumper) {
            if (shooting.flywheelRunning) {
                shooting.stopFlywheel();
            } else {
                shooting.startFlywheel();
            }
        }
        else if (gamepad1.rightTriggerWasReleased() && gamepad1.right_bumper) {
            if (shooting.flywheelRunning) {
                shooting.stopFlywheel();
            } else {
                shooting.flywheelReverse();
            }
        }

        if (gamepad1.ps && gamepad2.ps) {
            shooting.stopShootingSteps();
        }

        //Intake
        if (gamepad1.leftTriggerWasReleased() && !gamepad1.left_bumper) {
            if (shooting.intakeRunning) {
                shooting.stopIntake();
            } else {
                shooting.startIntake();
            }
        }
        else if (gamepad1.leftTriggerWasReleased() && gamepad1.left_bumper) {
            if (shooting.intakeRunning) {
                shooting.stopIntake();
            } else {
                shooting.intakeReverse();
            }
        }

        //Feeder Counterclockwise?
        if (gamepad1.dpadUpWasReleased()) {
            if (shooting.feederRunning){
                shooting.stopFeeder();
            } else {
                shooting.startFeeder();
            }
        }
        else if (gamepad1.dpadDownWasReleased()) {
            if (shooting.feederRunning){
                shooting.stopFeeder();
            } else {
                shooting.feederReverse();
            }
        }

        //Pollen Pusher
        if (gamepad2.squareWasReleased()) {
            if (shooting.pollenPusherUp) {
                shooting.pollenPusherDown();
            } else {
                shooting.pollenPusherUp();
            }
        }


    }
}
