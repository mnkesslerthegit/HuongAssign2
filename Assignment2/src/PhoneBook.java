
public class PhoneBook {

	private ListDT<Entry> myList;

	private static class Entry implements Comparable<Entry> {
		String lastName;
		String firstName;
		String phoneNumber;

		public Entry(String A, String B, String C) {
			lastName = A;
			firstName = C;
			phoneNumber = C;
		}

		// allows alphabetical sorting
		public int compareTo(Entry o) {
			// TODO Auto-generated method stub
			return 0;
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
	
	
	//must be unique phone number
	private void insert(){
		
		
	}
	
	private void delete(){
		
	}
	
	private void print(){
		System.out.println(this);
	}
	
	private void search(){
		
	}
	
	//save to original file
	private void quit(){
		
	}
	
	
	

	public PhoneBook(String fileName) {

	}

}
