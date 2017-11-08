package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

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


/**
 * Created by bridgetmacmillan on 9/26/17.
 */

@TeleOp(name = "RUN")
public class RUN extends LinearOpMode{

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

    Servo jointOneLeft;
    Servo jointOneRight;

    public static final String TAG = "Vuforia VuMark Sample";
    OpenGLMatrix lastLocation = null;
    VuforiaLocalizer vuforia;

    public void runOpMode() throws InterruptedException {
        waitForStart();
        LF = hardwareMap.dcMotor.get("LF");
        RF = hardwareMap.dcMotor.get("RF");
        LB = hardwareMap.dcMotor.get("LB");
        RB = hardwareMap.dcMotor.get("RB");

        LF.setDirection(DcMotor.Direction.REVERSE);
        RF.setDirection(DcMotor.Direction.REVERSE);

        jointOneLeft = hardwareMap.get(Servo.class, "J1L");
        jointOneRight = hardwareMap.get(Servo.class, "J1R");

        waitForStart();


        while (opModeIsActive()) {

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

            if (gamepad2.right_stick_y > 0.2) {
                jointOneLeft.setPosition(0.8);
                jointOneRight.setPosition(0.2);
            } else if (gamepad2.right_stick_y < -0.2) {
                jointOneLeft.setPosition(0.2);
                jointOneRight.setPosition(0.8);
            } else {
                jointOneLeft.setPosition(0.5);
                jointOneRight.setPosition(0.5);
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


        }
    }
}
