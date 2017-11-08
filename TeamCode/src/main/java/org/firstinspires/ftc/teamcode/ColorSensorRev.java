package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by bridgetmacmillan on 11/1/17.
 */
@Disabled

@TeleOp(name = "color senor REV")
public class ColorSensorRev extends LinearOpMode {

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
