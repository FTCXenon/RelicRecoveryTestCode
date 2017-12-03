package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;



/**
 * Created by bridgetmacmillan on 9/26/17.
 */

@TeleOp(name = "SpeedSignedQuadratic")
public class SpeedSignedQuadratic extends LinearOpMode{

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
    Servo jointTwo;

    //
    Servo clawRotate;
    Servo clawLeft;
    Servo clawRight;
    //


    private ElapsedTime runtime = new ElapsedTime();


    public void runOpMode() throws InterruptedException {
        MechanumInit();

        jointOneLeft = hardwareMap.get(Servo.class, "J1L");
        jointOneRight = hardwareMap.get(Servo.class, "J1R");

        jointTwo = hardwareMap.get(Servo.class, "J2");

        //
        clawRotate = hardwareMap.get(Servo.class, "S");
        clawLeft = hardwareMap.get(Servo.class, "CL");
        clawRight = hardwareMap.get(Servo.class, "CR");
        //

        jointOneRight.setPosition(0.5);
        jointOneLeft.setPosition(0.5);
        clawRotate.setPosition(0.5);
        jointTwo.setPosition(0.5);

        waitForStart();


        while (opModeIsActive()) {

            //MECHANUM DRIVE

            leftX = gamepad1.left_stick_y;
            leftY = gamepad1.left_stick_x;

            //below, check math
            angle = Math.atan2(leftX, -leftY) - (pi / 2);
            speed = Math.sqrt(leftX * leftX + leftY * leftY);
            speed = (Math.pow(speed,3.0)/(Math.abs(speed)));
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

            if (gamepad2.left_stick_y > 0.2) {
                jointTwo.setPosition(1);
            } else if (gamepad2.left_stick_y < -0.2) {
                jointTwo.setPosition(0);
            } else {
                jointTwo.setPosition(0.5);
            }


            //
            if (gamepad2.x){
                clawRotate.setPosition(0.9);
                telemetry.addData("ROTATE 1","");
            } else if (gamepad2.b){
                clawRotate.setPosition(0.1);
                telemetry.addData("ROTATE 0","");
            } else if (!gamepad2.x || !gamepad2.b){
                clawRotate.setPosition(0.5);
                telemetry.addData("STOP ROTATE","");
            } else {
                clawRotate.setPosition(0.5);
            }
            //



            //
            if (gamepad2.left_bumper){
                clawLeft.setPosition(1);
            } else {
                clawLeft.setPosition(0);
            }

            if (gamepad2.right_bumper){
                clawRight.setPosition(0);
            } else {
                clawRight.setPosition(1);
            }
            //

            //Check if need to scale -- if not set to 1 to nullify scale
            if (maxValue <= 1) {
                maxValue = 1;
            }

//            RF.setPower(Math.pow((posRF / maxValue),3.0)/(Math.abs(posRF / maxValue)));
//            LF.setPower(Math.pow((posLF / maxValue),3.0)/(Math.abs(posLF / maxValue)));
//            RB.setPower(Math.pow((posRB / maxValue),3.0)/(Math.abs(posRB / maxValue)));
//            LB.setPower(Math.pow((posLB / maxValue),3.0)/(Math.abs(posLB / maxValue)));

            RF.setPower(posRF / maxValue);
            LF.setPower(posLF / maxValue);
            RB.setPower(posRB / maxValue);
            LB.setPower(posLB / maxValue);

            //Rotate
            //Clockwise
            while (gamepad1.right_stick_x > 0) {
                motorPower = Math.abs(gamepad1.right_stick_x);
//                motorPower = Math.abs(Math.pow(motorPower,3.0)/(Math.abs(motorPower)));

                LF.setPower(motorPower);
                RF.setPower(-motorPower);
                LB.setPower(motorPower);
                RB.setPower(-motorPower);
            }
            //Counter-Clockwise
            while (gamepad1.right_stick_x < 0) {
                motorPower = Math.abs(gamepad1.right_stick_x);
//                motorPower = Math.abs(Math.pow(motorPower,3.0)/(Math.abs(motorPower)));

                LF.setPower(-motorPower);
                LB.setPower(-motorPower);
                RF.setPower(motorPower);
                RB.setPower(motorPower);
            }

            telemetry.update();

        }

    }
    public void MechanumInit(){
        LF = hardwareMap.dcMotor.get("LF");
        RF = hardwareMap.dcMotor.get("RF");
        LB = hardwareMap.dcMotor.get("LB");
        RB = hardwareMap.dcMotor.get("RB");

        LF.setDirection(DcMotor.Direction.REVERSE);
        RF.setDirection(DcMotor.Direction.FORWARD);
        LB.setDirection(DcMotor.Direction.REVERSE);
        RB.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        runtime.reset();


    }
}

