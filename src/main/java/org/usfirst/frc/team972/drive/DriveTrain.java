package org.usfirst.frc.team972.drive;

import org.usfirst.frc.team972.util.*;

public class DriveTrain {
	Motor[] motors, leftSide, rightSide;
	
	public DriveTrain(XMLReader reader) {
		String motorIDs = reader.parseXML(RobotSettings.DRIVE_MOTOR_IDS);
		String motorType = reader.parseXML(RobotSettings.DRIVE_MOTOR_TYPE);
		int motorCount = Integer.parseInt(reader.parseXML(RobotSettings.DRIVE_MOTOR_COUNT));
		
		motors = new Motor[motorCount];
		rightSide = new Motor[motorCount/2];
		leftSide = new Motor[motorCount/2];
		
		// Initializes the array of motors correctly
		for (int i = 0; i < motors.length; i++) {
			int motorID = Integer.parseInt(motorIDs.substring(i*2, i*2+1));			
			motors[i] = new Motor(motorType, motorID);
		}
	
		// Sets up the left and right sides
		for (int i = 0; i < motors.length/2; i++) {
			leftSide[i] = motors[i];
		}
		for (int i = motors.length/2; i < motors.length; i++) {
			rightSide[i] = motors[i];
		}
		
	}
	
	public void driveSides(double leftSideSpeed, double rightSideSpeed) {
		for (Motor l: leftSide) {
			l.set(leftSideSpeed);
		}
		for (Motor r: rightSide) {
			r.set(rightSideSpeed);
		}
	}

}
