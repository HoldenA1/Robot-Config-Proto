package frc.robot.util;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
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
			Logger.logError("Invalid Motor Type");
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
	
	/**
	 * This method only works on talons
	 */
	public void setFeedbackSensor() {
		if (type == MotorType.TALON)
			talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 100);
	}
	
	/**
	 * This method only works on talons
	 */
	public double getIntegratedEncoderPos() {
		if (type == MotorType.TALON)
			return talon.getSensorCollection().getQuadraturePosition();
		else {
			Logger.logError("getIntegratedEncoderPos only works with talons");
			return -1;
		}
	}
	
	/**
	 * This method only works on talons
	 */
	public void resetIntegratedEncoder() {
		if (type == MotorType.TALON)
			talon.getSensorCollection().setQuadraturePosition(0, 100);
		else
			Logger.logError("getIntegratedEncoderPos only works with talons");
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
				Logger.logError("Invalid motor type");
				return null;
			}
		}
	}

}