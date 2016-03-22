import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PhoneBook {

	private static Scanner scan = new Scanner(System.in);
	private ListDT<Entry> myList;
	private String fileName;

	public PhoneBook(String file) {
		myList = new ListDT<Entry>();
		fileName = file;
		String line = null;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			/**
			 * Read in strings from text file.
			 */
			while ((line = bufferedReader.readLine()) != null) {
				String[] lineData = line.split(" ");

				if (lineData.length == 3) {
					// check if the line data is a valid entry
					if (checkAlphabetitcal(lineData[0]) && checkAlphabetitcal(lineData[1])
							&& checkNumeric(lineData[2])) {
						// if it is, create an entry, and add it to the list.
						Entry nextEntry = new Entry(lineData[0], lineData[1], (lineData[2]));
						myList.add(nextEntry);

					}

				}

			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}

	}

	// save to original file
	public void quit() {
		System.out.print("quitting");

		try {
			// FileReader reads text files in the default encoding.
			FileWriter fileWriter = new FileWriter(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			/**
			 * Write to textFile
			 */
			bufferedWriter.write(this.toString());

			// Always close files.
			bufferedWriter.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}

	}

	/**
	 * Print out the Phone book in the same format that it reads data from
	 */
	public String toString() {
		String result = "";

		myList.reset();
		int size = myList.size();
		for (int i = 0; i < size; i++) {
			result += myList.getNext().toString();
		}

		return result;

	}

	private static class Entry implements Comparable<Entry> {
		String lastName;
		String firstName;
		String phoneNumber;

		public String toString() {
			return lastName + " " + firstName + " " + phoneNumber + "\n";
		}

		public Entry(String A, String B, String C) {
			lastName = A;
			firstName = B;
			phoneNumber = C;
		}

		// allows alphabetical sorting
		public int compareTo(Entry o) {
			// first check last name
			if (lastName.compareTo(o.lastName) > 0) {
				// System.out.println(lastName + " comes after " + o.lastName);
				return 1;

			} else if (lastName.compareTo(o.lastName) == 0) {
				// if last names are the same, check the first name
				// System.out.println("last names are same");
				return firstName.compareTo(o.firstName);

			} else {
				// System.out.println(lastName + " " + firstName + " comes
				// before " + o.lastName + " " + o.firstName);
				return -1;
			}

		}

		public boolean equals(Object e) {

			if (e == this) {
				return true;
			}

			if (!(e instanceof Entry)) {
				return false;
			}

			Entry ent = (Entry) e;

			if (lastName.equals(ent.lastName) && firstName.equals(ent.firstName)
					&& phoneNumber.equals(ent.phoneNumber)) {
				return true;
			} else {
				return false;
			}

		}

	}

	/**
	 * Checks if the phone book contains an entry
	 * 
	 * @param e
	 * @return
	 */
	private boolean containsNumber(String number) {
		myList.reset();

		int size = myList.size();
		for (int i = 0; i < size; i++) {

			if (number.equals(myList.getNext().phoneNumber)) {
				return true;
			}
		}

		return false;

	}

	/**
	 * Adds a new entry to the phonebook. the number must be unique.
	 * 
	 * @param last
	 *            the last name
	 * @param first
	 *            the first name
	 * @param number
	 *            the phone number
	 */
	public boolean insert(String last, String first, String number) {
		Entry ent = new Entry(last, first, number);
		if (!containsNumber(number)) {
			myList.add(ent);
			return true;
		}

		return false;

	}

	public boolean delete(String last, String first, String number) {
		Entry ent = new Entry(last, first, number);
		return myList.remove(ent);
	}

	public void print() {
		System.out.println(this);
	}

	/**
	 * Searches by first an last name
	 * 
	 * @param first
	 * @param last
	 */
	public void search(String first, String last) {
		myList.reset();

		System.out.println("Search returns: ");

		for (int i = 0; i < myList.size(); i++) {
			Entry next = myList.getNext();

			// first name and last name must match
			if (matches(first, next.firstName) && matches(last, next.lastName)) {
				System.out.println(next);
				continue;
			}

		}

	}

	/**
	 * Checks for a mismatch between the characters of the first parameters
	 * characters and the second parameters characters, until a * is reached in
	 * the first parameter, or all characters have been checked.
	 * 
	 * @param newName
	 * @param oldName
	 * @return
	 */
	private boolean matches(String newName, String oldName) {
		for (int i = 0; i < newName.length() && i < oldName.length(); i++) {
			if (newName.charAt(i) == '*') {
				break;
			} else {
				if (newName.charAt(i) != oldName.charAt(i)) {
					return false;
				}
			}

		}

		return true;
	}

	/**
	 * 
	 * @return gets three strings which reperesent one entry for the phone book
	 */
	public static String[] getCustomerInfo() {

		while (true) {
			String[] result = new String[3];
			System.out.println("Enter first name");
			result[1] = scan.next();

			if (!nameCheck(result[1])) {
				System.out.println("Bad first name, try again");
				continue;
			}
			System.out.println("Enter last name");
			result[0] = scan.next();

			if (!nameCheck(result[0])) {
				System.out.println("Bad last name, try again");
				continue;
			}

			System.out.println("Enter phone number");
			result[2] = scan.next();

			if (!checkNumeric(result[2])) {

				System.out.println("Bad phone number, try again");

				continue;
			} else if (result[2].length() > 10) {
				System.out.println("Bad phone number: too many numbers, try again");
				continue;
			}
			return result;

		}

	}

	/**
	 * Checks if a name is fit to be accepted into the phonebook (if it is
	 * alphabetical, and less than 21 characters)
	 * 
	 * @param name
	 * @return
	 */
	private static boolean nameCheck(String name) {
		return checkAlphabetitcal(name) && name.length() < 21;
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

	/**
	 * Gets a first name and last name to be used to search for entries
	 * 
	 * @return
	 */
	public static String[] getSearchQuery() {
		String[] result = new String[2];

		System.out.println("Enter first name, or * to search by all first names");
		result[0] = scan.next();
		scan.nextLine();

		System.out.println("Enter last name, or * to search all last names");
		result[1] = scan.next();
		scan.nextLine();

		return result;
	}

}
