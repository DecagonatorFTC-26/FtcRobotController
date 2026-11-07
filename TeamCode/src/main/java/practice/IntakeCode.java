package practice;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "TeleOp - Intake Control", group = "TeleOp")
public class IntakeCode extends LinearOpMode {


    private DcMotor intakeMotor;


    private static final double INTAKE_POWER = 0.85;
    private static final double OUTTAKE_POWER = -0.85;

    @Override
    public void runOpMode() {

        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");


        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);


        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        while (opModeIsActive()) {


            double intakeIn = gamepad1.right_trigger;
            double intakeOut = gamepad1.left_trigger;

            double power = intakeIn - intakeOut;


            if (gamepad1.a) {
                power = INTAKE_POWER;
            } else if (gamepad1.b) {
                power = OUTTAKE_POWER;
            } else if (gamepad1.x) {
                power = 0.0;
            }

            intakeMotor.setPower(power);


        }
    }
}