package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
/**
 * Created by bridgetmacmillan on 10/31/17.
 */
@TeleOp(name = "REV")
public class ServoTest extends LinearOpMode {

    Servo jointOneLeft;
    Servo jointOneRight;
    Servo jointTwo;
    Servo clawRotate;

    Servo clawLeft;
    Servo clawRight;


    @Override
    public void runOpMode()throws InterruptedException{

        jointOneLeft = hardwareMap.get(Servo.class, "J1L");
        jointOneRight = hardwareMap.get(Servo.class, "J1R");

        jointTwo = hardwareMap.get(Servo.class, "J2");

        clawRotate = hardwareMap.get(Servo.class, "S");

        clawLeft = hardwareMap.get(Servo.class, "CL");
        clawRight = hardwareMap.get(Servo.class, "CR");


        jointOneRight.setPosition(0.5);
        jointOneLeft.setPosition(0.5);
        clawRotate.setPosition(0.5);
        jointTwo.setPosition(0.5);



        waitForStart();
        while (opModeIsActive()) {

//            clawRotate(gamepad1.left_bumper,gamepad1.right_bumper);


            if (gamepad1.right_stick_y > 0.2) {
                jointOneLeft.setPosition(0.65);
                jointOneRight.setPosition(0.45);
            } else if (gamepad1.right_stick_y < -0.2) {
                jointOneLeft.setPosition(0.45);
                jointOneRight.setPosition(0.65);
            } else {
                jointOneLeft.setPosition(0.5);
                jointOneRight.setPosition(0.5);
            }

            if (gamepad1.left_stick_y > 0.2) {
                jointTwo.setPosition(0.9);
                telemetry.addData("ARM 1","");
            } else if (gamepad1.left_stick_y < -0.2) {
                jointTwo.setPosition(0.1);
                telemetry.addData("ARM 0","");
            } else {
                jointTwo.setPosition(0.5);
                telemetry.addData("ARM STAHP","");
            }


            if (gamepad1.left_bumper){
                clawRotate.setPosition(0.9);
                telemetry.addData("ROTATE 1","");
            } else if (gamepad1.right_bumper){
                clawRotate.setPosition(0.1);
                telemetry.addData("ROTATE 0","");
            } else if (!gamepad1.right_bumper || !gamepad1.left_bumper){
                clawRotate.setPosition(0.5);
                telemetry.addData("STOP ROTATE","");
            } else {
                clawRotate.setPosition(0.5);
            }


            if (gamepad1.left_trigger>0){
                clawLeft.setPosition(0);
            } else {
                clawLeft.setPosition(1);
            }

            if (gamepad1.right_trigger>0){
                clawRight.setPosition(0);
            } else {
                clawRight.setPosition(1);
            }

        telemetry.update();



    }



//    public void clawRotate(boolean left, boolean right){
//        if (gamepad1.left_bumper) {
//            clawRotate.setPosition(0.9);
//        } else if (gamepad1.right_bumper) {
//            clawRotate.setPosition(0.1);
//        } else {
//            clawRotate.setPosition(0.5);
//        }
//    }
}}