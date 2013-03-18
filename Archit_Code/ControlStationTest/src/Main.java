import java.util.Random;

public class Main
{
	static Random rand = new Random();
	private static final int RESEND_ATTEMPT_LIMIT = 3;

	public static void main(String[] args) throws InterruptedException
	{
		String temp = "";
		String description;
		int attempts = 0;
		boolean resend = false;
		Robot robot = new Robot();
		for(int i = 0; i < 30; i++)
		{
			if(!resend)
			{
				System.out.println("\tAwaiting message...");
				temp = robot.sendMessage();
			}
			else
			{
				System.out.println("\tAwaiting resend...");
				temp = robot.resendMessage();
			}
			System.out.println("Message received: " + temp);
			description = robot.messageDescription(temp);
			if(description.equals("Invalid message."))
			{
				if(attempts >= RESEND_ATTEMPT_LIMIT)
				{
					System.out.println("Error could not be corrected. Skipping message.\n");
					resend = false;
					attempts = 0;
					continue;
				}
				resend = true;
				attempts++;
			}
			else
			{
				resend = false;
				attempts = 0;
			}
			if(!resend)
				System.out.println(description + "\n" + robot.telemetrics());
		}
		System.out.println("Done.");
	}

}
