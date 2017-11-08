package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;


@TeleOp(name = "color senor MR")
public class ColorSensorMR extends LinearOpMode {

    ColorSensor colorSensor;    // Hardware Device Object


    @Override
    public void runOpMode() {

        colorSensor = hardwareMap.get(ColorSensor.class, "CS");

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Red  ", colorSensor.red());
            telemetry.addData("Blue ", colorSensor.blue());
            telemetry.update();

        }
    }
}
