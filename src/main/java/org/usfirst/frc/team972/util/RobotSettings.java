package org.usfirst.frc.team972.util;

public enum RobotSettings {
	// To add a setting just write the setting name, then for parameters put the tag, then the default value
	
	// Drive motor type is either CANTalon, Spark, or Victor
	DRIVE_MOTOR_TYPE("driveMotorType", "CANTalon"),
	DRIVE_MOTOR_COUNT("driveMotorCount", "4"),
	// Drive motor IDs are in order left-1, left-2, ..., right-1, right-2, ...
	DRIVE_MOTOR_IDS("driveMotorIDs", "1,2,3,4"),
	// Drive encoder channels are in order left-portA, left-portB, right-portA, right-portB
	DRIVE_ENCODER_CHANNELS("driveEncoderChannels", "0,1,2,3");
	
	private String tag;
	private String defaultSetting;
	
	private RobotSettings(String tag, String defaultSetting) {
		this.tag = tag;
		this.defaultSetting = defaultSetting;
	}
	
	public String getTag() {
		return this.tag;
	}
	
	public String getDefualt() {
		return this.defaultSetting;
	}
}
