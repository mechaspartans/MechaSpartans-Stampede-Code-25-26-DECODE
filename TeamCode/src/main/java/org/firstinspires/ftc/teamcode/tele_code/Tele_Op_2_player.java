package org.firstinspires.ftc.teamcode.tele_code;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.utility_code.Stampede;

@TeleOp(name = "Tele2Op")
public class Tele_Op_2_player extends OpMode {

    /* Declare OpMode members. */
    Stampede stampede;
    double x1, y1, x2;
    double outBottomSpeed, outTopSpeed, inSpeed, minSpeed;
    ElapsedTime holdTimer = new ElapsedTime();

    public void initRobot() {
        stampede = new Stampede();
    }

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        initRobot();
        stampede.init(hardwareMap);
        // You can set the robot's starting orientation
        stampede.angleTracker.setOrientation(180);

        telemetry.addData("Say", "Hello Driver");
        telemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {

    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        holdTimer.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        //turn correcting
        if (Math.abs(gamepad1.left_stick_y) > .2) {
            y1 = -gamepad1.left_stick_y;
        }
        else {
            y1 = 0;
        }
        if (Math.abs(gamepad1.left_stick_x) > .2) {
            x1 = gamepad1.left_stick_x;
        }
        else {
            x1 = 0;
        }
        boolean corrected = false;
        if (Math.abs(gamepad1.right_stick_x) > .2) {
            x2 = gamepad1.right_stick_x;
        }
        else {
            x2 = 0;
        }
        if (gamepad2.right_trigger > .4) {
            outBottomSpeed = 0.41;
            outTopSpeed = 0.41;
        } else if (!gamepad2.right_bumper) {
            outBottomSpeed = 0;
            outTopSpeed = 0;
        }
        if (gamepad2.right_bumper) {
            outBottomSpeed = 0.50;
            outTopSpeed = 0.44;
        } else if (gamepad2.right_trigger < .4) {
            outBottomSpeed = 0;
            outTopSpeed = 0;
        }

        if (gamepad1.left_trigger > .4) {
            inSpeed = 1;
        } else if (gamepad1.left_trigger < .4) {
            inSpeed = 0;
        }
        if (gamepad2.left_trigger > .4) {
            minSpeed = 1;
        } else if (gamepad2.left_trigger < .4) {
            minSpeed = 0;
        }
        if (gamepad1.a) {
            stampede.limelightPositioningClose(telemetry);
        }
        if (gamepad1.b) {
            stampede.limelightPositioningFar(telemetry);
        }
        if(gamepad1.y) {
            stampede.kickstand.setPosition(1);
        }else {
            stampede.kickstand.setPosition(0);
        }
        if (gamepad2.x) {
            stampede.pusher.setPosition(0);
        } else {
            stampede.pusher.setPosition(1);
        }
        if (gamepad2.dpad_left) {
            double x = stampede.sensorUtil.getDistanceLeft();
            double y = stampede.sensorUtil.getDistanceRight();
            telemetry.addData("", Math.toDegrees(Math.atan((y-x)/221.73)));
            telemetry.addData("", Math.sqrt(Math.pow(x,2) + Math.pow(221.73,2)));
        }
        if (gamepad2.dpad_right) {
            double x = stampede.sensorUtil.getDistanceLeft();
            double y = stampede.sensorUtil.getDistanceRight();
            telemetry.addData("Angle", Math.toDegrees(Math.atan((x-y)/221.73))); //red
            telemetry.addData("", Math.sqrt(Math.pow(y,2) + Math.pow(221.73,2)));
        }
        if (gamepad2.dpad_up) {
            telemetry.addData("Left Distance Sensor", stampede.sensorUtil.getDistanceLeft());
            telemetry.addData("Right Distance Sensor", stampede.sensorUtil.getDistanceRight());
        }

        //


//Nevin Coded 10/20/20252
        //if (gamepad1.a) {
        // stampede.pushert.setPosition(0);
        //stampede.gate.setPosition(0);
        // } else {
        // stampede.pusherb.setPosition(0);
        //stampede.pushert.setPosition(0.5);
        //stampede.gate.setPosition(0.5);
//ng251022}

        x1 *= 0.75;
        y1 *= 0.75;
        x2 *= 0.75;
        if (!gamepad2.a && !gamepad2.b) {stampede.drive(y1, x1, x2, telemetry);}
        stampede.driveIntake(inSpeed, minSpeed, telemetry);
        stampede.driveOuttake(outBottomSpeed, outTopSpeed, telemetry);
        telemetry.addData("Autoturning Active", corrected ? "Yes" : "No");


        stampede.updateFieldPosition();
        stampede.reportTelemetry(telemetry);
        telemetry.update();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}