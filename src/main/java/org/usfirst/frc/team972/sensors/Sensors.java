package org.usfirst.frc.team972.sensors;

import org.usfirst.frc.team972.util.RobotSettings;
import org.usfirst.frc.team972.util.XMLReader;
import edu.wpi.first.wpilibj.Encoder;

public class Sensors {
	// For now this number is a static two
	final int DRIVE_ENCODER_COUNT = 2;
	// Left is the first and right is the second
	Encoder[] driveEncoders;
	
	public void setUpDriveEncoders(XMLReader reader) {
		driveEncoders = new Encoder[DRIVE_ENCODER_COUNT];
		
		String encoderChannels = reader.parseXML(RobotSettings.DRIVE_ENCODER_CHANNELS);
		
		// Sets up the encoders
		for (int i = 0; i < DRIVE_ENCODER_COUNT; i++) {
			int channelA = Integer.parseInt(encoderChannels.substring(i*2, i*2+1));
			int channelB = Integer.parseInt(encoderChannels.substring((i+1)*2, (i+1)*2+1));
			driveEncoders[i] = new Encoder(channelA, channelB);
		}
	}
	
	/**
	 * @return the left drive encoder object
	 */
	public Encoder getLeftDriveEncoder() {
		return driveEncoders[0];
	}
	
	/**
	 * @return the right drive encoder object
	 */
	public Encoder getRightDriveEncoder() {
		return driveEncoders[1];
	}

}
