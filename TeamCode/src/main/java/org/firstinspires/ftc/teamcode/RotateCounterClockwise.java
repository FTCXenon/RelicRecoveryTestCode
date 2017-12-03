package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by bridgetmacmillan on 11/5/17.
 */

@Autonomous(name = "RotateCounterClockwise" , group = "LinearOpMode" )
public class RotateCounterClockwise extends LinearOpMode{
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
        LF.setPower(0.3);
        RF.setPower(0.3);
        LB.setPower(0.3);
        RB.setPower(0.3);
        sleep(500);
        LF.setPower(0);
        RF.setPower(0);
        LB.setPower(0);
        RB.setPower(0);
        sleep(20000);
    }
}
