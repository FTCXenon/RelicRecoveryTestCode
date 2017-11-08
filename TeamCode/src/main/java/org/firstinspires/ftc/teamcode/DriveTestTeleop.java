package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by bridgetmacmillan on 11/3/17.
 */
@TeleOp(name = "DriveTestTeleop")
public class DriveTestTeleop extends LinearOpMode{

    DcMotor LF;
    DcMotor RF;
    DcMotor LB;
    DcMotor RB;

    Servo jointOneLeft;
    Servo jointOneRight;
    Servo jointTwo;
    Servo clawRotate;

    Servo clawLeft;
    Servo clawRight;

    @Override public void runOpMode(){

        LF = hardwareMap.dcMotor.get("LF");
        RF = hardwareMap.dcMotor.get("RF");
        LB = hardwareMap.dcMotor.get("LB");
        RB = hardwareMap.dcMotor.get("RB");

        waitForStart();
        while (opModeIsActive()) {

            if (gamepad1.a){
                //turns counter clockwise
                LF.setPower(1);
                RF.setPower(1);
                LB.setPower(1);
                RB.setPower(1);
            } else if (gamepad1.b){
                //drives backwards
                LF.setPower(1);
                RF.setPower(-1);
                LB.setPower(1);
                RB.setPower(-1);
            }else if (gamepad1.x){
                // drive forwards
                LF.setPower(-1);
                RF.setPower(1);
                LB.setPower(-1);
                RB.setPower(1);
            } else if (gamepad1.y){
                //turns clockwise
                LF.setPower(-1);
                RF.setPower(-1);
                LB.setPower(-1);
                RB.setPower(-1);
            } else {
                LF.setPower(0);
                RF.setPower(0);
                LB.setPower(0);
                RB.setPower(0);
            }


        }
    }
}
