import java.util.Scanner;

public class Sender {
	
	public static void main(String[] args) {
		String[] payload;
		final char FLAG = 'F';
		final char ESC = 'E';
		Scanner input = new Scanner(System.in);
		System.out.println("Would you like to use byte count, byte stuffing, or bit stuffing");
		String job = input.nextLine();
		while (!job.equalsIgnoreCase("byte count") && !job.equalsIgnoreCase("byte stuffing")
				&& !job.equalsIgnoreCase("bit stuffing")) {
			System.out.println("Type one of the following to choose: \n'byte count'\n'byte stuffing'\n'bit stuffing'");
			job = input.nextLine();
		}
		System.out.println("Enter a string to transmit");
		String packet = input.nextLine();
		payload = packet.split("(?!^)");
		
	}

}
