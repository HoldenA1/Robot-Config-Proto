package frc.robot;

import edu.wpi.first.wpilibj.*;
import frc.robot.drive.*;
import frc.robot.util.*;

public class Robot extends TimedRobot {
  final int JOYSTICK_PORT = 0;
	
	DriveTrain driveTrain;
	Joystick joystick = new Joystick(JOYSTICK_PORT);


  @Override
  public void robotInit() {
    XMLReader reader = new XMLReader("robotConfig");
		driveTrain = new DriveTrain(reader);
  }

  @Override
  public void teleopPeriodic() {
    double leftSpeed = joystick.getRawAxis(1);
		double rightSpeed = joystick.getRawAxis(5);
		
		driveTrain.driveSides(leftSpeed, rightSpeed);
  }

  @Override
  public void testPeriodic() {
  }
}
