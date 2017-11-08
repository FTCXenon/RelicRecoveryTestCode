package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptVuforiaNavigation;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * Created by bridgetmacmillan on 9/26/17.
 */

@TeleOp(name = "Mechanum Only d")
public class MechOnly extends LinearOpMode{

    DcMotor LF;
    DcMotor RF;
    DcMotor LB;
    DcMotor RB;

    double speed;
    double pi = Math.PI;
    double angle;
    double dchange;
    double posRF, posLF, posRB, posLB, maxValue;
    double leftX;
    double leftY;
    double motorPower;

    private ElapsedTime runtime = new ElapsedTime();


    public static final String TAG = "Vuforia VuMark Sample";
    OpenGLMatrix lastLocation = null;
    VuforiaLocalizer vuforia;

    public void runOpMode() throws InterruptedException {
        waitForStart();
        LF = hardwareMap.dcMotor.get("LF");
        RF = hardwareMap.dcMotor.get("RF");
        LB = hardwareMap.dcMotor.get("LB");
        RB = hardwareMap.dcMotor.get("RB");

        LB.setDirection(DcMotor.Direction.REVERSE);
        RB.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();


        while (opModeIsActive()) {

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

            double rotation;

            if (gamepad2.right_stick_x > 0){
                rotation = ((gamepad1.right_stick_x*gamepad1.right_stick_x)/1);
                telemetry.addData("POS ROTATION", "");
            }else if (gamepad2.right_stick_x < 0){
                rotation = (((gamepad1.right_stick_x*gamepad1.right_stick_x)/-1));
                telemetry.addData("NEG ROTATION", "");
            } else {
                telemetry.addData("ZERO ROTATION", "");
                rotation = 0;
            }


            frontLeftPower   = Range.clip(x + y + rotation, -1.0, 1.0);
            frontRightPower  = Range.clip(-x + y - rotation, -1.0, 1.0);
            backLeftPower    = Range.clip(-x + y + rotation, -1.0, 1.0);
            backRightPower   = Range.clip(x + y - rotation, -1.0, 1.0);

            LF.setPower(frontLeftPower);
            RF.setPower(frontRightPower);
            LB.setPower(backLeftPower);
            RB.setPower(backRightPower);

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
