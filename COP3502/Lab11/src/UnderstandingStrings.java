
public class UnderstandingStrings {

	public static void main(String[] args) {
		String s1 = "Go";
		String s2 = "Gators";
		String s3 = new String("Go");
		System.out.println(s1.equals(s2));
		System.out.println(s1.equals(s3));
		System.out.println(s1 == s3);
		System.out.println(s1.compareTo(s3));
		s1 = "Gators";
		s3 = "Florida";
		System.out.println(s1 == s2);
		System.out.println(s1.compareTo(s2));
		System.out.println(s3.compareTo(s1));
		//additional question: What does s3 have to be equal to in order to
		//get a positive number when doing compareTo?
	}

}
