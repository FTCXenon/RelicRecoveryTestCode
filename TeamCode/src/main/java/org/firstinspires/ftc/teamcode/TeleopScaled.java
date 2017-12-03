package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;



/**
 * Created by bridgetmacmillan on 9/26/17.
 */

@TeleOp(name = "TeleopScaled")
public class TeleopScaled extends LinearOpMode{

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

    DcMotor jointOneRight;

//    Servo jointOneLeft;
//    Servo jointOneRight;
//    Servo jointTwo;

    //
    Servo clawRotate;
    Servo clawLeft;
    Servo clawRight;
    //


    private ElapsedTime runtime = new ElapsedTime();


    public void runOpMode() throws InterruptedException {
        MechanumInit();


        clawRotate = hardwareMap.get(Servo.class, "S");
        clawLeft = hardwareMap.get(Servo.class, "CL");
        clawRight = hardwareMap.get(Servo.class, "CR");

        jointOneRight = hardwareMap.dcMotor.get("J1R");



        clawRotate.setPosition(0.5);

        waitForStart();


        while (opModeIsActive()) {

            //MECHANUM DRIVE
            leftX = -gamepad1.left_stick_y;
            leftY = -gamepad1.left_stick_x;
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



            if (gamepad2.right_stick_y > 0) {
                jointOneRight.setPower((Math.pow(gamepad2.right_stick_y,5.0)/(Math.abs(gamepad2.right_stick_y))));
            } else if (gamepad2.right_stick_y < 0){
                jointOneRight.setPower(-(Math.pow(gamepad2.right_stick_y,5.0)/(Math.abs(gamepad2.right_stick_y))));
            } else if (gamepad2.right_stick_y == 0) {
                jointOneRight.setPower(0);
            } else {
                jointOneRight.setPower(0);
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

            RF.setPower((posRF / maxValue)/1.4);
            LF.setPower((posLF / maxValue)/1.4);
            RB.setPower((posRB / maxValue)/1.4);
            LB.setPower((posLB / maxValue)/1.4);


            //Rotate
            //Clockwise
            while (gamepad1.right_stick_x > 0) {
                motorPower = Math.abs(gamepad1.right_stick_x);

                LF.setPower(motorPower/1.4);
                RF.setPower(-motorPower/1.4);
                LB.setPower(motorPower/1.4);
                RB.setPower(-motorPower/1.4);
            }
            //Counter-Clockwise
            while (gamepad1.right_stick_x < 0) {
                motorPower = Math.abs(gamepad1.right_stick_x);

                LF.setPower(-motorPower/1.4);
                LB.setPower(-motorPower/1.4);
                RF.setPower(motorPower/1.4);
                RB.setPower(motorPower/1.4);
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

