package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;




/*
 * This is me explaining to oliver how the code worls
 */
//@TeleOp(name = "PushBot")

public class CodeTest extends LinearOpMode {

    DcMotor LF;
    DcMotor RF;
    DcMotor LB;
    DcMotor RB;


    public void runOpMode(){
        waitForStart();
        LF = hardwareMap.dcMotor.get("LF");
        RF = hardwareMap.dcMotor.get("RF");
        LB = hardwareMap.dcMotor.get("LB");
        RB = hardwareMap.dcMotor.get("RB");

        RF.setDirection(DcMotor.Direction.REVERSE);
        RB.setDirection(DcMotor.Direction.REVERSE);

        double leftVal;
        double rightVal;
        double leftValLog;
        double rightValLog;


        while(opModeIsActive()){

            leftVal = gamepad1.left_stick_y;
            rightVal = gamepad1.right_stick_y;

            if (leftVal >0 && leftVal <= 0.15){
                leftValLog = 0.25;
            } else if (leftVal < 0 && leftVal >= -0.15) {
                leftValLog = -0.25;
            } else if (leftVal >= 0.15) {
                leftValLog = (leftVal * leftVal) / 1;
            } else if (leftVal <= -0.15){
                leftValLog = (leftVal * leftVal) / -1;
            } else {
                leftValLog = 0;
            }

            if (rightVal >0 && rightVal <= 0.15){
                rightValLog = 0.25;
            } else if (rightVal < 0 && rightVal >= -0.15) {
                rightValLog = -0.25;
            } else if (rightVal >= 0.15) {
                rightValLog = (rightVal * rightVal) / 1;
            } else if (rightVal <= -0.15){
                rightValLog = (rightVal * rightVal) / -1;
            } else {
                rightValLog = 0;
            }

            LF.setPower(leftValLog);
            LB.setPower(leftValLog);
            RB.setPower(rightValLog);
            RF.setPower(rightValLog);

        }
    }
}