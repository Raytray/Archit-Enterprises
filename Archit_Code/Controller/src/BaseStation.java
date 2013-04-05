import java.util.*;
import javax.swing.*;
import java.awt.*;
import lejos.util.*;

public class BaseStation {
	//public NXTComm connection;
	/*static UltrasonicSensor usensor;
	static LightSensor lsensor;
	static Microphone msensor;
	static TouchSensor tsensor; */
	private int speed;
	
	public BaseStation()
	{
		speed = 0;
		//set up connection to robot
		//need to know bluetooth channel number
		//connection = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
	}
	
	public void setSpeed(int newSpeed)
	{
		speed = newSpeed;
	}
	
	public void moveForward()
	{
		//Encrypt a move forward command and send using bluetooth
		//checksum
		System.out.println("Moving forward at speed " + speed);
		String command = "MSF0000000";
		//connection.sendPacket(command.getBytes(),11);
	}
	
	public void moveForward(int speed)
	{
		speed = this.speed;
		moveForward();
	}
	
	public void moveBackward()
	{
		//Encrypt a move backward command and send using bluetooth
		//checksum
		System.out.println("Moving backward at speed " + speed);
		String command = "MSB0000000";
		//connection.sendPacket(command.getBytes(), 11);
	}
	
	public void moveBackward(int speed)
	{
		speed = this.speed;
		moveBackward();
	}
	
	public void turnLeft()
	{
		//encrypt a turn left command and send using bluetooth
		//checksum
		System.out.println("Turning left...");
		String command = "TNL0000000";
		//connection.sendPacket(command.getBytes(), 11);
	}
	
	public void turnRight()
	{
		//encrypt a turn right command and send using bluetooth
		//checksum
		System.out.println("Turning right...");
		String command = "TNR0000000";
		//connection.sendPacket(command.getBytes(), 11);
	}
	
	public void moveForwardLeft()
	{
		System.out.println("Moving forward-left at speed " + speed);
	}
	
	public void moveForwardRight()
	{
		System.out.println("Moving forward-right at speed " + speed);
	}
	
	public void moveBackwardLeft()
	{
		System.out.println("Moving backward-left at speed " + speed);
	}
	
	public void moveBackwardRight()
	{
		System.out.println("Moving backward-right at speed " + speed);
	}
	
	public void stop()
	{
		//encrypt a stop command and send using bluetooth
		//checksum
		System.out.println("Stopped.");
		String command = "ST00000000";
		//connection.sendPacket(command.getBytes(), 11);
	}
	
	public void turn180()
	{
		System.out.print("\tPlease wait");
		for(int i = 0; i < 5; i++)
		{
			System.out.print(".");
			try 
			{
				Thread.sleep(400);
			} catch (Exception e) 
			{
				break;
			}
		}
		System.out.println("\nTurn complete.");
	}
}
