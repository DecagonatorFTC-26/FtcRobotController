package org.firstinspires.ftc.teamcode.learnJavaForFTC;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class UseString extends OpMode {
    @Override
    public void init() {
        String myName = "Ritesh Satapathy";

        telemetry.addData("Hello", myName);
    }

    @Override
    public void loop() {

    }
}
