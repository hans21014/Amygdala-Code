package org.firstinspires.ftc.teamcode.subsystems.drivetrain;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Drive {

    // Initializing Drivetrain
    protected DcMotorEx frontLeft;
    protected DcMotorEx backLeft;
    protected DcMotorEx frontRight;
    protected DcMotorEx backRight;
    private Telemetry telemetry;
    private final PinpointLocalizer pinpoint;
    private double inPerTick = 20;

    public Drive(HardwareMap hwMap, Telemetry telemetry, Pose2d initialPose) {

        this.telemetry = telemetry;

        // Defining the Motors
        frontLeft = (DcMotorEx)hwMap.dcMotor.get("FL");
        frontRight = (DcMotorEx)hwMap.dcMotor.get("FR");
        backLeft = (DcMotorEx)hwMap.dcMotor.get("BL");
        backRight = (DcMotorEx)hwMap.dcMotor.get("BR");

        // Reverse Direction if Needed
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        pinpoint = new PinpointLocalizer(hwMap, inPerTick, initialPose);
    }

    public void setDTMotorPowers(double frontLeftPower, double frontRightPower, double backLeftPower, double backRightPower) {
        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
    }

    public void stop() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    public void setDrivePowers(double axial, double lateral, double rotation, double scale) {
        frontLeft.setPower((axial + lateral + rotation) * scale);
        backLeft.setPower((axial - lateral + rotation) * scale);
        frontRight.setPower((axial - lateral - rotation) * scale);
        backRight.setPower((axial + lateral - rotation) * scale);
        telemetry.addData("FL power:" , frontLeft.getPower());
        telemetry.addData("FR power:" , frontRight.getPower());
        telemetry.addData("BL power:" , backLeft.getPower());
        telemetry.addData("BR power:" , backRight.getPower());

    }

    public void updatePinpoint() {
        pinpoint.update();
    }

    public Pose2d getPose() {
        return pinpoint.getPose();
    }

    public void setPose(Pose2d pose) {
        pinpoint.setPose(pose);
    }
}