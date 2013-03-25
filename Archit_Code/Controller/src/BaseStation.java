import java.util.*;
import javax.swing.*;
import java.awt.*;
import lejos.nxt.comm.*;

public class BaseStation {
	public BTConnection connection;
	/*static UltrasonicSensor usensor;
	static LightSensor lsensor;
	static Microphone msensor;
	static TouchSensor tsensor; */
	
	public BaseStation()
	{
		//set up connection to robot
		//need to know bluetooth channel number
		connection = Bluetooth.waitForConnection();
	}
	
	public void moveForward()
	{
		//Encrypt a move forward command and send using bluetooth
		//checksum
		String command = "MSF0000000";
		//connection.sendPacket(command.getBytes(),11);
	}
	
	public void moveBackward()
	{
		//Encrypt a move backward command and send using bluetooth
		//checksum
		String command = "MSB0000000";
		//connection.sendPacket(command.getBytes(), 11);
	}
	
	public void turnLeft()
	{
		//encrypt a turn left command and send using bluetooth
		//checksum
		String command = "TNL0000000";
		//connection.sendPacket(command.getBytes(), 11);
	}
	
	public void turnRight()
	{
		//encrypt a turn right command and send using bluetooth
		//checksum
		String command = "TNR0000000";
		//connection.sendPacket(command.getBytes(), 11);
	}
	
	public void stop()
	{
		//encrypt a stop command and send using bluetooth
		//checksum
		String command = "ST00000000";
		//connection.sendPacket(command.getBytes(), 11);
	}
}
