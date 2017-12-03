package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;



/**
 * Created by bridgetmacmillan on 9/26/17.
 */

@TeleOp(name = "jewl arm test")
public class servoTests extends LinearOpMode{

    Servo JA;

    public void runOpMode() throws InterruptedException {
        JA = hardwareMap.get(Servo.class, "JA");

        waitForStart();


        while (opModeIsActive()) {
            if (gamepad1.a){
                JA.setPosition(0);
            }
            if (gamepad1.x){
                JA.setPosition(.5);
            }
            if (gamepad1.y){
                JA.setPosition(1);
            }
        }

    }
}

