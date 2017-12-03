package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by bridgetmacmillan on 11/5/17.
 */

@Autonomous(name = "AdvancedAuto BlueCenterTest" , group = "LinearOpMode" )
public class BetterAutov1 extends LinearOpMode{
    DcMotor LF;
    DcMotor RF;
    DcMotor LB;
    DcMotor RB;

    ColorSensor colorSensor;
    Servo JA;


    @Override public void runOpMode()throws InterruptedException{
        LF = hardwareMap.dcMotor.get("LF");
        RF = hardwareMap.dcMotor.get("RF");
        LB = hardwareMap.dcMotor.get("LB");
        RB = hardwareMap.dcMotor.get("RB");
        colorSensor = hardwareMap.get(ColorSensor.class, "CS");
        JA = hardwareMap.get(Servo.class, "JA");


        waitForStart();

        JA.setPosition(0);
        sleep(500);


        if((colorSensor.red()-colorSensor.blue())>10){
            telemetry.addData("BALL IS RED  ", colorSensor.red());
            telemetry.addData("BALL IS RED ", colorSensor.blue());
            LF.setPower(1);
            RF.setPower(1);
            LB.setPower(1);
            RB.setPower(1);
            sleep(500);
            LF.setPower(0);
            RF.setPower(0);
            LB.setPower(0);
            RB.setPower(0);
            sleep(100);
            JA.setPosition(1);
        }

        if((colorSensor.blue()-colorSensor.red())>10){
            telemetry.addData("BALL IS BLUE  ", colorSensor.red());
            telemetry.addData("BALL IS BLUE ", colorSensor.blue());
            LF.setPower(-1);
            RF.setPower(-1);
            LB.setPower(-1);
            RB.setPower(-1);
            sleep(500);
            LF.setPower(0);
            RF.setPower(0);
            LB.setPower(0);
            RB.setPower(0);
            sleep(100);
            JA.setPosition(1);


        }

        telemetry.addData("Red  ", colorSensor.red());
        telemetry.addData("Blue ", colorSensor.blue());
        telemetry.update();



        LF.setPower(6);
        RF.setPower(-6);
        LB.setPower(6);
        RB.setPower(-6);
        sleep(500);
        LF.setPower(0);
        RF.setPower(0);
        LB.setPower(0);
        RB.setPower(0);
        sleep(20000);

    }


}
