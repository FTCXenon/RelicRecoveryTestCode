package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
/**
 * Created by bridgetmacmillan on 10/31/17.
 */
@TeleOp(name = "oneservo")
public class servoAuto extends LinearOpMode {

    Servo jointOneLeft;
    Servo jointOneRight;
    Servo jointTwo;
    Servo clawRotate;


    @Override
    public void runOpMode() {

        jointOneLeft = hardwareMap.get(Servo.class, "J1L");
//        jointOneRight = hardwareMap.get(Servo.class, "J1R");
//
//        jointTwo = hardwareMap.get(Servo.class, "J2");
//
//        clawRotate = hardwareMap.get(Servo.class, "CR");


        waitForStart();
        while (opModeIsActive()) {



            if (gamepad1.a){
                jointOneLeft.setPosition(1);
            }
            if (gamepad1.b){
                jointOneLeft.setPosition(0);
            }
            if (gamepad1.x){
                jointOneLeft.setPosition(.5);
            }

            telemetry.update();



        }
    }
}