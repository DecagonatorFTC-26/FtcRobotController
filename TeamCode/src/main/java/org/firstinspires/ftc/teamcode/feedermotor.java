package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;


@TeleOp(name="Feeder TeleOp", group="")
public class feedermotor extends OpMode {
    private CRServo feederServo = null;

    @Override
    public void init() {

        feederServo = hardwareMap.get(CRServo.class, "feederServo");


    }

    @Override
    public void loop() {
        double feederforward = gamepad1.right_trigger;
        double feederbackward = gamepad1.left_trigger;

        if (feederforward > 0) {

            feederServo.setPower(feederforward);
        } else if (feederbackward > 0) {

            feederServo.setPower(feederbackward);
        } else {
            feederServo.setPower(0);
        }
    }
}
