
public class Contact {
	String name;
	String nick;
	String DOB;
	
	Contact() {
		name = "No Entry";
		nick = "";
		DOB = "";
	}
	
	Contact(String in) {
		String[] args = in.split("-");
		name = args[0];
		nick = args[1];
		DOB = args[2];
	}
	
	void printContact() {
		System.out.println(name);
		System.out.println(nick);
		System.out.println(DOB);
		System.out.println();
	}
}
