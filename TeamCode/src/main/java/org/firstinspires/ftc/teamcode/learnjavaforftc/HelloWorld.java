package org.firstinspires.ftc.teamcode.learnjavaforftc;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/*
-------- Parts of an OpMode --------
OpModes are required to have 2 methods:
    1. init() - This is run once when the diver presses INIT
    2. loop() - This is run repeatedly after the driver presses PLAY but before STOP
In addition, there are 3 optional methods. These are less common but can be very useful:
    1. init_loop() - This is run repeatedly after the driver presses INIT but before PLAY
    2. start() - This is run once when the driver presses PLAY
    3. stop() - This is run once when the driver presses STOP
 */
@TeleOp
public class HelloWorld  extends OpMode {
    @Override
    public void init() {
        telemetry.addData("Hello", "World");
    }

    @Override
    public void loop() {

    }
}
