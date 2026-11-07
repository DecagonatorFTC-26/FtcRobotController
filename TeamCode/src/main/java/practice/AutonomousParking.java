package practice;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.mechanisms.HardwareConfig;

@Autonomous
public class AutonomousParking extends LinearOpMode {

    HardwareConfig config = new HardwareConfig();

    @Override
    public void runOpMode() {
        config.init(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {

            //Move diagonally forward & left
            config.frontLeftMotor.setPower(0.12);
            config.frontRightMotor.setPower(0.70);
            config.backLeftMotor.setPower(0.70);
            config.backRightMotor.setPower(0.12);
            sleep(1200);

            //Move diagonally backward & left
            config.frontLeftMotor.setPower(-0.70);
            config.frontRightMotor.setPower(-0.12);
            config.backLeftMotor.setPower(-0.12);
            config.backRightMotor.setPower(-0.70);
            sleep(1200);

            //Stop
            config.frontLeftMotor.setPower(0);
            config.frontRightMotor.setPower(0);
            config.backLeftMotor.setPower(0);
            config.backRightMotor.setPower(0);
        }
    }

}