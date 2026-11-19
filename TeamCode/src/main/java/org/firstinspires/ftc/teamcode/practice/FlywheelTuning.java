package org.firstinspires.ftc.teamcode.practice;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.mechanisms.ShootingConfig;

@TeleOp(name = "Tuning for Flywheel")
public class FlywheelTuning extends OpMode {

    ShootingConfig shooting = new ShootingConfig();

    boolean gamepad1LeftTrigger = false;

    @Override
    public void init() {
        //Initialization
        shooting.init(hardwareMap);
        telemetry.speak("Shooting Initialization Complete");
    }

    @Override
    public void loop() {

        //Raises and Lowers Flywheel Velocity High and Flywheel Velocity Low
        if (gamepad2.dpadUpWasPressed()) {
            shooting.flywheelVelocityHigh += shooting.stepSizesVelocity[shooting.stepIndexVelocity];
        }

        if (gamepad2.dpadDownWasPressed()) {
            shooting.flywheelVelocityHigh -= shooting.stepSizesVelocity[shooting.stepIndexVelocity];
        }

        if (gamepad2.dpadRightWasPressed()) {
            shooting.flywheelVelocityLow += shooting.stepSizesVelocity[shooting.stepIndexVelocity];
        }

        if (gamepad2.dpadLeftWasPressed()) {
            shooting.flywheelVelocityLow -= shooting.stepSizesVelocity[shooting.stepIndexVelocity];
        }

        //Changes the rate of change for velocity
        if (gamepad2.bWasPressed()) {
            shooting.stepIndexVelocity = (shooting.stepIndexVelocity + 1) % shooting.stepSizesVelocity.length;
        }

        //Makes the velocity lower (Mostly for Testing P and F values from PIDF on flywheel)
        if (gamepad1.yWasPressed()) {
            if (shooting.curTargetVelocityFlywheel == shooting.flywheelVelocityHigh) {
                shooting.curTargetVelocityFlywheel = shooting.flywheelVelocityLow;
            } else {
                shooting.curTargetVelocityFlywheel = shooting.flywheelVelocityHigh;
            }
        }

        //Changes the rate of change for P and F values
        if (gamepad1.bWasPressed()) {
            shooting.stepIndexValues = (shooting.stepIndexValues + 1) % shooting.stepSizesValues.length;
        }

        //Lowers and Raises the P and F values
        if (gamepad1.dpadLeftWasPressed()) {
            shooting.flywheelF -= shooting.stepSizesValues[shooting.stepIndexValues];
        }

        if (gamepad1.dpadRightWasPressed()) {
            shooting.flywheelF += shooting.stepSizesValues[shooting.stepIndexValues];
        }

        if (gamepad1.dpadUpWasPressed()) {
            shooting.flywheelP += shooting.stepSizesValues[shooting.stepIndexValues];
        }

        if (gamepad1.dpadDownWasPressed()) {
            shooting.flywheelP -= shooting.stepSizesValues[shooting.stepIndexValues];
        }

        //Intake Reverse, Start, and Stop
        if (gamepad1.left_trigger_pressed && !gamepad1.left_bumper && !gamepad1LeftTrigger) {
            if (shooting.intakeRunning) {
                shooting.stopIntake();
            } else {
                shooting.startIntake();
            }
        }
        else if (gamepad1.left_trigger_pressed && gamepad1.left_bumper && !gamepad1LeftTrigger) {
            if (shooting.intakeRunning) {
                shooting.stopIntake();
            } else {
                shooting.intakeReverse();
            }
        }

        gamepad1LeftTrigger = gamepad1.left_trigger_pressed;

        //Start and Stops the Feeder
        if (gamepad1.rightTriggerWasReleased()) {
            if (shooting.feederRunning) {
                shooting.stopFeeder();
            } else {
                shooting.startFeeder();
            }
        }
        //Reverses and Stops the Feeder
        if (gamepad1.rightBumperWasReleased()) {
            if (shooting.feederRunning) {
                shooting.stopFeeder();
            } else {
                shooting.feederReverse();
            }
        }

        //Sets PIDF Constants
        PIDFCoefficients pidfCoefficientsFlywheel = new PIDFCoefficients(shooting.flywheelP, 0, 0, shooting.flywheelF);
        shooting.flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficientsFlywheel);

        //Runs Flywheel
        if (gamepad1.psWasPressed()){
            if (shooting.flywheelRunning) {
                shooting.stopFlywheel();
            } else {
                shooting.flywheelMotor.setVelocity(shooting.curTargetVelocityFlywheel);
                shooting.flywheelRunning = true;
            }
        }

        double curVelocity = shooting.flywheelMotor.getVelocity();
        double error = shooting.curTargetVelocityFlywheel - curVelocity;

        telemetry.addData("Target Velocity", shooting.curTargetVelocityFlywheel);
        telemetry.addData("Current Velocity", "%.2f", curVelocity);
        telemetry.addData("Error", "%.2f", error);
        telemetry.addLine("---------------Gamepad 1----------------");
        telemetry.addLine("Y is for dropping the velocity of flywheel");
        telemetry.addData("Tuning P", "%.4f (D-Pad U/D)", shooting.flywheelP);
        telemetry.addData("Tuning F", "%.4f (D-Pad R/L)", shooting.flywheelF);
        telemetry.addData("Step Size", "%.4f (B Button)", shooting.stepSizesValues[shooting.stepIndexValues]);
        telemetry.addLine("---------------Gamepad 2----------------");
        telemetry.addData("Flywheel Velocity High", "%.4f (D-Pad U/D)",shooting.flywheelVelocityHigh);
        telemetry.addData("Flywheel Velocity Low", "%.4f (D-Pad R/L)", shooting.flywheelVelocityLow);
        telemetry.addData("Step Size", "%.4f (B Button)",shooting.stepSizesVelocity[shooting.stepIndexVelocity]);
    }
}
