package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Simple Feeder TeleOp", group="Iterative Opmode")
public class feedermotor extends OpMode {
    private DcMotor feederMotor = null;

    @Override
    public void init() {

        feederMotor = hardwareMap.get(DcMotor.class, "feeder_motor");


        feederMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {
        double feederforward = gamepad1.right_trigger;
        double feederbackward = gamepad1.left_trigger;

        if (feederforward > 0) {

            feederMotor.setPower(feederforward);
        } else if (feederbackward > 0) {

            feederMotor.setPower(feederbackward);
        } else {
            feederMotor.setPower(0);
        }
    }
}
