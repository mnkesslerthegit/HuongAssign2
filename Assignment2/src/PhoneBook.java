
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
	
	/**
	 * Checks if the phone book contains an entry 
	 * @param e
	 * @return
	 */
	private boolean containsNumber(String number){
		myList.reset();
		for(int i = 0; i < myList.size(); i++){
			if(number.equals(myList.getNext().phoneNumber)){
				return true;
			}
		}
		
		return false;
		
	}
	
	
	/**
	 * Adds a new entry to the phonebook. the number must be unique. 
	 * @param last the last name
	 * @param first the first name
	 * @param number the phone number
	 */
	public boolean insert(String last, String first, String number){
		Entry ent = new Entry(last, first, number);
		if(!containsNumber(number)){
		myList.add(ent);
		return true;
		}
		
		return false; 
		
	}
	
	public void delete(String last, String first, String number){
		Entry ent = new Entry(last, first, number);
		myList.remove(ent);
	}
	
	public void print(){
		System.out.println(this);
	}
	
	public void search(String last, String first, String number){
		myList.reset();
		for(int i = 0; i < myList.size(); i++){
			
			
		}
		
		
	}
	
	//save to original file
	public void quit(){
		
	}
	
	
	

	public PhoneBook(String fileName) {

	}

}
