package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class ShootingConfig {

    enum ShootingState {
        IDLE,
        INTAKE_START,
        SPIN_UP,
        LAUNCH,
        LAUNCHING
    }

    public ShootingState shootingState;
    public DcMotor intakeMotor;
    public DcMotorEx flywheelMotor;
    public Servo pollenPusherServo;
    public CRServo feederServo;
    public ElapsedTime feederTimer = new ElapsedTime();
    public Double feederPower = 0.8;
    public Double feederTimeSec = 10.0;
    public Double flywheelVelocity = 1700.0;

    public void init(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        flywheelMotor = hardwareMap.get(DcMotorEx.class, "flywheelMotor");
        pollenPusherServo = hardwareMap.get(Servo.class, "pollenPusherServo");
        feederServo = hardwareMap.get(CRServo.class, "feederServo");

        intakeMotor.setDirection(DcMotor.Direction.FORWARD);
        flywheelMotor.setDirection(DcMotor.Direction.REVERSE);
        pollenPusherServo.setDirection(Servo.Direction.REVERSE);
        feederServo.setDirection(CRServo.Direction.REVERSE);

        flywheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        flywheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        flywheelMotor.setVelocity(0);
        intakeMotor.setPower(0);
        feederServo.setPower(0);

        pollenPusherServo.setPosition(0);

        shootingState = ShootingState.IDLE;
    }



    public void updateState() {
        switch (shootingState) {
            case IDLE:
                intakeMotor.setPower(0);
                flywheelMotor.setVelocity(0);
                feederServo.setPower(0);
                shootingState = ShootingState.INTAKE_START;
                break;
            case INTAKE_START:
                intakeMotor.setPower(1);
                shootingState = ShootingState.SPIN_UP;
                break;
            case SPIN_UP:
                flywheelMotor.setVelocity(1800);
                if (flywheelMotor.getVelocity() >= flywheelVelocity) {
                    shootingState = ShootingState.LAUNCH;
                }
                break;
            case LAUNCH:
                feederServo.setPower(feederPower);
                feederTimer.reset();
                shootingState = ShootingState.LAUNCHING;
                break;
            case LAUNCHING:
                if (feederTimer.seconds() >= feederTimeSec) {
                    feederServo.setPower(0);
                    shootingState = ShootingState.IDLE;
                }
                break;
        }
    }
}