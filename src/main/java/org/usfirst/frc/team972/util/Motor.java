package org.usfirst.frc.team972.util;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.*;

public class Motor {
	WPI_TalonSRX talon;
	Spark spark;
	Victor victor;
	MotorType type;
	
	public Motor(MotorType type, int ID) {
		switch (type) {
		case TALON:
			talon = new WPI_TalonSRX(ID);
			talon.set(ControlMode.PercentOutput, 0);
			break;
		case SPARK:
			spark = new Spark(ID);
			break;
		case VICTOR:
			victor = new Victor(ID);
			break;
		default:
			System.out.println("ERROR! INVALID MOTOR TYPE!");
			break;
		}
		this.type = type;
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
	
	public enum MotorType {
		TALON, SPARK, VICTOR;
		
		public static MotorType getMotorType(String readerOutput) {
			switch (readerOutput) {
			case "TALON":
				return MotorType.TALON;
			case "SPARK":
				return MotorType.SPARK;
			case "VICTOR":
				return MotorType.VICTOR;
			default:
				return null;
			}
		}
	}

}