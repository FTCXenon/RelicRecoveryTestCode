package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by bridgetmacmillan on 11/3/17.
 */
@Autonomous(name = "DriveTestAuto")
public class DriveTestAuto extends LinearOpMode{

    DcMotor LF;
    DcMotor RF;
    DcMotor LB;
    DcMotor RB;


    @Override public void runOpMode()throws InterruptedException{

        LF = hardwareMap.dcMotor.get("LF");
        RF = hardwareMap.dcMotor.get("RF");
        LB = hardwareMap.dcMotor.get("LB");
        RB = hardwareMap.dcMotor.get("RB");

        waitForStart();
        ZeroMotors();
        RunShit(1000);

        sleep();

        TurnCounterClockwise(1000);
        ZeroMotors();


    }

    public void RunShit (long time)throws InterruptedException{
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
    public void ZeroMotors()throws InterruptedException{
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
    public void sleep () {
        sleep(3000);
    }
}
