package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "AutoRed" , group = "LinearOpMode" )
public class AutoRed extends LinearOpMode{

    ColorSensor CS;
    Servo JA;
    DcMotor LF;
    DcMotor RF;
    DcMotor LB;
    DcMotor RB;

    long rotateTime = 1000;
    long extendedTime = 3000;

    boolean runMe = true;

//    public void HardwareMap()throws InterruptedException{
//        CS = hardwareMap.get(ColorSensor.class, "CS");
//        JA = hardwareMap.get(Servo.class, "JA");
//        LF = hardwareMap.dcMotor.get("LF");
//        RF = hardwareMap.dcMotor.get("RF");
//        LB = hardwareMap.dcMotor.get("LB");
//        RB = hardwareMap.dcMotor.get("RB");
//
//    }

    @Override public void runOpMode()throws InterruptedException{
//        HardwareMap();
        CS = hardwareMap.get(ColorSensor.class, "CS");
        JA = hardwareMap.get(Servo.class, "JA");
        LF = hardwareMap.dcMotor.get("LF");
        RF = hardwareMap.dcMotor.get("RF");
        LB = hardwareMap.dcMotor.get("LB");
        RB = hardwareMap.dcMotor.get("RB");
        waitForStart();

        ZeroMotors();
        JewelArmRed(extendedTime);
//        while (opModeIsActive() && runMe){
//            ZeroMotors();
//            JewelArmRed(extendedTime);
//            runMe = false;
//        }
    }

    public void ZeroMotors()throws InterruptedException{
        LF.setPower(0);
        RF.setPower(0);
        LB.setPower(0);
        RB.setPower(0);
    }

    public void CloseJewelArm()throws InterruptedException{
        JA.setPosition(0);
    }

    public void JewelArmRed(long extendedTime)throws InterruptedException{
        JA.setPosition(0);
        JA.setPosition(1);
        ColorPickerRed();
//        sleep(extendedTime);
        CloseJewelArm();
    }

    public void JewelArmBlue(long extendedTime)throws InterruptedException{
        JA.setPosition(0);
        JA.setPosition(1);
        ColorPickerBlue();
//        sleep(extendedTime);
        CloseJewelArm();
    }

    public void ColorPickerBlue()throws InterruptedException{


        if((CS.red()-CS.blue())>10){
            telemetry.addData("BALL IS RED","");
            TurnCounterClockwise(rotateTime);
        }

        if((CS.blue()-CS.red())>10){
            telemetry.addData("BALL IS BLUE","");
            TurnClockwise(rotateTime);
        }
        CloseJewelArm();
    }

    public void ColorPickerRed()throws InterruptedException{

        if((CS.red()-CS.blue())>10){
            telemetry.addData("BALL IS RED  ","");
            TurnCounterClockwise(rotateTime);
        }

        if((CS.blue()-CS.red())>10){
            telemetry.addData("BALL IS BLUE","");
            TurnCounterClockwise(rotateTime);
        }

        CloseJewelArm();
    }

    public void TurnClockwise(long time)throws InterruptedException{
        LF.setPower(-1);
        RF.setPower(-1);
        LB.setPower(-1);
        RB.setPower(-1);
        sleep(time);
        LF.setPower(0);
        RF.setPower(0);
        LB.setPower(0);
        RB.setPower(0);
    }

    public void TurnCounterClockwise(long time)throws InterruptedException{
        LF.setPower(1);
        RF.setPower(1);
        LB.setPower(1);
        RB.setPower(1);
        sleep(time);
        LF.setPower(0);
        RF.setPower(0);
        LB.setPower(0);
        RB.setPower(0);
    }

    public void DriveForward(long time)throws InterruptedException{
        LF.setPower(-1);
        RF.setPower(1);
        LB.setPower(-1);
        RB.setPower(1);
        sleep(time);
        LF.setPower(0);
        RF.setPower(0);
        LB.setPower(0);
        RB.setPower(0);
    }

    public void DriveBackwards(long time)throws InterruptedException{
        LF.setPower(1);
        RF.setPower(-1);
        LB.setPower(1);
        RB.setPower(-1);
        sleep(time);
        LF.setPower(0);
        RF.setPower(0);
        LB.setPower(0);
        RB.setPower(0);
    }
}
