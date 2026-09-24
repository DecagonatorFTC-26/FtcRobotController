package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.DCMotorTestRun;

@TeleOp
public class DCMMotorTrial extends OpMode {
    private DCMotorTestRun motorTestRun;

    @Override
    public void init() {
        motorTestRun = new DCMotorTestRun();
        motorTestRun.init(hardwareMap);
    }

    @Override
    public void loop() {
        motorTestRun.setMotors(1, 1, 1, 1);
    }

    // Connect gamepad button to run motor backward
    public void gamepad1ButtonB() {
        motorTestRun.setMotors(-1, -1, -1, -1);
    }
}
