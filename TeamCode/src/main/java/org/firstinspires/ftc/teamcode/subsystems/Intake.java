package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {

    DcMotorEx lift;
    DcMotorEx collector;

    public Intake(HardwareMap hwMap, Telemetry telemetry) {
        collector = hwMap.get(DcMotorEx.class, "wheels");
        collector.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        collector.setDirection(DcMotor.Direction.REVERSE); //change direction if needed
        collector.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



    }

    public void setMotorPower(double power) {
        //makes robot move at that set power
        collector.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        collector.setPower(power);
    }

    public void setMotorPosition(int position, double power) {
        collector.setPower(power);
        collector.setTargetPosition(position);
        collector.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void stopMotor() {
        setMotorPower(0);
    }
}