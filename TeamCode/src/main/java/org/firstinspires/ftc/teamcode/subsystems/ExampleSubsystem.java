package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ExampleSubsystem {

    DcMotorEx exampleMotor;
    ServoImplEx exampleServo;

    public ExampleSubsystem(HardwareMap hwMap, Telemetry telemetry) {

        exampleMotor = hwMap.get(DcMotorEx.class, "motorName");
        exampleMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        exampleMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        exampleMotor.setDirection(DcMotor.Direction.FORWARD); //change direction if needed
        exampleMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        exampleServo = hwMap.get(ServoImplEx.class, "servoName");
        exampleServo.setPwmRange(new PwmControl.PwmRange(500, 2500));
    }

    public void setMotorPower(double power) {
        exampleMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        exampleMotor.setPower(power);
    }

    public void setMotorPosition(int position, double power) {
        exampleMotor.setPower(power);
        exampleMotor.setTargetPosition(position);
        exampleMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void stopMotor() {
        setMotorPower(0);
    }

    public void setServoPosition(double position) { // position 0-1
        exampleServo.setPosition(position);
    }
}