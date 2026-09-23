package org.firstinspires.ftc.teamcode;//this tells location the code is in



import com.qualcomm.robotcore.eventloop.opmode.OpMode; // import means its telling java your bring something into this file
import com.qualcomm.robotcore.eventloop.opmode.TeleOp; // the other parts tell what specific package you wnt to bring

@TeleOp   // means this calss is a teleop program and it puts it in the teleop section
public class HelloWorld extends OpMode {  //
    @Override
    public void init() {
        telemetry.addData("Hello", "World");


    }

    @Override
    public void loop() {

    }

}




