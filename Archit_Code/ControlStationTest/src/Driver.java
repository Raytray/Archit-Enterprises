import java.util.Random;

public class Driver
{
	static Random generator = new Random();
	private static String randomString()
	{

		char word[] = new char[10];
		for(int i = 0; i < 10; i++)
		{
			word[i] = (char) ((generator.nextInt(26)) + 65);
		}
		return String.valueOf(word);
	}

	public static void main(String[] args)
	{
		String temp = "";
		Robot robot = new Robot();
		System.out.println("robot.isValidMessage(\"STEELE\")\n" + robot.isValidMessage("STEELE"));
		System.out.println("robot.isValidMessage(\"MAFR000000\")\n" + robot.isValidMessage("MAFR000000"));
		System.out.println("robot.isValidMessage(\"MAFR00000\")\n" + robot.isValidMessage("MAFR00000"));
		for(int i = 0; i < 10; i++)
		{
			if(generator.nextInt()%5 == 0)
			{
				temp = randomString();
			}
			System.out.println("robot.isValidMessage(\"" + temp + "\")\n" + robot.isValidMessage(temp));
		}

	}

}
