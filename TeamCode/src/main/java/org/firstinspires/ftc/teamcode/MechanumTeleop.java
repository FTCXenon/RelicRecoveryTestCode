//11/1/17
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Mechanum Teleop", group="Mecanum Opmode")
public class MechanumTeleop extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor LF = null;
    private DcMotor RF = null;
    private DcMotor LB = null;
    private DcMotor RB = null;

    Servo jointOneLeft;
    Servo jointOneRight;
    Servo jointTwo;
    Servo clawRotate;

    Servo clawLeft;
    Servo clawRight;


    double speed;
    double pi = Math.PI;
    double angle;
    double dchange;
    double posRF, posLF, posRB, posLB, maxValue;
    double leftX;
    double leftY;
    double motorPower;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).

        LF = hardwareMap.dcMotor.get("LF");
        RF = hardwareMap.dcMotor.get("RF");
        LB = hardwareMap.dcMotor.get("LB");
        RB = hardwareMap.dcMotor.get("RB");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        LF.setDirection(DcMotor.Direction.REVERSE);
        RF.setDirection(DcMotor.Direction.FORWARD);
        LB.setDirection(DcMotor.Direction.REVERSE);
        RB.setDirection(DcMotor.Direction.FORWARD);

        // Wait for the game to start (driver presses PLAY)
        jointOneLeft = hardwareMap.get(Servo.class, "J1L");
        jointOneRight = hardwareMap.get(Servo.class, "J1R");

        jointTwo = hardwareMap.get(Servo.class, "J2");

        clawRotate = hardwareMap.get(Servo.class, "S");

        clawLeft = hardwareMap.get(Servo.class, "CL");
        clawRight = hardwareMap.get(Servo.class, "CR");

        double rotation = 0;

        jointOneRight.setPosition(0.5);
        jointOneLeft.setPosition(0.5);
        clawRotate.setPosition(0.5);
        jointTwo.setPosition(0.5);


        waitForStart();
        runtime.reset();

        MechanumInit();
        // Run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double frontLeftPower;
            double frontRightPower;
            double backLeftPower;
            double backRightPower;




            double x = gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y;

            //MECHANUM DRIVE
            leftX = gamepad1.left_stick_x;
            leftY = gamepad1.left_stick_y;
            //below, check math
            angle = Math.atan2(leftX, -leftY) - (pi / 2);
            speed = Math.sqrt(leftX * leftX + leftY * leftY);
            dchange = -(leftX * leftX) / 3.33;
            posRF = speed * Math.sin(angle + (Math.PI / 4)) + dchange;
            posLF = speed * Math.cos(angle + (Math.PI / 4)) + dchange;
            posRB = speed * Math.cos(angle + (Math.PI / 4)) - dchange;
            posLB = speed * Math.sin(angle + (Math.PI / 4)) - dchange;
            //Set maxValue to pos1 absolute
            maxValue = Math.abs(posRF);

            //If pos2 absolute is greater than maxValue, then make maxValue equal to pos2 absolute
            if (Math.abs(posLF) > maxValue) {
                maxValue = Math.abs(posLF);
            }
            //If pos2 absolute is greater than maxValue, then make maxValue equal to pos2 absolute
            if (Math.abs(posLF) > maxValue) {
                maxValue = Math.abs(posLF);
            }
            //If pos3 absolute is greater than maxValue, then make maxValue equal to pos3 absolute
            if (Math.abs(posRB) > maxValue) {
                maxValue = Math.abs(posRB);
            }
            //If pos4 absolute is greater than maxValue, then make maxValue equal to pos4 absolute
            if (Math.abs(posLB) > maxValue) {
                maxValue = Math.abs(posLB);
            }

            //Check if need to scale -- if not set to 1 to nullify scale
            if (maxValue <= 1) {
                maxValue = 1;
            }
            RF.setPower(posRF / maxValue);
            LF.setPower(posLF / maxValue);
            RB.setPower(posRB / maxValue);
            LB.setPower(posLB / maxValue);
            //Rotate
            //Clockwise
            while (gamepad1.right_stick_x > 0) {
                motorPower = Math.abs(gamepad1.right_stick_x);
                LF.setPower(motorPower);
                RF.setPower(motorPower);
                LB.setPower(-motorPower);
                RB.setPower(-motorPower);
            }
            //Counter-Clockwise
            while (gamepad1.right_stick_x < 0) {
                motorPower = Math.abs(gamepad1.right_stick_x);
                LF.setPower(-motorPower);
                LB.setPower(motorPower);
                RF.setPower(-motorPower);
                RB.setPower(motorPower);
            }

            if (gamepad2.dpad_down) {
                jointTwo.setPosition(1);
                telemetry.addData("ARM 1","");
            } else if (gamepad2.dpad_up) {
                jointTwo.setPosition(0);
                telemetry.addData("ARM 0","");
            } else {
                jointTwo.setPosition(0.5);
                telemetry.addData("ARM STAHP","");
            }


            if (gamepad2.left_bumper){
                clawRotate.setPosition(0.9);
                telemetry.addData("ROTATE 1","");
            } else if (gamepad2.right_bumper){
                clawRotate.setPosition(0.1);
                telemetry.addData("ROTATE 0","");
            } else if (!gamepad2.right_bumper || !gamepad2.left_bumper){
                clawRotate.setPosition(0.5);
                telemetry.addData("STOP ROTATE","");
            } else {
                clawRotate.setPosition(0.5);
            }


            if (gamepad2.left_trigger>0){
                clawLeft.setPosition(0);
            } else {
                clawLeft.setPosition(1);
            }

            if (gamepad2.right_trigger>0){
                clawRight.setPosition(0);
            } else {
                clawRight.setPosition(1);
            }


            frontLeftPower   = Range.clip(x + y + rotation, -1.0, 1.0);
            frontRightPower  = Range.clip(-x + y - rotation, -1.0, 1.0);
            backLeftPower    = Range.clip(-x + y + rotation, -1.0, 1.0);
            backRightPower   = Range.clip(x + y - rotation, -1.0, 1.0);

            LF.setPower(frontLeftPower);
            RF.setPower(frontRightPower);
            LB.setPower(backLeftPower);
            RB.setPower(backRightPower);




            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Left", "front (%.2f), back (%.2f)", frontLeftPower, backLeftPower);
            telemetry.addData("Right", "front (%.2f), back (%.2f)", frontRightPower, backRightPower);
            telemetry.update();
        }
    }
    public void MechanumInit(){
        LF = hardwareMap.dcMotor.get("LF");
        RF = hardwareMap.dcMotor.get("RF");
        LB = hardwareMap.dcMotor.get("LB");
        RB = hardwareMap.dcMotor.get("RB");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        LF.setDirection(DcMotor.Direction.REVERSE);
        RF.setDirection(DcMotor.Direction.FORWARD);
        LB.setDirection(DcMotor.Direction.REVERSE);
        RB.setDirection(DcMotor.Direction.FORWARD);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();
    }
}
