package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
/**
 * Created by bridgetmacmillan on 10/31/17.
 */
@Disabled
@TeleOp(name = "CLAW TEST")
public class ServoSplitter extends LinearOpMode{

    CRServo one;
    CRServo two;


    @Override
    public void runOpMode() {

        one = hardwareMap.get(CRServo.class, "S1");
        two = hardwareMap.get(CRServo.class, "S1");


        waitForStart();
        while (opModeIsActive()) {

            if (gamepad1.a) {
//                one.setDirection(x);
            }

            if (gamepad1.b) {
//                one.setDirection(x);
            }




        }
    }

}
