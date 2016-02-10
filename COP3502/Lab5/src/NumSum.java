/*
 * Jassiel Llerena
 * Charels Knox
 */
public class NumSum {

	public static void main(String[] args) {
		int count = 0;
		int sum = 0;
		while( count < 100 ) {
			count++;
			if ((count%2 == 0 || count%5 == 0) && (!(count%2 == 0) || !(count%5 == 0))) {
				//Above is an xor statement, dont do anything if in here
			} else {
				sum += count;
			}
		}
		
		System.out.println("Sum: " + sum);

	}

}
