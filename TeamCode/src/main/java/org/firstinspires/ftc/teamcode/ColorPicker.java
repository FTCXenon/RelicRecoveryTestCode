package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by bridgetmacmillan on 11/1/17.
 */
@Disabled

@TeleOp(name = "Color Picker")
public class ColorPicker extends LinearOpMode{

    ColorSensor colorSensor;    // Hardware Device Object

    @Override
    public void runOpMode() {

        colorSensor = hardwareMap.get(ColorSensor.class, "CS");

        waitForStart();

        while (opModeIsActive()) {

            if((colorSensor.red()-colorSensor.blue())>10){
                telemetry.addData("BALL IS RED  ", colorSensor.red());
            telemetry.addData("BALL IS RED ", colorSensor.blue());
        }

        if((colorSensor.blue()-colorSensor.red())>10){
            telemetry.addData("BALL IS BLUE  ", colorSensor.red());
            telemetry.addData("BALL IS BLUE ", colorSensor.blue());
        }

            telemetry.addData("Red  ", colorSensor.red());
            telemetry.addData("Blue ", colorSensor.blue());
            telemetry.update();

        }
    }
}
