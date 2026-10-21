package org.firstinspires.ftc.teamcode.practice;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.DriveConfig;
import org.firstinspires.ftc.teamcode.mechanisms.ShootingConfig;

@TeleOp(name = "TeleOp Run Code")
public class TeleOpCode extends OpMode {

    DriveConfig drive = new DriveConfig();
    ShootingConfig shooting = new ShootingConfig();
    boolean gamepad1RightTrigger = false;
    boolean gamepad1LeftTrigger = false;
    boolean gamepad1DPadUp = false;
    boolean gamepad1DPadDown = false;
    boolean gamepad2Square = false;
    double forward = gamepad2.right_stick_y;
    double strafe = gamepad2.right_stick_x;
    double rotate = gamepad2.left_stick_x;

    @Override
    public void init() {
        drive.init(hardwareMap);
        telemetry.addLine("Drive Initialized");
        shooting.init(hardwareMap);
        telemetry.addLine("Shooting Initialized");
    }

    @Override
    public void loop() {
        telemetry.addData("Shooting State", shooting.shootingState);

        drive.driveFieldRelative(forward, strafe, rotate);

        if (gamepad1.right_trigger_pressed && !gamepad1RightTrigger) {
            if (shooting.flywheelRunning) {
                shooting.stopShootingSteps();
            }

            if (!shooting.flywheelRunning) {
                shooting.startFlywheel();
            }
        }

        if (gamepad1.right_trigger_pressed && gamepad1.right_bumper && !gamepad1RightTrigger) {
            if (shooting.flywheelRunning) {
                shooting.stopShootingSteps();
            }

            if (!shooting.flywheelRunning) {
                shooting.flywheelReverse();
            }
        }

        if (gamepad1.left_trigger_pressed && !gamepad1LeftTrigger) {
            if (shooting.intakeRunning) {
                shooting.stopShootingSteps();
            }

            if (!shooting.intakeRunning) {
                shooting.startIntake();
            }
        }

        if (gamepad1.left_trigger_pressed && gamepad1.left_bumper && !gamepad1LeftTrigger) {
            if (shooting.intakeRunning) {
                shooting.stopShootingSteps();
            }

            if (!shooting.intakeRunning) {
                shooting.intakeReverse();
            }
        }

        if (gamepad1.dpad_up && !gamepad1DPadUp) {
            if (shooting.feederRunning){
                shooting.stopShootingSteps();
            }

            if (!shooting.feederRunning) {
                shooting.launchPollen();
            }
        }

        if (gamepad1.dpad_down && !gamepad1DPadDown) {
            if (shooting.feederRunning){
                shooting.stopShootingSteps();
            }

            if (!shooting.feederRunning) {
                shooting.feederReverse();
            }
        }

        if (gamepad2.square && !gamepad2Square) {
            if (shooting.pollenPusherUp) {
                shooting.pollenPusherDown();
            }

            if (!shooting.pollenPusherUp) {
                shooting.pollenPusherUp();
            }
        }

        gamepad1RightTrigger = gamepad1.right_trigger_pressed;
        gamepad1LeftTrigger = gamepad1.left_trigger_pressed;
        gamepad1DPadUp = gamepad1.dpad_up;
        gamepad1DPadDown = gamepad1.dpad_down;
        gamepad2Square = gamepad2.square;
    }
}
