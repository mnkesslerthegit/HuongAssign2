import java.util.Scanner;

public class Main {

	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		PhoneBook book;

		while (true) {
			System.out.print("Please enter file location for phone book");
			String location = scan.next();
			try {
				book = new PhoneBook(location);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.print("Bad file location: please try again");
				continue;
			}
			break;
		}

		int choice = 5;

		// main loop
		do {

			System.out.print("Enter a number to choose an option: " + "\n 1: Insert a new customer"
					+ "\n 2: Delete an existing customer" + "\n 3: Print the phone book"
					+ "\n 4: search for customers by first or last name" + "\n 5: Quit");
			try {
				choice = scan.nextInt();
			} catch (Exception e) {
				System.out.println("\n + Bad choice number; try again. + \n");
				e.printStackTrace();
				continue;
			}

			switch (choice) {
			case 1:
				String[] info = getCustomerInfo();
				book.insert(info[0], info[1], info[2]);
				break;
			case 2:
				info = getCustomerInfo();
				book.delete(info[0],info[1], info[2]);
				break;
			case 3:
				book.print();
				break;
				
			case 4:
				String[] search = getSearchQuery();
				book.search(search[0],search[1],search[2]);
				break;

			}

		} while (choice != 5);

	}

	/**
	 * 
	 * @return gets three strings which reperesent one entry for the phone book
	 */
	private static String[] getCustomerInfo() {

		while (true) {
			String[] result = new String[3];
			System.out.println("Enter first name");
			result[1] = scan.next();

			if (!checkAlphabetitcal(result[1])) {
				System.out.println("Bad first name, try again");
				continue;
			}
			System.out.println("Enter last name");
			result[0] = scan.next();

			if (!checkAlphabetitcal(result[0])) {
				System.out.println("Bad last name, try again");
				continue;
			}

			System.out.println("Enter phone number");
			result[2] = scan.next();

			if (!checkNumeric(result[2])) {
				System.out.println("Bad phone number, try again");
				continue;
			}
			
			return result;

		}

	}

	/**
	 * 
	 * @param str
	 * @return Returns true if the string parameter is composed only of
	 *         alphabetical characters, or else false
	 */
	private static boolean checkAlphabetitcal(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isAlphabetic(str.charAt(i))) {
				return false;
			}

		}
		return true;
	}

	/**
	 * 
	 * @param str
	 * @return Returns true if the string parameter is composed only of numbers,
	 *         or else false
	 */
	private static boolean checkNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}

		}
		return true;
	}

	private static String[] getSearchQuery() {
		// TODO Auto-generated method stub
		return null;
	}

}
