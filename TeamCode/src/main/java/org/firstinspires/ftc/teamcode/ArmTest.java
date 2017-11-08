package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "ARM TEST")
@Disabled
public class ArmTest extends LinearOpMode{

    Servo JA;

    @Override public void runOpMode() {

        JA = hardwareMap.get(Servo.class, "JA");


        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                JA.setPosition(0);
            }

            if (gamepad1.b) {
                JA.setPosition(1);

            }
        }
    }
}
