import java.util.Scanner;

public class Main {

	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		
		PhoneBook book;

		while (true) {
			System.out.print("Please enter file location for phone book");
			String location = scan.nextLine();
			try {
				book = new PhoneBook(location);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.print("Bad file location: please try again");
				continue;
			}
			break;
		}

		int choice = 0;

		// main loop
		do {

			System.out.print("Enter a number to choose an option: " + "\n 1: Insert a new customer"
					+ "\n 2: Delete an existing customer" + "\n 3: Print the phone book"
					+ "\n 4: search for customers by first or last name" + "\n 5: Quit + \n");
			try {
				choice = scan.nextInt();
				scan.nextLine();
			} catch (Exception e) {
				System.out.println("\n Bad choice number; try again. \n");
				// e.printStackTrace();
				scan.nextLine();
				continue;
			}

			switch (choice) {
			case 1:
				String[] info = PhoneBook.getCustomerInfo();
				if (book.insert(info[0], info[1], info[2])) {

				} else {
					System.out.println("Failed to insert: duplicate number");
				}
				break;
			case 2:
				info = PhoneBook.getCustomerInfo();
				if (!book.delete(info[0], info[1], info[2])) {
					System.out.println("Failed to remove anything");
				}
				break;
			case 3:
				book.print();
				break;

			case 4:
				String[] search = PhoneBook.getSearchQuery();
				book.search(search[0], search[1]);
				break;

			}

		} while (choice != 5);
		book.quit();

	}

	

}
