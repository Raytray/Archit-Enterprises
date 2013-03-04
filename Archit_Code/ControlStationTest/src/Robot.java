import java.util.ArrayList;
import java.util.Random;

public class Robot
{
	private Random rand = new Random();
	private String[] descriptions = {"Move Straight Forward.", "Move Straight Backward.", 
			"Turn left.", "Turn right.", "Move arc forward-left.",
			"Move arc forward-right.", "Move arc backward-left.", "Move arc backward-right.",
			"Stationary.", "Ultrasonic sensor.", "Light sensor."};
	private String[] initialMessages = {"MSF0000000", "MSB0000000", "TNL0000000", 
			"TNR0000000", "MAFL000000", "MAFR000000", "MABL000000", "MABR000000",
			"ST00000000", "RS30000000", "RS40000000"};
	private int[] ranges = {0, 720, 
			0, 720,
			0, 0, 0, 0, 0,
			0, 0, 0};

	private ArrayList<String> messages = new ArrayList<String>();
	private String[] initialErrors = {"Command not recognized!", "Value out of range!"
			
	};
	private int burst = 0;

	public Robot()
	{
		for(int i = 0; i < initialMessages.length; i++)
		{
			messages.add(initialMessages[i]);
		}
	}

	public Boolean isValidMessage(String message)
	{
		return messages.contains(message);
	}

	public String alterString(String message)
	{
		char[] array = message.toCharArray();
		boolean done = false;
		while(!done)
		{
			for(int i = 0; i < message.length(); i++)
			{
				if(rand.nextInt(7) == 0)
				{
					array[i] = randomChar();
					done = true;
				}
			}
		}
		return new String(array);
	}

	public String corruptString(String message)
	{
		char[] array = message.toCharArray();
		boolean done = false;
		while(!done)
		{
			for(int i = 0; i < message.length(); i++)
			{
				if(rand.nextInt(5) == 0)
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
			return (char) ((rand.nextInt(26)) + 65);
		return (char) ((rand.nextInt(10)) + 48);
	}

	public char randomByte()
	{
		return (char)(rand.nextInt(256));
	}

	private int randomIndex()
	{
		return rand.nextInt(messages.size());
	}

	public String sendMessage()
	{
		if(burst > 0)
		{
			burst--;
			return corruptString(messages.get(randomIndex()));
		}
		else if(rand.nextInt(10) == 0)
		{
			if(rand.nextInt(8) == 0)
			{
				burst = rand.nextInt(5);
			}
			return corruptString(messages.get(randomIndex()));
		}
		return messages.get(randomIndex());
	}
}
