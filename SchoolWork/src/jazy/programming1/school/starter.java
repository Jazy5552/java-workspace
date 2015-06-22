package jazy.programming1.school;

public class starter {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		int sum=0;
		// TODO Auto-generated method stub
		numbers num = new numbers(3);
		
		System.out.print("The nums are " );
		for (int i=0;i<num.getNumbers().length;i++) {
			sum+=num.getNumbers()[i];
		}
		System.out.print(sum);
	}

}
