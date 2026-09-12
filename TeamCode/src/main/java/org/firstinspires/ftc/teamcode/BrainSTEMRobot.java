package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.drivetrain.Drive;

public class BrainSTEMRobot {

    // Initializing Subsystems
    public final Drive drive;
    public final Lift lift;
    public final Intake intake;

    public BrainSTEMRobot(HardwareMap hwMap, Telemetry telemetry) {
        Pose2d initalPose = new Pose2d(0, 0, 0);
        drive = new Drive(hwMap, telemetry, initalPose);
        lift = new Lift(hwMap, telemetry);
        intake = new Intake(hwMap, telemetry);
    }
}