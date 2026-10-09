package practice;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;


public class RumbleTest extends OpMode {

    boolean wasA, isA;

    @Override
    public void init() {


    }

    @Override
    public void loop() {
        isA = gamepad1.a;
        if (isA && !wasA) {
            gamepad1.rumble(1.0, 0, 100);
        }
        wasA = isA;

    }

}
