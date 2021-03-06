package org.usfirst.frc.team972;

import org.usfirst.frc.team972.drive.*;
import org.usfirst.frc.team972.util.*;
import edu.wpi.first.wpilibj.*;

public class Robot extends TimedRobot {
	final int JOYSTICK_PORT = 0;
	
	DriveTrain driveTrain;
	Joystick joystick = new Joystick(JOYSTICK_PORT);
	
	public void robotInit() {
		XMLReader reader = new XMLReader("robotConfig");
		
		driveTrain = new DriveTrain(reader);
	}
	
	public void teleopPeriodic() {
		double leftSpeed = joystick.getRawAxis(1);
		double rightSpeed = joystick.getRawAxis(5);
		
		driveTrain.driveSides(leftSpeed, rightSpeed);
	}

}