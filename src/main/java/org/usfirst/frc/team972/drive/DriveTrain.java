package org.usfirst.frc.team972.drive;

import org.usfirst.frc.team972.util.*;
import org.usfirst.frc.team972.util.Motor.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain {
	Motor[] motors, leftSide, rightSide;
	
	public DriveTrain(XMLReader reader) {
		String motorIDs = reader.parseXML(RobotSettings.DRIVE_MOTOR_IDS);
		MotorType motorType = MotorType.getMotorType(reader.parseXML(RobotSettings.DRIVE_MOTOR_TYPE));
		int motorCount = Integer.parseInt(reader.parseXML(RobotSettings.DRIVE_MOTOR_COUNT));
		
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
		double currentSum = 0;
		for (int i = 0; i < motors.length; i++) {
			currentSum += motors[i].getOutputCurrent();
		}
		SmartDashboard.putNumber("avg drivetrain current draw", currentSum/motors.length);
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
