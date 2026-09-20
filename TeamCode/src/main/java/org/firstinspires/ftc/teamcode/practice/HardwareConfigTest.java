package org.firstinspires.ftc.teamcode.practice;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HardwareConfig;

public class HardwareConfigTest extends LinearOpMode {
    HardwareConfig hardware = new HardwareConfig();

    @Override
    public void runOpMode() {
        hardware.init(hardwareMap);
        telemetry.addData("Status", "Ready");

        hardware.frontLeft.setPower(0.5);

    }
}
