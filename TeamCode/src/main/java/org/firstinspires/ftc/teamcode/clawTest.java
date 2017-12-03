package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by bridgetmacmillan on 11/5/17.
 */

@TeleOp(name = "ClawTest" , group = "LinearOpMode" )
public class clawTest extends LinearOpMode{
    Servo clawLeft;
    Servo clawRight;
    @Override public void runOpMode()throws InterruptedException{
        clawLeft = hardwareMap.get(Servo.class, "CL");
        clawRight = hardwareMap.get(Servo.class, "CR");
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.left_bumper){
                clawLeft.setPosition(1);
            } else {
                clawLeft.setPosition(0);
            }

            if (gamepad1.right_bumper){
                clawRight.setPosition(0);
            } else {
                clawRight.setPosition(1);
            }
        }
    }

}
