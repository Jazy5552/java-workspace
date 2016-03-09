//Lyle Buchanan
//Kyle Hamlin
//Jassiel Llerena
import java.util.Random;

public class JustMathyThings {

	public static void main(String[] args) {
		Random r = new Random(); //Random number generator
		int[] nums = new int[10];
		for (int i=0; i<nums.length; i++) {
			//Generate a random number and save it to nums[i]
			nums[i] = r.nextInt(999) + 1;
		}
		//nums is now full of random numbers
		System.out.println("Random Array Generated: ");
		displayArr(nums);
		System.out.println("The mean is: " + findMean(nums));
		System.out.println("The range is: " + findRange(nums));
		//Do the doubling nonsense
		doubleEveryOtherVal(nums);
		System.out.println("New Array Generated: ");
		displayArr(nums);
		//Such math
	}
	
	public static void displayArr(int[] arr) {
		System.out.print("[");
		for (int i=0; i<arr.length; i++) {
			if (i == arr.length-1) {
				//On the last one so don't print the , 
				System.out.print(arr[i]);
			} else {
				System.out.print(arr[i] + ", ");
			}
		}
		System.out.println("]\n");
	}
	
	public static double findMean(int[] arr) {
		double sum = 0;
		//Loop through and add every number into sum
		for (int i=0; i<arr.length; i++) {
			sum += arr[i];
		}
		//Return the mean
		return sum / arr.length;
	}
	
	public static int findRange(int[] arr) {
		int max = arr[0], min = arr[0];
		for (int i=0; i<arr.length; i++) {
			//Check if arr[i] is max
			if (arr[i] > max) {
				//The new max is chosen!
				max = arr[i];
			}
			if (arr[i] < min) {
				//A new min has been chosen!
				min = arr[i];
			}
		}
		//Return the range
		return max - min;
	}
	
	public static void doubleEveryOtherVal(int[] arr) {
		for (int i=0; i<arr.length; i+=2) {
			//Just like a normal for loop but in increments of 2!
			arr[i] = arr[i] * 2;
		}
		//No return, arr is a reference to the array passed in so the original
		//will be edited. 
	}

}
