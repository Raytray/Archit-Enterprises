
public class Driver
{
	private static String randomGenerator()
	{
		
		return "0";
	}
	
	public static void main(String[] args)
	{
		Robot robot = new Robot();
		System.out.println("robot.isValidMessage(\"STEELE\")\n" + robot.isValidMessage("STEELE"));
		System.out.println("robot.isValidMessage(\"MAFR000000\")\n" + robot.isValidMessage("MAFR000000"));
		System.out.println("robot.isValidMessage(\"MAFR00000\")\n" + robot.isValidMessage("MAFR00000"));

	}

}
