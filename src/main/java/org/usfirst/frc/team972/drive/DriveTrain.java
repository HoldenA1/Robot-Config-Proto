package org.usfirst.frc.team972.drive;

import org.usfirst.frc.team972.util.Motor.MotorType;
import org.usfirst.frc.team972.util.*;

public class DriveTrain {
	Motor[] motors, leftSide, rightSide;
	MotorType motorType;
	int motorCount;
	
	public DriveTrain(XMLReader reader) {
		String motorIDs = reader.parseXML(RobotSettings.DRIVE_MOTOR_IDS);
		motorType = MotorType.getMotorType(reader.parseXML(RobotSettings.DRIVE_MOTOR_TYPE));
		motorCount = Integer.parseInt(reader.parseXML(RobotSettings.DRIVE_MOTOR_COUNT));
		
		motors = new Motor[motorCount];
		rightSide = new Motor[motorCount/2];
		leftSide = new Motor[motorCount/2];
		
		// Initializes the array of motors
		for (int i = 0; i < motorCount; i++) {
			int motorID = Integer.parseInt(motorIDs.substring(i*2, i*2+1));			
			motors[i] = new Motor(motorType, motorID);
		}
	
		// Sets up the left and right sides
		for (int i = 0; i < motorCount/2; i++) {
			leftSide[i] = motors[i];
		}
		for (int i = 0; i < motorCount/2; i++) {
			rightSide[i] = motors[i+motorCount/2];
		}
		
	}
	
	/**
	 * Only use if drive motor type is TALON
	 */
	public void logOutputCurrent() {
		if (motorType == MotorType.TALON) {
			double currentSum = 0;
			for (Motor m: motors) {
				currentSum += m.getOutputCurrent();
			}
			System.out.println("Avg drivetrain current draw: " + currentSum / motorCount);
		} else {
			System.out.println("[Log output current failed] This method only works with a talon_srx drivetrain");
		}
	}
	
	public void driveSides(double leftSideSpeed, double rightSideSpeed) {
		for (Motor l: leftSide) {
			l.set(leftSideSpeed);
		}
		for (Motor r: rightSide) {
			r.set(-rightSideSpeed);
		}
	}

}
