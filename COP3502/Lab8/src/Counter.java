//Lyle Buchanan
//Kyle Hamlin
//Jassiel Llerena
import java.util.Scanner;

public class Counter {

	public static void main(String[] args) throws InterruptedException { //Eww
		Scanner in = new Scanner(System.in);
		int secs, sec, min;
		System.out.print("Enter time in seconds: ");
		secs = in.nextInt();
		//Now we loop for how ever many seconds
		for (int i=0; i<=secs; i++) {
			//Print the current time left
			//Get the minutes left
			min = (secs-i) / 60;
			//Get the seconds left after minutes
			sec = (secs-i) % 60;
			//Print out the time
			System.out.printf("%02d:%02d\n", min, sec);
			//Now we take a nap
			Thread.sleep(1000);
		}
		
		in.close(); //Shh bby
	}

}
