package org.firstinspires.ftc.teamcode.utility_code;

import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class SensorUtil {
    private DistanceSensor distanceLeft; //
    private DistanceSensor distanceRight; //
    private ColorSensor colorSensor;
    private TouchSensor touchSensor;


    public void init(HardwareMap hwMap) {
        distanceLeft = hwMap.get(DistanceSensor.class, "dist_sens_left");
        distanceRight = hwMap.get(DistanceSensor.class, "dist_sens_right");
        //colorSensor = hwMap.get(ColorRangeSensor.class, "color_sens");
        //touchSensor = hwMap.get(TouchSensor.class, "touch_sens");
    }

    public double getDistanceLeft() {
        return distanceLeft.getDistance(DistanceUnit.CM);
    }

    public double getDistanceRight() {
        return distanceRight.getDistance(DistanceUnit.CM);
    }

    /*public double getColor() {
        return colorSensor.argb();
    }

    public double getTouch() {
        return touchSensor.getValue();
    }*/

}
