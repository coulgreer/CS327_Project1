import java.util.Scanner;

public class Receiver {

	public static void main(String[] args) {
		String[] payload;
		final String FLAG = "F";
		final String ESC = "E";
		Scanner input = new Scanner(System.in);
		System.out.println("Choose either byte count, byte stuffing, or bit stuffing.");
		String method = input.nextLine();
		while (!method.equalsIgnoreCase("byte count") && !method.equalsIgnoreCase("byte stuffing")
				&& !method.equalsIgnoreCase("bit stuffing")) {
			System.out.println("Type one of the following to choose: \n'byte count'\n'byte stuffing'\n'bit stuffing'");
			method = input.nextLine();
		}
		System.out.println("Enter a string to transmit: ");
		String packetReceiver = input.nextLine();
		String packetSender = "";
		payload = packetReceiver.split("(?!^)");
		if (method.equalsIgnoreCase("byte count")) {
			
		} else if (method.equalsIgnoreCase("byte stuffing")) {
			
		} else if (method.equalsIgnoreCase("bit stuffing")) {
			
		}
		System.out.println("The received packet is: " + packetSender);

	}

}
