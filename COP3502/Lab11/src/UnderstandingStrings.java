//Jassiel Llerena
//Tony Parra
//David Stellear
public class UnderstandingStrings {

	public static void main(String[] args) {
		String s1 = "Go";
		String s2 = "Gators";
		String s3 = new String("Go");
		System.out.println(s1.equals(s2));
		//The result is false because .equals checks if each indexed character of the strings match
		
		System.out.println(s1.equals(s3));
		//The result is true because .equals checks if each indexed character of the strings match
		
		System.out.println(s1 == s3);
		//The result is false because s1 and s3 reference different objects and the == checks if the objects' addresses are identical
		
		System.out.println(s1.compareTo(s3));
		//The result is 0 because .compareTo compares the indexed characters of the strings
		
		s1 = "Gators";
		s3 = "Florida";
		System.out.println(s1 == s2);
		//Result is true because s1 is not referencing the same address as s2 ("Gators") in memory
		
		System.out.println(s1.compareTo(s2));
		//Result is 0 because s1 and s2 have identical characters
		
		System.out.println(s3.compareTo(s1));
		//Result is -1 because compareTo sees that the first character in s1 and s3 differ and returns the ascii value of the
		//character from s3 - s1
		
		//additional question: What does s3 have to be equal to in order to
		//get a positive number when doing compareTo?
		//s3 Would have to start with a character after the letter G
	}

}
