package org.firstinspires.ftc.teamcode.learnJavaForFTC;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/*
 ------ IMPORTANT ------
 If you want the driver station to recognize the gamepad you have to do the following step:
    Gamepad 1: Start + A Simultaneously
    Gamepad 2: Start + B Simultaneously
Once it has been recognized the gamepad icon in the upper right corder of the driver station
will be illuminated
 */
@TeleOp
public class GamepadOpMode extends OpMode {
    @Override
    public void init() {

    }

    @Override
    public void loop() {
        telemetry.addData("Left stick x", gamepad1.left_stick_x);
        telemetry.addData("Left stick y", gamepad1.left_stick_y);
        telemetry.addData("A button", gamepad1.a);
    }
}
