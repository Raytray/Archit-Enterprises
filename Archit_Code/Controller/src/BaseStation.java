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
   
    //verify the checksum that is held at the 11th byte.
    public boolean verifyChecksum(String message) {
	if(message.length() == 11) {
            byte[] string = message.getBytes();
            if(getChecksum(message.substring(0, 10)).equals(message.substring(10))){
                return true;
            }
        }
        return false;
    }

    //Gets the checksum to be sent as the byte.
    private String getChecksum(String message) {
	int sum = 0;
        String ret;
        byte[] buffer = message.getBytes();
        for (int i = 0; i < buffer.length; i++) {
            sum += (int) buffer[i];
        }
        sum = sum % 256;
        byte[] checksum = new byte[1];
        checksum[0] = (byte) sum;
        ret = new String(checksum);
        return ret;
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
