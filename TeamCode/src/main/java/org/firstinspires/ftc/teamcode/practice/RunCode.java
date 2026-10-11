package org.firstinspires.ftc.teamcode.practice;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.mechanisms.FunctionConfig;
import org.firstinspires.ftc.teamcode.mechanisms.HardwareConfig;

@TeleOp
public class RunCode extends OpMode {

    HardwareConfig config = new HardwareConfig();
    FunctionConfig function = new FunctionConfig();

    double forward;
    double strafe;
    double rotate;
    double feederPower;
    boolean isPressed = false;

    @Override
    public void init() {
        config.init(hardwareMap);
        feederPower = 0.3;
        telemetry.addLine("Hardware Initialized");
    }

    @Override
    public void loop() {
        forward = gamepad1.right_stick_y;
        strafe = gamepad1.right_stick_x;
        rotate = gamepad1.left_stick_x;

        function.driveFieldRelative(forward, strafe, rotate);

        telemetry.addData("Gamepad 1 Right Stick Y - Forward", gamepad1.right_stick_y);
        telemetry.addData("Gamepad 1 Left Stick X - Rotate", gamepad1.left_stick_x);
        telemetry.addData("Gamepad 1 Right Stick X - Strafe", gamepad1.right_stick_x);
        telemetry.addData("Gamepad 2 Left Trigger - Intake", gamepad2.left_trigger);
        telemetry.addData("Gamepad 2 Right Trigger - Shooter", gamepad2.right_trigger);
        telemetry.addData("Gamepad 2 D-Pad Up - Feeder to Shooter", gamepad2.dpad_up);
        telemetry.addData("Gamepad 2 D-Pad Down - Feeder to Intake", gamepad2.dpad_down);
        telemetry.addData("Gamepad 1 Triangle Pollen Pusher Up", gamepad1.triangle);
        telemetry.addData("Gamepad 1 Triangle Pollen Pusher Down", gamepad1.square);

        if (gamepad2.left_trigger_pressed && !gamepad2.left_bumper) {
            config.intakeMotor.setPower(gamepad2.left_trigger);
        }
        else {
            config.intakeMotor.setPower(0);
        }

        if (gamepad2.left_trigger_pressed && gamepad2.left_bumper) {
            config.intakeMotor.setPower(-gamepad2.left_trigger);
        }
        else {
            config.intakeMotor.setPower(0);
        }

        if (gamepad2.right_trigger_pressed && !gamepad2.right_bumper) {
            config.flywheelMotor.setPower(gamepad2.right_trigger);
        }
        else {
            config.flywheelMotor.setPower(0);
        }

        if (gamepad2.right_trigger_pressed && gamepad2.right_bumper) {
            config.flywheelMotor.setPower(gamepad2.right_trigger);
        }
        else {
            config.flywheelMotor.setPower(0);
        }

        if (gamepad2.dpad_up){
            config.feederServo.setPower(feederPower);
        }
        else {
            config.feederServo.setPower(0);
        }

        if (gamepad2.dpad_down){
            config.feederServo.setPower(-feederPower);
        }
        else{
            config.feederServo.setPower(0);
        }

        if (gamepad1.triangle && !isPressed) {
            isPressed = true;
            config.pollenPusherServo.setPosition(0);
        }
        else {
            isPressed = false;
        }
        if (gamepad1.square && !isPressed){
            isPressed = true;
            config.pollenPusherServo.setPosition(1);
        }
        else {
            isPressed = false;
        }
    }
}
