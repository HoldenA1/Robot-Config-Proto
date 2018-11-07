package org.usfirst.frc.team972;

import org.usfirst.frc.team972.drive.*;
import org.usfirst.frc.team972.util.*;
import edu.wpi.first.wpilibj.*;

public class Robot extends IterativeRobot {
	static DriveTrain driveTrain;
	
	public void robotInit() {
		XMLReader reader = new XMLReader("robotConfig");
		driveTrain = new DriveTrain(reader);
	}
	
	public void teleopPeriodic() {
		driveTrain.driveSides(0.5, 0.5);
	}

}
