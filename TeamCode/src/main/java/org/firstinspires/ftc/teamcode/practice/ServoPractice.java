package org.firstinspires.ftc.teamcode.practice;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HardwareConfig;

public class ServoPractice extends LinearOpMode {

    HardwareConfig hardware = new HardwareConfig();

    @Override
    public void runOpMode() {
        hardware.init(hardwareMap);
        telemetry.addData("Status", "Ready");
        telemetry.update();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                hardware.pollenPusher.setPosition(0);
            }
        }
    }
}
