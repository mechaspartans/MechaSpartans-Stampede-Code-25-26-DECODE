package org.firstinspires.ftc.teamcode.tele_code;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.utility_code.SensorUtil;

@TeleOp
public class DistanceTest extends OpMode {
    SensorUtil distUtil = new SensorUtil();

    @Override
    public void init() {
        distUtil.init(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("Distance Left", distUtil.getDistanceLeft() + "CM");
        telemetry.addData("Distance Right", distUtil.getDistanceRight() + "CM");
    }
}
