package org.firstinspires.ftc.teamcode.opModes;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.BrainSTEMRobot;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.util.DriveToPoint;
import org.firstinspires.ftc.teamcode.util.AutoCommands;
@Autonomous (name = "Pinpoint Auto - Blue")
public class PinpointAuto extends LinearOpMode {

    @Override
    public void runOpMode() {
        Pose2d startPose = new Pose2d(new Vector2d(60, 60), Math.toRadians(180));

        BrainSTEMRobot robot = new BrainSTEMRobot(hardwareMap, telemetry);

        waitForStart();

        robot.drive.setPose(startPose);

        Actions.runBlocking(
                new SequentialAction(
                        new DriveToPoint(robot.drive, telemetry, -60, 30, Math.toRadians(180)),
                        new DriveToPoint(robot.drive, telemetry, -60, 45, Math.toRadians(90)),
                        //AutoCommands.setLiftPosition(robot.lift, Lift.LiftState.UP),
                        AutoCommands.setIntakeSpeed(robot.intake, -0.8),
                        new DriveToPoint(robot.drive, telemetry, -60,48,Math.toRadians(90))

                        //possible second intake
                        //new DriveToPoint(robot.drive, telemetry, 0,0, Math.toRadians(0)),
                        //new AutoCommands.setIntakeSpeed(robot.intake, 0.8),
                        //
                        //new DriveToPoint(robot.drive, telemetry, -60, 45, Math.toRadians(90)),
                        //new AutoCommands.setLiftPosition(robot.lift, Lift.LiftState.UP),
                        //new AutoCommands.setIntakeSpeed(robot.intake, -0.8),
                        //new DriveToPoint(robot.drive, telemetry, -60,48,Math.toRadians(90))


                        //Hans code
                        //new DriveToPoint(robot.drive, telemetry, 24,0, Math.toRadians(180)),
                        //new DriveToPoint(robot.drive, telemetry, 24 ,12, Math.toRadians(180)),
                        //new DriveToPoint(robot.drive, telemetry, 112, 12, Math.toRadians(90)),
                        //new DriveToPoint(robot.drive, telemetry, 112 , -6, Math.toRadians(0))\

                )
        );
    }
}