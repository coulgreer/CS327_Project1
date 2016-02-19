import java.util.Scanner;

public class Sender {

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
		String packetReceiver = "";
		String packetSender = input.nextLine();
		payload = packetSender.split("(?!^)");
		if (method.equalsIgnoreCase("byte count")) {
			if (packetSender.length() < 9) {
				packetReceiver = (packetSender.length() + 1) + packetSender;
			} else {
				while (packetSender.length() > 0) {
					if (packetSender.length() > 8) {
						packetReceiver += 8 + packetSender.substring(0, 7);
						packetSender = packetSender.replaceFirst(packetSender.substring(0, 7), "");
					} else {
						packetReceiver += (packetSender.length() + 1) + packetSender;
						packetSender = packetSender.replace(packetSender, "");
					}
				}
			}
		} else if (method.equalsIgnoreCase("byte stuffing")) {
			packetReceiver += FLAG;
			for (String str : payload) {
				switch (str) {
				case FLAG:
					packetReceiver += ESC + FLAG;
					break;
				case ESC:
					packetReceiver += ESC + ESC;
					break;
				default:
					packetReceiver += str;
					break;
				}
			}
			packetReceiver += FLAG;
		} else if (method.equalsIgnoreCase("bit stuffing")) {
			String regex = "[0-1]+";
			while (!packetSender.matches(regex)) {
				System.out.println("Enter a string with only 1s and 0s: ");
				packetSender = input.nextLine();
			}
			packetReceiver += "01111110";
			while (packetSender.contains("111111")) {
				packetSender = packetSender.replace("111111", "1111101");
			}
			packetReceiver += packetSender;
			packetReceiver += "01111110";
		}
		System.out.println("The sent packet is: " + packetReceiver);

	}

}
