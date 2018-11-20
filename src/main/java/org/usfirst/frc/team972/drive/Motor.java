package org.usfirst.frc.team972.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.*;

public class Motor {
	WPI_TalonSRX talon;
	Spark spark;
	Victor victor;
	MotorType type;
	
	public Motor(String type, int ID) {
		switch (type) {
		case "CANTalon":
			talon = new WPI_TalonSRX(ID);
			talon.set(ControlMode.PercentOutput, 0);
			this.type = MotorType.TALON;
			break;
		case "Spark":
			spark = new Spark(ID);
			this.type = MotorType.SPARK;
			break;
		case "Victor":
			victor = new Victor(ID);
			this.type = MotorType.VICTOR;
			break;
		default:
			System.out.println("ERROR! INVALID MOTOR TYPE!");
			break;
		}
	}
	
	public void set(double speed) {
		switch (type) {
		case TALON:
			talon.set(speed);
			break;
		case SPARK:
			spark.set(speed);
			break;
		case VICTOR:
			victor.set(speed);
			break;
		}
	}
	
	/**
	 * This method only works on talons
	 */
	public double getOutputCurrent() {
		if (type == MotorType.TALON)
			return talon.getOutputCurrent();
		else
			return -1;
	}
	
	private enum MotorType {
		TALON, SPARK, VICTOR;
	}

}
