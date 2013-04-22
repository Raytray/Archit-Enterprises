/*
 * Remaining phase 1 inspections:
1. in setSpeed() what are numbers 10 and 100? 
	(I know they're checks for how many digits are in the numbers, but what
	would you guys suggest as a name for a symbolic constant?)

inspection notes:
1. turn180 was removed since it was just a 180-degree turn-right, but the other team
suggested we remove stop() as well. Maybe I'm not familiar with the design specs well
enough, but I don't see how we could get by without it (unless the robot does movements in ~10ms
increments, so stopping the robot would simply involve stopping any movement commands)
 */


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;

public class BaseStation
{
	public NXTComm connection;
	public NXTInfo info;
	public OutputStream os;
	public boolean readFlag;
	public InputStream is;
	public DataOutputStream oHandle;
	public DataInputStream iHandle;
	public String command;
	private int touchValue, ultraSonicValue, lightValue, micValue;
	private final int UPDATE_TIME = 10;
	private final int BYTE_SIZE = 256;
	private final int MESSAGE_LENGTH = 10;

	public BaseStation() throws NXTCommException, IOException
	{
		touchValue = 0;
		ultraSonicValue = 0;
		lightValue = 0;
		micValue = 0;
	}

	public void establishConnection() throws NXTCommException, IOException {
		connection = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
		info = new NXTInfo(NXTCommFactory.BLUETOOTH, "TROGDOR", "00:16:53:13:E6:74"); 
		//info = new NXTInfo(NXTCommFactory.BLUETOOTH, "NXT", "00:16:53:13:D3:FC");

		connection.open(info);
		os = connection.getOutputStream();
		is = connection.getInputStream();
		iHandle = new DataInputStream(is);
		oHandle = new DataOutputStream(os);

		Thread PCreceiver = new Thread()
		{
			public void run()
			{
				byte[] buffer;
				String message;
				int count;
				readFlag = true;
				while (readFlag)
				{
					try
					{
						buffer = new byte[BYTE_SIZE];
						count = iHandle.read(buffer); // TODO check ack later
						if (count > 0)
						{
							message = (new String(buffer)).trim();
							if(verifyChecksum(message))
							{
								switch(message.substring(0,3))
								{
								case "SDT":
									touchValue = Integer.parseInt(message.substring(9,MESSAGE_LENGTH));
									break;
								case "SDU":
									ultraSonicValue = Integer.parseInt(message.substring(3,MESSAGE_LENGTH));
									break;
								case "SDM":
									micValue = Integer.parseInt(message.substring(3,MESSAGE_LENGTH));
									break;
								case "SDL":
									lightValue = Integer.parseInt(message.substring(3,MESSAGE_LENGTH));
									break;
								}
							}
						}
						Thread.sleep(UPDATE_TIME);
					} 
					catch (IOException e) 
					{
						System.out.println("Fail to read from iHandle bc " + e.toString());
						return;
					} 
					catch (InterruptedException e) 
					{
						// TODO empty catch block
					}
				}
			}
		};
		PCreceiver.start();
	}

	public void sendMessage(String message) throws IOException
	{
		oHandle.write(message.getBytes());
		oHandle.flush();
	}

	public void moveForward() throws IOException
	{
		command = "MSF0000000";
		buildCommand();
		sendMessage(command);
	}

	public void moveBackward() throws IOException
	{
		command = "MSB0000000";
		buildCommand();
		sendMessage(command);
	}

	public void turnLeft(int degrees) throws IOException
	{ // TODO implement variability
		if(degrees < 0)
		{
			turnRight(degrees*(-1));
		}
		else if(degrees > 0)
		{
			command = "TNL0000000";
			buildCommand(degrees);
			sendMessage(command);
		}
	}

	public void turnRight(int degrees) throws IOException
	{ // TODO implement variability
		if(degrees < 0)
		{
			turnLeft(degrees*(-1));
		}
		else if(degrees > 0)
		{
			command = "TNR0000000";
			buildCommand(degrees);
			sendMessage(command);
		}
	}

	public void moveForwardLeft()
	{
		// TODO implement
	}

	public void moveForwardRight()
	{
		// TODO implement
	}

	public void moveBackwardLeft()
	{
		// TODO implement
	}

	public void moveBackwardRight()
	{
		// TODO implement
	}

	public void stop() throws IOException
	{
		command = "ST00000000";
		buildCommand();
		sendMessage(command);
	}

	public void getTouchSensor() throws IOException
	{
		command ="RST0000000";
		buildCommand();
		sendMessage(command);
	}

	public boolean getTouchValue()
	{
		if(touchValue == 1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private String getChecksum(String message) 
	{
		int sum = 0;
		byte[] checksum = new byte[1];
		String checksumString;
		byte[] buffer = message.getBytes();
		for (int i = 0; i < buffer.length; i++) 
		{
			sum += (int) buffer[i];
		}
		sum = sum % BYTE_SIZE;
		checksum[0] = (byte) sum;
		checksumString = new String(checksum);
		return checksumString;
	}

	public boolean verifyChecksum(String message) 
	{
		if(message.length() == 11) 
		{
			if(getChecksum(message.substring(0, MESSAGE_LENGTH))
					.equals(message.substring(MESSAGE_LENGTH)))
			{
				return true;
			}
		}
		return false;
	}

	public void getMicrophoneSensor() throws IOException 
	{
		command ="RSM0000000";
		buildCommand();
		sendMessage(command);
	}

	public void getLightSensor() throws IOException 
	{
		command ="RSL0000000";
		buildCommand();
		sendMessage(command);
	}

	public void getUltraSensor() throws IOException 
	{
		command ="RSU0000000";
		buildCommand();
		sendMessage(command);
	}

	public void readSensors() throws IOException 
	{
		command = "RA00000000";
		buildCommand();
		sendMessage(command);
	}

	public int getMicrophoneValue()
	{
		return micValue;
	}

	public int getLightValue()
	{
		return lightValue;
	}

	public int getUltrasonicValue()
	{
		return ultraSonicValue;
	}

	public void exitRobot() throws IOException
	{
		command = "ECE0000000";
		buildCommand();
		oHandle.close();
		iHandle.close();
		os.close();
		is.close();
		connection.close();
		readFlag = false;
		sendMessage(command);
	}

	public void setSpeed(int newSpeed) throws IOException
	{
		if(newSpeed < 10)
		{
			command = "SSDT00000" + Integer.toString(newSpeed);
		}
		else if(newSpeed < 100)
		{
			command = "SSDT0000" + Integer.toString(newSpeed);
		}
		else
		{
			command = "SSDT000" + Integer.toString(newSpeed);
		}
		command = command + getChecksum(command);
		sendMessage(command);
	}

	private void buildCommand()
	{
		buildCommand(0);
	}

	private void buildCommand(int value)
	{ // TODO check for proper function
		if(value < 10)
		{
			command = command.substring(0,MESSAGE_LENGTH-1);
		}
		else if(value < 100)
		{
			command = command.substring(0,MESSAGE_LENGTH-2);
		}
		else if(value < 1000)
		{
			command = command.substring(0,MESSAGE_LENGTH-3);
		}
		command = command + Integer.toString(value);
		command = command + getChecksum(command);
	}
}