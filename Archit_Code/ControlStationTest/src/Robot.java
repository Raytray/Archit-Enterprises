import java.util.ArrayList;
import java.util.Random;

public class Robot
{
	private static final int INTERFERENCE_CONSTANT = 6;
	private static final int BYTE_SIZE = 256;
	private static final int MESSAGE_LENGTH = 10;
	private static final int MESSAGE_TYPE_LENGTH = 7;
	private static final int MESSAGE_VALUE_LENGTH = 3;
	private Random rand = new Random();
	private String[] descriptions = {"Move Straight Forward.", "Move Straight Backward.", 
			"Turn left.", "Turn right.", "Move arc forward-left.",
			"Move arc forward-right.", "Move arc backward-left.", "Move arc backward-right.",
			"Stop"};
	private String[] initialMessages = {"MSF0000", "MSB0000", "TNL0000", 
			"TNR0000", "MFL0000", "MFR0000", "MBL0000", "MBR0000",
			"ST00000"};
	private String[] telemetrics = {"RS30000", "RS40000", "RS50000"};
	private String[] telemetricsDescriptions = {"Ultrasonic Sensor", "Light Sensor", "Touch Sensor"};
	private int[] ranges = {720, 720,
			100, 100, 720, 
			720, 720, 720,
			1};
	private int[] sensorRanges = {100, 100, 1};

	private ArrayList<String> messages = new ArrayList<String>();
	private String[] initialErrors = {"Description not found!", "Command not recognized!", "Value out of range!"
			
	};
	private int burst = 0;
	private int lastIndex = 0;

	public Robot()
	{ // initialize the Robot by building the ArrayList of possible messages
		for(int i = 0; i < initialMessages.length; i++)
		{
			messages.add(initialMessages[i]);
		}
	}

	public Boolean isValidMessage(String message)
	{
		int value = -1;
		try
		{ // convert the last three characters of the string, representing the value, into an int
			value = Integer.parseInt(message.substring(MESSAGE_TYPE_LENGTH, MESSAGE_LENGTH - 1));
		}
		catch(Exception e)
		{ // message is not valid if last three characters do not represent a valid string
			return false;
		}
		int messageIndex = messages.lastIndexOf(message.substring(0, MESSAGE_TYPE_LENGTH));
		if(messageIndex > -1 && value >= 0 && value <= ranges[messageIndex])
		{ // return true if value is in range for the given message type
			return true;
		}
		return false; // else return false
	}
	
	public String messageDescription(String message)
	{ // Looks up the description for a given encoded message
		if(isValidMessage(message))
		{
			return descriptions[messages.lastIndexOf(message.substring(0, MESSAGE_TYPE_LENGTH))];
		}
		return "Invalid message.";
	}

	public String corruptString(String message)
	{
		char[] array = message.toCharArray();
		boolean done = false;
		while(!done)
		{
			for(int i = 0; i < message.length(); i++)
			{
				if(rand.nextInt(INTERFERENCE_CONSTANT) == 0)
				{
					array[i] = randomByte();
					done = true;
				}
			}
		}
		return new String(array);
	}

	public char randomChar()
	{
		if(rand.nextInt(2) == 1)
			return (char) ((rand.nextInt(26)) + 'A');
		return (char) ((rand.nextInt(10)) + '0');
	}

	public char randomByte()
	{
		return (char)(rand.nextInt(BYTE_SIZE));
	}

	private int randomIndex()
	{
		return rand.nextInt(messages.size());
	}

	public String sendMessage()
	{
		lastIndex = randomIndex();
		if(burst > 0)
		{
			burst--;
			return corruptString(messages.get(lastIndex) + randomValue(lastIndex));
		}
		else if(rand.nextInt(INTERFERENCE_CONSTANT) == 0)
		{
			if(rand.nextInt(INTERFERENCE_CONSTANT) == 0)
			{
				burst = rand.nextInt(5);
			}
			return corruptString(messages.get(lastIndex) + randomValue(lastIndex));
		}
		return messages.get(lastIndex) + randomValue(lastIndex);
	}
	
	public String resendMessage()
	{
		if(burst > 0)
		{
			burst--;
			return corruptString(messages.get(lastIndex) + randomValue(lastIndex));
		}
		else if(rand.nextInt(INTERFERENCE_CONSTANT) == 0)
		{
			if(rand.nextInt(INTERFERENCE_CONSTANT) == 0)
			{
				burst = rand.nextInt(5);
			}
			return corruptString(messages.get(lastIndex) + randomValue(lastIndex));
		}
		return messages.get(lastIndex) + randomValue(lastIndex);
	}
	
	private String randomValue(int index)
	{
		String value = String.valueOf(rand.nextInt(ranges[index]));
		if(value.length() == 1)
			value = "00" + value;
		else if(value.length() == 2)
			value = "0" + value;
		return value;
	}
	
	public String telemetrics()
	{
		String s = "\tCurrent Telemetrics\n";
		for(int i = 0; i < telemetrics.length; i++)
		{
			s += telemetricsDescriptions[i] + ": " + randomTelemetryValue(i) + "\n";
		}
		return s;
	}
	
	private int randomTelemetryValue(int index)
	{
		return rand.nextInt(sensorRanges[index]+1);
	}
}
