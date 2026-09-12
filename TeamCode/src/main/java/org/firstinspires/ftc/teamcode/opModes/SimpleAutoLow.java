package org.firstinspires.ftc.teamcode.opModes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.util.AutoCommands;
import org.firstinspires.ftc.teamcode.BrainSTEMRobot;
@Config
@Autonomous (name = "Simple Auto Low - Blue ")
public class SimpleAutoLow extends LinearOpMode {


    public static double boxAlignTime1 = 0.6;
    public static double boxAlignTime2 = 0.4;
    public static double driveTime1 = -0.1;
    public static double driveTimeB = 3;
    public static double driveTimeF = 3.5;
    public static double strafeTimeL = 0.6;
    public static double strafeTimeR = 0.5;
    public static double turnTime1 = 0.2;
    public static double turnTimeL = 0.4;
    public static double turnTimeR = 0.5;

    @Override
    public void runOpMode() {

        BrainSTEMRobot robot = new BrainSTEMRobot(hardwareMap, telemetry);

        waitForStart();

        Actions.runBlocking(
                new ParallelAction(
                        AutoCommands.updateEverything(robot.lift, robot.intake),
                        new SequentialAction(
                                //going out the first time
                                AutoCommands.driveRobot(robot.drive, -0.5, driveTime1),
                                AutoCommands.turnRobotLeft(robot.drive,0.5, turnTime1),
                                AutoCommands.driveRobot(robot.drive, 0.5, driveTimeF),
                                AutoCommands.turnRobotRight(robot.drive,0.5,turnTimeR),
                                AutoCommands.strafeRobot(robot.drive, -0.5, strafeTimeL),
                                AutoCommands.driveRobot(robot.drive, 0.5 , boxAlignTime1),
                                AutoCommands.setLiftMidPosition(robot.lift),
                                new SleepAction(1),
                                AutoCommands.driveRobot(robot.drive, 0.5, boxAlignTime2),
                                AutoCommands.setIntakeSpeed(robot.intake, -0.5),
                                new SleepAction(5),
                                AutoCommands.driveRobot(robot.drive,-0.5, 0.1),
                                AutoCommands.setLiftDownPosition(robot.lift)
                        )


                        //going back to home
                        //AutoCommands.turnRobotLeft(robot.drive,0.5,turnTimeL),
//                        AutoCommands.driveRobot(robot.drive,-0.5,driveTimeB),
//                        AutoCommands.strafeRobot(robot.drive,0.5,strafeTimeR),
//                        AutoCommands.setIntakeSpeed(robot.intake,0.5),
//
//                        //going out the second time
//                        AutoCommands.strafeRobot(robot.drive, -0.5, strafeTimeL),
//                        AutoCommands.driveRobot(robot.drive, 0.5, driveTimeF),
//                        AutoCommands.turnRobotRight(robot.drive,0.5,turnTimeR),
//                        AutoCommands.setLiftPosition(robot.lift, Lift.LiftState.UP),
//                        AutoCommands.setIntakeSpeed(robot.intake, -0.5),
//                        //wait a few seconds and stop intake
//                        AutoCommands.setLiftPosition(robot.lift, Lift.LiftState.DOWN)

                )
        );
    }
}