import java.util.Scanner;

public class Sender {

	public static void main(String[] args) {
		String[] payload;
		final char FLAG = 'F';
		final char ESC = 'E';
		Scanner input = new Scanner(System.in);
		System.out.println("Choose either byte count, byte stuffing, or bit stuffing.");
		String method = input.nextLine();
		while (!method.equalsIgnoreCase("byte count") && !method.equalsIgnoreCase("byte stuffing")
				&& !method.equalsIgnoreCase("bit stuffing")) {
			System.out.println("Type one of the following to choose: \n'byte count'\n'byte stuffing'\n'bit stuffing'");
			method = input.nextLine();
		}
		System.out.println("Enter a string to transmit");
		String packetReceiver = "";
		String packetSender = input.nextLine();
		payload = packetSender.split("(?!^)");
		if (method.equalsIgnoreCase("byte stuffing")) {
			packetReceiver += "F";
			for (String str : payload) {
				switch (str) {
				case "F":
					packetReceiver += "EF";
					break;
				case "E":
					packetReceiver += "EE";
					break;
				default:
					packetReceiver += str;
				}
			}
			packetReceiver += "F";
		} else if (method.equalsIgnoreCase("bit stuffing")) {
			
			packetReceiver += "01111110";
			for (String str : payload) {
				switch(str) {
				
				}
			}
		}
		System.out.println("The resulting packet is: " + packetReceiver);

	}

}
