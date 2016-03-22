
public interface PhoneBookInterface {

	public boolean insert(String firstName, String lastName, String number);
	public boolean delete(String firstName, String LastName, String number);
	public void print();
	public void search(String firstName, String LastName);
	public void quit();
	
}
