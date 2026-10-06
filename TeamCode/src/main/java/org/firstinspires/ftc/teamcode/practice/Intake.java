package org.firstinspires.ftc.teamcode.practice;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp
public class Intake extends OpMode {

    private DcMotor intake;

    @Override
    public void init() {
        intake = hardwareMap.get(DcMotor.class, "intakeMotor");
        intake.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        if (gamepad2.left_trigger_pressed && !gamepad2.left_bumper) {
            intake.setPower(gamepad2.left_trigger);
        }
        else {
            intake.setPower(0);
        }

        if (gamepad2.left_trigger_pressed && gamepad2.left_bumper) {
            intake.setPower(-gamepad2.left_trigger);
        }
        else {
            intake.setPower(0);
        }
    }
}
