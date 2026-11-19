package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
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
    public Double flywheelVelocityHigh = 1700.0;
    public Double flywheelVelocityLow = 1100.0;
    public Double curTargetVelocityFlywheel = flywheelVelocityHigh;
    public Double flywheelF = 0.0;
    public Double flywheelP = 0.0;
    public double[] stepSizesValues = {10.0, 1.0, 0.1, 0.01, 0.001, 0.0001};
    public int stepIndexValues = 1;
    public double[] stepSizesVelocity = {0.01, 0.1, 1.0, 10.0, 100.0, 1000.0};
    public int stepIndexVelocity = 1;
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
        flywheelMotor.setDirection(DcMotor.Direction.FORWARD);
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

        PIDFCoefficients pidfCoefficientsFlywheel = new PIDFCoefficients(flywheelP, 0, 0, flywheelF);
        flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficientsFlywheel);

        shootingState = ShootingState.IDLE;
    }

    public void stopShootingSteps() {
        shootingState = ShootingState.IDLE;
        updateState();
    }

    public void startIntake() {
        intakeMotor.setPower(1);
        intakeRunning = true;
    }

    public void stopIntake() {
        intakeMotor.setPower(0);
        intakeRunning = false;
    }


    public void intakeReverse() {
        intakeMotor.setPower(-1);
        intakeRunning = true;
    }

    public void startFlywheel() {
        flywheelMotor.setVelocity(flywheelVelocityHigh + 150);
        flywheelRunning = true;
    }

    public void stopFlywheel() {
        flywheelMotor.setVelocity(0);
        flywheelRunning = false;
    }
    public void flywheelReverse() {
        flywheelMotor.setVelocity(-flywheelVelocityHigh);
        flywheelRunning = true;
    }

    public void startFeeder() {
        feederServo.setPower(feederPower);
        feederRunning = true;
    }
    public void stopFeeder() {
        feederServo.setPower(0);
        feederRunning = false;
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
                flywheelMotor.setVelocity(flywheelVelocityHigh + 150);
                flywheelRunning = true;
                if (flywheelMotor.getVelocity() >= flywheelVelocityHigh) {
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
