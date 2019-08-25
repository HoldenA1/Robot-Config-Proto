package frc.robot.util;

public enum RobotSettings {
	// To add a setting just write the setting name, then for parameters put the tag, then the default value
	
	// Drive motor type is either TALON, SPARK, or VICTOR
	DRIVE_MOTOR_TYPE("driveMotorType", "TALON"),
	DRIVE_MOTOR_COUNT("driveMotorCount", "4"),
	// Drive motor IDs are in order left-1,left-2,...,right-1,right-2,...
	DRIVE_MOTOR_IDS("driveMotorIDs", "1,2,3,4"),
	// Drive encoder type is either INTEGRATED (Only works with a Talon drive train), NORMAL, or NONE (count and channels don't matter if you select none)
	DRIVE_ENCODER_TYPE("driveEncoderType", "NONE"),
	DRIVE_ENCODER_COUNT("driveEncoderCount", ""),
	/* Drive encoder channels are either: 
	* For NORMAL: left-1-portA,left-1-portB,left-2-portA,left-2-portB,...,right-1-portA,right-1-portB,...
	* For INTEGRATED: Leave blank. If there are fewer encoders than drive motors,
	make sure they are plugged into left-1 and right-1 */
	DRIVE_ENCODER_CHANNELS("driveEncoderChannels", "");
	
	/* Example of how to add your own motors
	EXAMPLE_MOTOR_TYPE("exampleMotorType", "TALON, SPARK, or VICTOR"),
	EXAMPLE_MOTOR_ID("exampleMotorIDs", "Separate numbers with commas (i.e. 1,2,3)");
	*/
	
	
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
