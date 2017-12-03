package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by bridgetmacmillan on 11/5/17.
 */

@Autonomous(name = "RedSide" , group = "LinearOpMode")
public class RedSide extends LinearOpMode{
    DcMotor LF;
    DcMotor RF;
    DcMotor LB;
    DcMotor RB;

    Servo JA;
    ColorSensor CS;
    @Override public void runOpMode()throws InterruptedException{
        LF = hardwareMap.dcMotor.get("LF");
        RF = hardwareMap.dcMotor.get("RF");
        LB = hardwareMap.dcMotor.get("LB");
        RB = hardwareMap.dcMotor.get("RB");

        JA = hardwareMap.get(Servo.class, "JA");

        CS = hardwareMap.get(ColorSensor.class, "RCS");

        JA.setPosition(0.4);

        waitForStart();

        JA.setPosition(0.5);
        sleep(100);
        JA.setPosition(1);
        sleep(500);
        if((CS.blue()-CS.red())>10){
            //ball is red
            LF.setPower(0.3);
            RF.setPower(-0.3);
            LB.setPower(0.3);
            RB.setPower(-0.3);
            sleep(260);
            LF.setPower(0);
            RF.setPower(0);
            LB.setPower(0);
            RB.setPower(0);
            sleep(200);
            JA.setPosition(0.5);
            LF.setPower(-0.3);
            RF.setPower(0.3);
            LB.setPower(-0.3);
            RB.setPower(0.3);
            sleep(260);
            LF.setPower(0);
            RF.setPower(0);
            LB.setPower(0);
            RB.setPower(0);
        } else if((CS.red()-CS.blue())>10){
            //ball is blue
            LF.setPower(-0.3);
            RF.setPower(0.3);
            LB.setPower(-0.3);
            RB.setPower(0.3);
            sleep(260);
            LF.setPower(0);
            RF.setPower(0);
            LB.setPower(0);
            RB.setPower(0);
            sleep(200);
            JA.setPosition(0.5);
            LF.setPower(0.3);
            RF.setPower(-0.3);
            LB.setPower(0.3);
            RB.setPower(-0.3);
            sleep(260);
            LF.setPower(0);
            RF.setPower(0);
            LB.setPower(0);
            RB.setPower(0);
        }

        LF.setPower(0.3);
        RF.setPower(0.3);
        LB.setPower(0.3);
        RB.setPower(0.3);
        sleep(320);
        LF.setPower(0);
        RF.setPower(0);
        LB.setPower(0);
        RB.setPower(0);
        sleep(100);

        LF.setPower(-0.6);
        RF.setPower(0.6);
        LB.setPower(-0.6);
        RB.setPower(0.6);
        sleep(500);
        LF.setPower(0);
        RF.setPower(0);
        LB.setPower(0);
        RB.setPower(0);
        sleep(20000);
    }


}
