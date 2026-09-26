package org.firstinspires.ftc.teamcode.practice;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HardwareConfig;

public class ServoPractice extends LinearOpMode {

    HardwareConfig config = new HardwareConfig();

    @Override
    public void runOpMode() {
        config.init(hardwareMap);
        telemetry.addData("Status", "Ready");
        telemetry.update();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                config.pollenPusher.setPosition(0);
            }
        }
    }
}
