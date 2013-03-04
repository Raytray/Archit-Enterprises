import java.util.Random;

public class Driver
{
	static Random rand = new Random();
	private static String randomAlphanumericString()
	{
		char word[] = new char[10];
		for(int i = 0; i < 10; i++)
		{
			if(rand.nextInt(2) == 1)
				word[i] = (char) ((rand.nextInt(26)) + 65);
			else
				word[i] = (char) ((rand.nextInt(10)) + 48);
		}
		return new String(word);
	}
	private static String randomString()
	{
		char word[] = new char[10];
		for(int i = 0; i < 10; i++)
		{
				word[i] = (char)(rand.nextInt(256));
		}
		return new String(word);
	}

	public static void main(String[] args) throws InterruptedException
	{
		String temp = "";
		Robot robot = new Robot();
		for(int i = 0; i < 100; i++)
		{
			System.out.println(robot.sendMessage());
		}
	}

}
