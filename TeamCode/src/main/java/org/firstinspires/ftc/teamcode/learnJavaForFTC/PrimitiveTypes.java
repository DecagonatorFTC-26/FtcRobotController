package org.firstinspires.ftc.teamcode.learnJavaForFTC;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/*
 -------- Variables and Data Types ---------
    1. byte - from the range -128 to 127
    2. char - for holding a single unicode character
    3. short - a smaller integer (almost never used in FTC)
    4. int  - this is short for integer. It is for numbers with no decimal*
    5. long - this is a larger integer. You can use it when you are concerned about running
    out of room in an int**.
    6. float - this is for floating point numbers. It is smaller than a double so we typically
    convert to a double.
    7. double - this is for floating point numbers. It can hold numbers with decimals***
    8. boolean - this can be either true or false. (It is only one or the other value given)

    *It is limited in range from +2,147,483,647 to -2,147,483,648
    **It is limited in range from +9,223,372,036,854,775,807 to -9,223,372,036,854,775,808
    ***While technically it is limited, it is so large you can think of it as unlimited
 */
@TeleOp
public class PrimitiveTypes extends OpMode {
    @Override
    public void init() {
        int teamNumber = 36013;
        double motorSpeed = 0.5;
        boolean touchSensorPressed = true;

        telemetry.addData("Team Number", teamNumber);
        telemetry.addData("Motor Speed", motorSpeed);
        telemetry.addData("Touch Sensor", touchSensorPressed);
    }

    @Override
    public void loop() {

    }
}
