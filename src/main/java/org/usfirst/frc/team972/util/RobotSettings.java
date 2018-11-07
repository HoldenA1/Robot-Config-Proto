package org.usfirst.frc.team972.util;

public enum RobotSettings {
	// To add a setting just write the setting name, then for parameters put the tag, then the default value
	DRIVE_MOTOR_TYPE("driveMotorType", "Talon"),
	DRIVE_MOTOR_COUNT("driveMotorCount", "4"),
	DRIVE_MOTOR_IDS("driveMotorIDs", "1,2,3,4");
	
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
