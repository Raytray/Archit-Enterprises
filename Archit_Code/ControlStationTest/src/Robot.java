import java.util.ArrayList;
import java.util.Random;

public class Robot
{
	private Random generator = new Random();
	private String[] initialMessages = {"MSF0000000", "MSB0000000", "TNL0000000", 
			"TNR0000000", "MAFL000000", "MAFR000000", "MABL000000", "MABR000000",
			"ST00000000", "RS30000000", "RS40000000"};
	private ArrayList<String> messages = new ArrayList<String>();
	private String[] initialErrors;

	
	public Robot()
	{
		for(int i = 0; i < initialMessages.length; i++)
		{
			messages.add(initialMessages[i]);
		}
	}
	
	public Boolean isValidMessage(String message)
	{
//		for(int i = 0; i < initialMessages.length; i++)
//		{
//			if(initialMessages[i].equals(message))
//				return true;
//		}
//		return false;
		return messages.contains(message);
	}
	
	private int randomIndex()
	{
		return generator.nextInt(messages.size());
	}
	
	public String randomMessage()
	{
		return messages.get(randomIndex());
	}
}
