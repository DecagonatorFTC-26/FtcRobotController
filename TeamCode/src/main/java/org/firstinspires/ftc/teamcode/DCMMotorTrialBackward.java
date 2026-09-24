package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.DCMotorTestRun;
import org.firstinspires.ftc.teamcode.mechanisms.DCMotorTestRunBackward;

@TeleOp
public class DCMMotorTrialBackward extends OpMode {
    private DCMotorTestRunBackward motorTestRun;

    @Override
    public void init() {
        motorTestRun = new DCMotorTestRunBackward();
        motorTestRun.init(hardwareMap);
    }

    @Override
    public void loop() {
        motorTestRun.setMotors(1, 1, 1, 1);
    }
}
