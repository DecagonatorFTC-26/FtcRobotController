package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class ShootingConfig {

    public enum ShootingState {
        IDLE,
        INTAKE_START,
        SPIN_UP,
        LAUNCH,
        LAUNCHING
    }

    public ShootingState shootingState = ShootingState.IDLE;
    public DcMotor intakeMotor;
    public DcMotorEx flywheelMotor;
    public Servo pollenPusherServo;
    public CRServo feederServo;
    public ElapsedTime feederTimer = new ElapsedTime();
    public Double feederPower = 0.8;
    public Double feederTimeSec = 10.0;
    public Double flywheelVelocity = 1700.0;
    public Boolean flywheelRunning = false;
    public Boolean intakeRunning = false;
    public Boolean feederRunning = false;
    public Boolean pollenPusherUp = true;

    public void init(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        flywheelMotor = hardwareMap.get(DcMotorEx.class, "flywheelMotor");
        pollenPusherServo = hardwareMap.get(Servo.class, "pollenServo");
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

    public void stopShootingSteps() {
        shootingState = ShootingState.IDLE;
        updateState();
    }

    public void startIntake() {
        shootingState = ShootingState.INTAKE_START;
        updateState();
    }

    public void intakeReverse() {
        intakeMotor.setPower(-1);
        intakeRunning = true;
    }

    public void startFlywheel() {
        shootingState = ShootingState.SPIN_UP;
        updateState();
    }

    public void flywheelReverse() {
        flywheelMotor.setVelocity(-flywheelVelocity);
        flywheelRunning = true;
    }

    public void launchPollen() {
        shootingState = ShootingState.LAUNCH;
        updateState();
    }

    public void feederReverse() {
        feederServo.setPower(-feederPower);
        feederRunning = true;
    }

    public void pollenPusherUp() {
        pollenPusherServo.setPosition(1);
        pollenPusherUp = true;
    }

    public void pollenPusherDown() {
        pollenPusherServo.setPosition(0.25);
        pollenPusherUp = false;
    }

    public void checkRunning() {
        if (intakeRunning) {
            intakeMotor.setPower(1);
        }
        if (flywheelRunning) {
            flywheelMotor.setVelocity(flywheelVelocity);
        }
        if (feederRunning) {
            feederServo.setPower(feederPower);
        }
    }

    public void updateState() {
        switch (shootingState) {
            case IDLE:
                intakeMotor.setPower(0);
                flywheelMotor.setVelocity(0);
                feederServo.setPower(0);
                flywheelRunning = false;
                feederRunning = false;
                intakeRunning = false;
                shootingState = ShootingState.INTAKE_START;
                break;
            case INTAKE_START:
                intakeMotor.setPower(1);
                intakeRunning = true;
                shootingState = ShootingState.SPIN_UP;
                break;
            case SPIN_UP:
                flywheelMotor.setVelocity(1800);
                flywheelRunning = true;
                if (flywheelMotor.getVelocity() >= flywheelVelocity) {
                    shootingState = ShootingState.LAUNCH;
                }
                break;
            case LAUNCH:
                feederServo.setPower(feederPower);
                feederTimer.reset();
                feederRunning = true;
                shootingState = ShootingState.LAUNCHING;
                break;
            case LAUNCHING:
                if (feederTimer.seconds() >= feederTimeSec) {
                    feederServo.setPower(0);
                    feederRunning = false;
                    shootingState = ShootingState.IDLE;
                }
                break;
        }
    }
}
