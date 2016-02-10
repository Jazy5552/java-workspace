
public class Reverse {
	public static int reverseNum(int num) {
		int rnum = 0;
		while (num > 0) {
			rnum = rnum * 10 + num % 10;
			num /= 10;
		}
		return rnum;
	}
	
	public static void main(String[] args) {
		System.out.println(reverseNum(1234567));
	}

}
