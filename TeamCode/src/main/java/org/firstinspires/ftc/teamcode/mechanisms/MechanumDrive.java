package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.HardwareConfig;

public class MechanumDrive extends OpMode {

    HardwareConfig hardware = new HardwareConfig();

    @Override
    public void init() {
        hardware.init(hardwareMap);
    }

    @Override
    public void loop() {

    }

    public void drive(double forward, double strafe, double rotate){
        double frontLeftPower = forward + strafe + rotate;
        double backLeftPower = forward - strafe + rotate;
        double frontRightPower = forward - strafe - rotate;
        double backRightPower = forward + strafe - rotate;

        double maxPower = 1.0;
        //You do not need this for competition but useful for outreach.(maxSpeed)
        double maxSpeed = 1.0;
    }
}