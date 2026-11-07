package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class IntakeMotorTeleOp extends OpMode {

    private DcMotor intakeMotor;

    @Override
    public void init() {
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
    }

    @Override
    public void loop() {
        telemetry.addData("Intake Motor Power", gamepad2.triangle);
        if (gamepad2.triangle) {
            intakeMotor.setPower(-1.0);
        } else {
            intakeMotor.setPower(0.0);
            telemetry.update();

        }
    }
}

