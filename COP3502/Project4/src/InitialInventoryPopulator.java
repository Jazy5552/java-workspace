import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class InitialInventoryPopulator {

		/*
		 * @params filename: Path to file containing inventory data
		 * fortyFiveStack: RecordStack that will be filled with 45 type records
		 * lpStack: RecordStack that will be filled with lp type records
		 */
		public static void getInitialInventory(String filename, RecordStack fortyFiveStack, RecordStack lpStack) {
			//Read file and create the appropriate records.
			//Create a record stack and add the records to it
			String[] tmp;
			Scanner in;
			try {
				in = new Scanner(new File(filename));
			} catch (FileNotFoundException e) {
				System.out.println(filename + " not found!");
				return;
			}
			
			while (in.hasNextLine()) {
				//Split the string by commas
				tmp = in.nextLine().split(", ");
				//Artist, Title, Year, Type
				if (tmp[3].equals("45")) {
					//Create a forty five record
					Record ffR = new FortyFive(tmp[0], tmp[1], Integer.parseInt(tmp[2]));
					fortyFiveStack.push(ffR);
				} else {
					//tmp[3] == "lp"
					//Create an lp record
					Record lpR = new LP(tmp[0], tmp[1], Integer.parseInt(tmp[2]));
					lpStack.push(lpR);
				}
			}
			in.close();
			//No return. We are doing it the C++ way of parameter references. ew
		}
}
