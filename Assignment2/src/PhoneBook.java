import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PhoneBook {

	private ListDT<Entry> myList;

	public PhoneBook(String fileName) {
		myList = new ListDT<Entry>();

		String line = null;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
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

	public String toString() {
		String result = "Phone book: \n ";

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
			return "Entry: " + lastName + " " + firstName + " " + phoneNumber + "\n";
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
				System.out.println(lastName + " comes after " + o.lastName);
				return 1;

			} else if (lastName.compareTo(o.lastName) == 0) {
				// if last names are the same, check the first name
				System.out.println("last names are same");
				return firstName.compareTo(o.firstName);

			} else {
				System.out.println(lastName + "  " + firstName + " comes before " + o.lastName + "  " + o.firstName);
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

	public void search(String last, String first) {
		myList.reset();

		System.out.println("Search returns: ");

		for (int i = 0; i < myList.size(); i++) {
			Entry next = myList.getNext();
			// first name matches
			if (next.firstName.equals(first) || first.equals("*")) {

				// last name matches
				if (next.lastName.equals(last) || last.equals("*")) {
					System.out.println(next);
				}

			}

		}

	}

	// save to original file
	public void quit() {

	}

}
