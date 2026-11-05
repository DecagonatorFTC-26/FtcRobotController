package org.firstinspires.ftc.teamcode.practice;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.utils.Timer;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class PedroPractice extends OpMode {
    private Follower follower;
    private Timer pathTimer;
    private Timer opModeTimer;

    public enum PathState {
        //START_POS_END_POS
        START_POS_SHOOT_POS,
        SHOOT_POS_NEUTRAL_POS,
        NEUTRAL_POS_PARK_POS
    }

    PathState pathState;
    private final Pose startPose = new Pose(82.21414342629483, 133.71513944223105, Math.toRadians(270));
    private final Pose shootPose = new Pose(83.05976095617531, 101.11254980079681, Math.toRadians(270));
    private final Pose neutralPose = new Pose(118.38645418326693, 107.61804345119522, Math.toRadians(270));
    private final Pose parkPose = new Pose(131.63446215139442, 44.47860122011952, Math.toRadians(270));



    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }
}
