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
			String currentFrame;
			while (packetReceiver.length() > 0) {
				try {
					int i = Integer.parseInt(packetReceiver.substring(0, 1));
					currentFrame = packetReceiver.substring(1, i);
					packetSender += currentFrame;
					packetReceiver = packetReceiver.replace(packetReceiver.substring(0, i), "");
				} catch (NumberFormatException e) {
					System.err.println("There is an error.");
					System.exit(0);
				}
			}
		} else if (method.equalsIgnoreCase("byte stuffing")) {
			if (packetReceiver.startsWith(FLAG) && packetReceiver.endsWith(FLAG)) {
				String currentFrame = packetReceiver.substring(1, packetReceiver.length() - 1);
				int countESC = countCharacter(currentFrame, ESC);
				int countFlag = countCharacter(currentFrame, FLAG);
				if ((countESC - countFlag) % 2 == 0) {
					currentFrame = currentFrame.replace("EE", "E");
					currentFrame = currentFrame.replace("EF", "F");
					packetSender = currentFrame;
				} else {
					System.err.println("There is an error");
					System.exit(0);
				}
			} else {
				System.err.println("There is an error");
				System.exit(0);
			}
		} else if (method.equalsIgnoreCase("bit stuffing")) {

		}
		System.out.println("The original packet is: " + packetSender);

	}

	private static int countCharacter(String str, String character) {
		int count = 0;
		for (String c : str.split("(?!^)")) {
			if (c.equals(character)) {
				count++;
			}
		}
		return count;
	}

}
