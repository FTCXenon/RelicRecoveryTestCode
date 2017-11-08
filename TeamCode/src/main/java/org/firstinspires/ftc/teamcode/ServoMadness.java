package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@TeleOp(name = "ServoMadness")
public class ServoMadness extends LinearOpMode {
    Servo servo;

    @Override
    public void runOpMode() {

        servo = hardwareMap.get(Servo.class, "S");

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.a){
                servo.setPosition(0);
            }

            if (gamepad1.b){
                servo.setPosition(1);
            }

            if (gamepad1.x){
                servo.setPosition(-1);
            }
            if (gamepad1.y){
                servo.setPosition(0.5);
            }
            telemetry.update();

        }
    }
}
