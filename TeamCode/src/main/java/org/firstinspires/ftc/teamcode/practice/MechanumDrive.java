package org.firstinspires.ftc.teamcode.practice;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.mechanisms.HardwareConfig;
import org.firstinspires.ftc.teamcode.mechanisms.FunctionConfig;

public class MechanumDrive extends OpMode {
    FunctionConfig function = new FunctionConfig();
    HardwareConfig config = new HardwareConfig();

    double forward;
    double strafe;
    double rotate;

    @Override
    public void init() {
        config.init(hardwareMap);
        function.init(config);
    }

    @Override
    public void loop() {
        forward = gamepad1.right_stick_y;
        strafe = gamepad1.right_stick_x;
        rotate = gamepad1.left_stick_x;

        function.driveFieldRelative(forward, strafe, rotate);
    }
}
