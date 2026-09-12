package org.firstinspires.ftc.teamcode.opModes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.BrainSTEMRobot;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.util.AutoCommands;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp")

@Config


public class TeleOp extends LinearOpMode {



    public static double intakePower = 1;
    public static double boxPower = 0.75;

    private BrainSTEMRobot robot;


    boolean on = false;



    @Override
    public void runOpMode() {

        robot = new BrainSTEMRobot(hardwareMap, telemetry);
        //robot.intake.stopMotor();
        waitForStart();

        while (opModeIsActive()) {

            updateDriver1();
            updateDriver2();

            telemetry.update();
            robot.lift.update();
        }
    }

    public void updateDriver1() {
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;


        double scale = 1.0;
        if (gamepad1.right_trigger > 0.1) {
            scale = 0.6;
        } else {
            scale = 1.0;
        }
        robot.drive.setDrivePowers(y, x, rx, scale);

        telemetry.addData("scale", scale);
        telemetry.addData("y-axis :", y);
        telemetry.addData("x-axis :", x);
        telemetry.addData("turn :", rx);
        telemetry.addData("lift encoder:",robot.lift.getMotorPosition());
    }
    public void updateDriver2() {
        /*
//        double y = -gamepad2.left_stick_y;
        if (gamepad2.dpadUpWasPressed()) {
            robot.lift.liftState = Lift.LiftState.UP;
        }
        if (gamepad2.dpadDownWasPressed()) {
            robot.lift.liftState = Lift.LiftState.DOWN;
        }

        if (gamepad2.dpadLeftWasPressed()){
            robot.lift.liftState = Lift.LiftState.MID;
        }

        if (gamepad2.aWasPressed()){
            robot.intake.setMotorPower(intakePower);
        } else if (gamepad2.yWasPressed()) {
            robot.intake.setMotorPower(-intakePower);
        } else if (gamepad2.bWasPressed()) {
            robot.intake.stopMotor();
        }

         */
    }
}