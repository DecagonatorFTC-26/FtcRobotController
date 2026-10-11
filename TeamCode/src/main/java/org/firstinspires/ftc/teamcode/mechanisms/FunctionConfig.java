package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class FunctionConfig {

    //This is the HardwareConfig
    HardwareConfig config = new HardwareConfig();

    //This is added variables
        //To be added

    //This is the added functions
    public void drive(double forward, double strafe, double rotate) {
        double frontLeftPower = forward + strafe + rotate;
        double backLeftPower = forward - strafe + rotate;
        double frontRightPower = forward - strafe - rotate;
        double backRightPower = forward + strafe - rotate;

        double maxPower = 1.0;
        //You do not need this for competition but useful for outreach.(maxSpeed)
        double maxSpeed = 1.0;

        maxPower = Math.max(maxPower, Math.abs(frontLeftPower));
        maxPower = Math.max(maxPower, Math.abs(frontRightPower));
        maxPower = Math.max(maxPower, Math.abs(backLeftPower));
        maxPower = Math.max(maxPower, Math.abs(backRightPower));

        config.frontLeft.setPower(maxSpeed * (frontLeftPower / maxPower));
        config.frontRight.setPower(maxSpeed * (frontRightPower / maxPower));
        config.backLeft.setPower(maxSpeed * (backLeftPower / maxPower));
        config.backRight.setPower(maxSpeed * (backRightPower / maxPower));
    }

    public void driveFieldRelative(double forward, double strafe, double rotate) {
        double theta = Math.atan2(forward, strafe);
        double r = Math.hypot(strafe, forward);

        theta = AngleUnit.normalizeRadians(theta -
                config.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));

        double newForward = r * Math.sin(theta);
        double newStrafe = r * Math.cos(theta);

        this.drive(newForward, newStrafe, rotate);
    }
}