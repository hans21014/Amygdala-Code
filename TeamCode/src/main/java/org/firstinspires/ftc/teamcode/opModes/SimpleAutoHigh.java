package org.firstinspires.ftc.teamcode.opModes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.BrainSTEMRobot;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.util.AutoCommands;

@Config
@Autonomous (name = "Simple Auto High - Blue ")
public class SimpleAutoHigh extends LinearOpMode {

public static double driveRobot1 = 3;
public static double strafeRobot1 = 1.2;
public static double driveRobot2 = 1;

    @Override
    public void runOpMode() {

        BrainSTEMRobot robot = new BrainSTEMRobot(hardwareMap, telemetry);

        waitForStart();

        Actions.runBlocking(
                new SequentialAction(

                    AutoCommands.driveRobot(robot.drive, 0.5, driveRobot1),
                    AutoCommands.strafeRobot(robot.drive,0.5, strafeRobot1),
                    AutoCommands.setLiftMidPosition(robot.lift),
                    AutoCommands.driveRobot(robot.drive, 0.5, driveRobot2),
                    AutoCommands.setIntakeSpeed(robot.intake, -0.8)

                )
        );
    }
}