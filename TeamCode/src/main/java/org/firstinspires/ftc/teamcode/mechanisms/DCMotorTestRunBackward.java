package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DCMotorTestRunBackward {
    private DcMotor leftMotorFront;
    private DcMotor leftMotorBack;
    private DcMotor rightMotorFront;
    private DcMotor rightMotorBack;

    public void init(HardwareMap hardwareMap) {
        leftMotorFront = hardwareMap.get(DcMotor.class,"leftMotorFront");
        leftMotorFront.setDirection(DcMotor.Direction.REVERSE);
        leftMotorFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftMotorBack = hardwareMap.get(DcMotor.class,"leftMotorBack");
        leftMotorBack.setDirection(DcMotor.Direction.REVERSE);
        leftMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        rightMotorFront = hardwareMap.get(DcMotor.class,"rightMotorFront");
        rightMotorFront.setDirection(DcMotor.Direction.FORWARD);
        rightMotorFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        rightMotorBack = hardwareMap.get(DcMotor.class,"rightMotorBack");
        rightMotorBack.setDirection(DcMotor.Direction.FORWARD);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void setMotors(double leftFront, double leftBack, double rightFront, double rightBack) {
        leftMotorFront.setPower(leftFront);
        leftMotorBack.setPower(leftBack);
        rightMotorFront.setPower(rightFront);
        rightMotorBack.setPower(rightBack);
    }


}
