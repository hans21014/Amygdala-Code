package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.util.PIDController;

@Config
public class Lift {

    DcMotorEx lift;
    Telemetry telemetry;
    PIDController pidController;
    public static int boxUpPosition = 3000;
    public static int boxDownPosition = 0;
    public static int boxMidPosition = 810;
    public static double kP = 0.005, kI = 0, kD = 0, kG = 0.0;
    public static double currentThreshold = 1500;
    public static double hold_power = 0.2, down_power = -0.4;


    public enum LiftState {
        UP, DOWN, OFF, HOLD, MID
    }
    public LiftState liftState;


    public Lift(HardwareMap hwMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        lift = hwMap.get(DcMotorEx.class, "lift");
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lift.setDirection(DcMotor.Direction.REVERSE); //change direction if needed
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        liftState = LiftState.DOWN;

        pidController = new PIDController(kP, kI, kD);
    }

    public void setMotorPower(double power) {
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lift.setPower(power);
    }

    public void setMotorPosition(int position, double power) {
        lift.setPower(power);
        lift.setTargetPosition(position);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void setMotorPIDPosition(int targetPosition) {
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        pidController.setTarget(targetPosition);
        double error = targetPosition - getMotorPosition();

        double power = pidController.updateWithError(error);

        telemetry.addData("Lift Target Power: ", power + kG);
        telemetry.addData("Lift Current Power: ", lift.getPower());
        lift.setPower(power + kG);
    }

    public void update() {
        switch(liftState) {
            case UP:
                if (Math.abs(getMotorPosition() - pidController.getTarget()) < 75) liftState = LiftState.HOLD;
                setMotorPIDPosition(boxUpPosition);
                break;
            case MID:
                setMotorPIDPosition(boxMidPosition);
                break;
            case DOWN:
                if (lift.getCurrent(CurrentUnit.MILLIAMPS) > currentThreshold) liftState = LiftState.OFF;
//                setMotorPower(-0.6);
                setMotorPower(down_power);
                break;
            case OFF:
                setMotorPower(0);
                lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                break;
            case HOLD:
                setMotorPower(hold_power);
                break;
        }

        telemetry.addData("Lift State", liftState);
        telemetry.addData("Lift Current", lift.getCurrent(CurrentUnit.MILLIAMPS));
    }

    public double getMotorPosition() {
        return lift.getCurrentPosition();
    }

    public void setLiftState(LiftState state) {
        this.liftState = state;
    }

    public void stopMotor() {setMotorPower(0);}
}