package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by bridgetmacmillan on 9/11/17.
 */
@Disabled

@Autonomous(name = "AutoMadness Blue")
public class AutoMadness extends LinearOpMode{

    DcMotor LF;
    DcMotor RF;
    DcMotor LB;
    DcMotor RB;

    ColorSensor colorSensor;

    Servo jewelArm;

//    OpenGLMatrix lastLocation = null;
//    VuforiaLocalizer vuforia;
//
//    VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
//    VuforiaTrackable relicTemplate = relicTrackables.get(0);

    public void HardwareMap()throws InterruptedException{
        LF = hardwareMap.dcMotor.get("LF");
        RF = hardwareMap.dcMotor.get("RF");
        LB = hardwareMap.dcMotor.get("LB");
        RB = hardwareMap.dcMotor.get("RB");

        colorSensor = hardwareMap.colorSensor.get("S");

        jewelArm = hardwareMap.servo.get("JA");

//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
//        parameters.vuforiaLicenseKey = "AYh6YDf/////AAAAGZDBru4WXE+AmK7KehDG6RJtFNArHiwXcwwFDhnuZ5mVngFbavJmOWea89Pk12G8YxqKKWn2BI1LB997tYYpps9gzKdsskXu/nB6TJ/yqGT26F3mMKwRC81OnHum3VYIMm7eBUojOVJKrLkumfL4JBr1uxzL56S6r57DaW1LBS1cn4VwPcv0/2ALgoPGmH3LQlcANJjPMqp217o0mN5lApzOsoVbVVlAsVbF9YHcAO9pC+JKOuybJ5NninkoDwBkqhuVcFlmUZnE0YWwherirv7HXG3Y0be0sbQQvVDOcIhpcU4Dz9AujHnMCo9Mjmcq50nOv8yBITbSkJwC3fQZslfZ2jdn/zM3m2Z3RgkGxMaT\n";
//        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
//        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
//
//        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        telemetry.addData(">", "Press Play to start");
        telemetry.update();

        LF.setDirection(DcMotor.Direction.REVERSE);
        RF.setDirection(DcMotor.Direction.FORWARD);
        LB.setDirection(DcMotor.Direction.REVERSE);
        RB.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();

//        relicTrackables.activate();

    }
    public void driveForawrd(long driveForward)throws InterruptedException{
        LF.setPower(1);
        RF.setPower(1);
        LB.setPower(1);
        RB.setPower(1);
        sleep(driveForward);
        LF.setPower(0);
        RF.setPower(0);
        LB.setPower(0);
        RB.setPower(0);
    }

    public void driveSlowForward(long driveForward)throws InterruptedException{
        LF.setPower(0.4);
        RF.setPower(0.4);
        LB.setPower(0.4);
        RB.setPower(0.4);
        sleep(driveForward);
        LF.setPower(0);
        RF.setPower(0);
        LB.setPower(0);
        RB.setPower(0);
    }
    public void driveSlowbackwards(long driveForward)throws InterruptedException{
        LF.setPower(-0.4);
        RF.setPower(-0.4);
        LB.setPower(-0.4);
        RB.setPower(-0.4);
        sleep(driveForward);
        LF.setPower(0);
        RF.setPower(0);
        LB.setPower(0);
        RB.setPower(0);
    }
    public void driveBackward(long DriveBackward)throws InterruptedException{
        LF.setPower(-1);
        RF.setPower(-1);
        LB.setPower(-1);
        RB.setPower(-1);
        sleep(DriveBackward);
        LF.setPower(0);
        RF.setPower(0);
        LB.setPower(0);
        RB.setPower(0);
    }
    public void jewelArm()throws InterruptedException{

        sleep(3000);
        if((colorSensor.red()-colorSensor.blue())>10){
            telemetry.addData("BALL IS RED  ", colorSensor.red());
            telemetry.addData("BALL IS RED ", colorSensor.blue());
            sleep(3000);
            jewelArm.setPosition( 0);
            telemetry.addData("DRIVE FORWARD ", "");
            driveSlowForward(1000);
        }

        if((colorSensor.blue()-colorSensor.red())>10){
            telemetry.addData("BALL IS BLUE  ", colorSensor.red());
            telemetry.addData("BALL IS BLUE ", colorSensor.blue());
            sleep(3000);
            jewelArm.setPosition(0);
            telemetry.addData("DRIVE BACKWARDS ", "");

            driveSlowbackwards(1000);
        }

        telemetry.addData("Red  ", colorSensor.red());
        telemetry.addData("Blue ", colorSensor.blue());
        telemetry.update();

    }
//    public void pictographDecoder(){
//        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
//        if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
//
//                /* Found an instance of the template. In the actual game, you will probably
//                 * loop until this condition occurs, then move on to act accordingly depending
//                 * on which VuMark was visible. */
//            telemetry.addData("VuMark", "%s visible", vuMark);
//
//                /* For fun, we also exhibit the navigational pose. In the Relic Recovery game,
//                 * it is perhaps unlikely that you will actually need to act on this pose information, but
//                 * we illustrate it nevertheless, for completeness. */
//            OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();
//            telemetry.addData("Pose", format(pose));
//
//                /* We further illustrate how to decompose the pose into useful rotational and
//                 * translational components */
//            if (pose != null) {
//                VectorF trans = pose.getTranslation();
//                Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);
//
//                // Extract the X, Y, and Z components of the offset of the target relative to the robot
//                double tX = trans.get(0);
//                double tY = trans.get(1);
//                double tZ = trans.get(2);
//
//                // Extract the rotational components of the target relative to the robot
//                double rX = rot.firstAngle;
//                double rY = rot.secondAngle;
//                double rZ = rot.thirdAngle;
//            }
//        }
//        else {
//            telemetry.addData("VuMark", "not visible");
//        }
//        telemetry.update();
//    }

//    String format(OpenGLMatrix transformationMatrix) {
//        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
//    }

    @Override public void runOpMode()throws InterruptedException{
        HardwareMap();
        waitForStart();
        while (opModeIsActive()) {
            //CODE GOES HERE
            jewelArm.setPosition(1);

            jewelArm();


        }
    }
}
