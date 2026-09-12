package org.firstinspires.ftc.teamcode.util;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.drivetrain.Drive;

public class AutoCommands {
    public static Action driveRobot(Drive drive, double power, double time) {
        return new SequentialAction(
                packet -> {
                    drive.setDTMotorPowers(power, power, power, power);
                    return false;
                },
                new SleepAction(time),
                packet -> {
                    drive.stop();
                    return false;
                }
        );
    }

    public static Action strafeRobot(Drive drive, double power, double time) {
        return new SequentialAction(
                packet -> {
                    drive.setDTMotorPowers(power, -power, -power, power);
                    return false;
                },
                new SleepAction(time),
                packet -> {
                    drive.stop();
                    return false;
                }
        );
    }

    public static Action turnRobotRight(Drive drive, double power, double time) {
        return new SequentialAction(
                packet -> {
                    drive.setDTMotorPowers(power, -power, power, -power);
                    return false;
                },
                new SleepAction(time),
                packet -> {
                    drive.stop();
                    return false;
                }
        );
    }

    public static Action turnRobotLeft(Drive drive, double power, double time) {
        return new SequentialAction(
                packet -> {
                    drive.setDTMotorPowers(-power, power, -power, power);
                    return false;
                },
                new SleepAction(time),
                packet -> {
                    drive.stop();
                    return false;
                }
        );
    }
    public static Action updateEverything(Lift lift, Intake intake) {
        return
                packet -> {
                    lift.update();
                    return true;
                };
    }

    public static Action setLiftMidPosition(Lift lift) {
        return packet -> {
                    lift.liftState = Lift.LiftState.MID;
                    return false;
                };
    }

    public static Action setLiftDownPosition(Lift lift) {
        return packet -> {
            lift.liftState = Lift.LiftState.DOWN;
            return false;
        };
    }
    public static Action setIntakeSpeed(Intake intake, double power) {
        return new SequentialAction(
                packet -> {
                    intake.setMotorPower(power);
                    return false;
                }
        );
    }
}

