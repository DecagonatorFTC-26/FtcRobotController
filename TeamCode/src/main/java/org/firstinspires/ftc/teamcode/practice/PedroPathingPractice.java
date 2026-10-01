package org.firstinspires.ftc.teamcode.practice;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.utils.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous
public class PedroPathingPractice extends OpMode {
    private Follower follower;
    private Timer pathTimer;
    private Timer opModeTimer;
     public enum PathState {
         //START POSTITION_END POSITION
         //DRIVE > MOVEMENT STATE
         //SHOOT > ATTEMPT TO SHOOT NECTAR OR POLLEN

         DRIVE_STARTPOS_SHOOT_POS,
         SHOOT_PRELOAD
     }

     PathState pathState;

     //(THIS IS GOING TO BE USED)private final Pose startPose = new Pose();

    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }
}
