import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class StoreFront {
	private static Queue<Record> repairQueue;
	private static RecordStack inventory45s;
	private static RecordStack inventoryLPs;

	public static void main(String[] args) {
		//Initialize variables
		inventory45s = new RecordStack();
		inventoryLPs = new RecordStack();
		repairQueue = new LinkedList<Record>();
		
		//Initialize inventory
		InitialInventoryPopulator.getInitialInventory("Lucy_Initial_Inventory.csv", inventory45s, inventoryLPs);
		
		//Main Loop
		System.out.println("Welcome Lucy Starnight!\n");
		int choice;
		Record r;
		Scanner in = new Scanner(System.in);
		do {
			//MENU
			System.out.print("" +
			"---------------------------------------------------\n" +
		    "1. Add a new album to be REPAIRED\n" +
		    "2. Repair an album\n" +
		    "3. View Repair Queue\n" +
		    "4. Add a new album to be SOLD\n" +
		    "5. Sell an LP album\n" +
		    "6. Sell a 45 album\n" +
		    "7. View Sales Inventories\n" +
		    "8. Abandon All Hope\n" +
			"-----------------------------------------------------\n\n"
			+ "Select an Option > ");
		
			//Get user choice
			choice = Integer.parseInt(in.nextLine());
			
			switch(choice) {
			case 1: //Add new album to be repaired
				r = PromptForRecord(in);
				repairQueue.add(r);
				System.out.println(r + "\nAlbum added to repair queue!");
				break;
			case 2: //Repair an album
				r = repairQueue.remove(); //OSHA Would be disappointed
				System.out.println(r + "\nAlbum repaired!");
				break;
			case 3: //View repair queue
				if (repairQueue.isEmpty()) {
					//Queue empty
					System.out.println("Error! No albums in the repair queue. Enjoy a break.");
				} else {
					System.out.println("Next album in the repair queue:\n" + repairQueue.peek()); 
				}
				break;
			case 4: //Add a new album to be sold (to the stack)
				r = PromptForRecord(in);
				if (r instanceof LP) {
					inventoryLPs.push(r);
				} else { //yolo
					inventory45s.push(r);
				}
				System.out.println(r + "\nAlbum added to inventory!");
				break;
			case 5: //Sell an LP album
				if (inventoryLPs.isEmpty()) {
					System.out.println("Error! No LP albums available in inventory!");
				} else {
					r = inventoryLPs.pop();
					System.out.println(r + "\nAlbum sold!");
				}
				break;
			case 6: //Sell a 45 album
				if (inventory45s.isEmpty()) {
					System.out.println("Error! No 45 albums available in inventory!");
				} else {
					r = inventory45s.pop();
					System.out.println(r + "\nAlbum sold!");
				}
				break;
			case 7: //View sales inventory
				System.out.println("---LP Albums in Inventory---");
				System.out.println(inventoryLPs);
				System.out.println("---45 Albums in Inventory---");
				System.out.println(inventory45s);
			case 8: //Begone!
				break;
			default:
				System.out.println("Invalid input. Try again.");
			}
			
		} while(choice != 8);
		
		System.out.println("Farewell Lucy!");
		in.close();
	}
	
	public static Record PromptForRecord(Scanner in) {
		String type, artist, title, year;
		System.out.print("Is the album type an LP or 45 > ");
		type = in.nextLine();
		System.out.print("Enter the album Artist > ");
		artist = in.nextLine();
		System.out.print("Enter the album Title > ");
		title = in.nextLine();
		System.out.print("Enter the album Year > ");
		year = in.nextLine();
		
		if (type.toLowerCase().equals("lp")) {
			//Make an LP record
			return (new LP(artist, title, Integer.parseInt(year)));
		} else { //Everything else goes??? savage
			//Make a 45 record
			return (new FortyFive(artist, title, Integer.parseInt(year)));
		}
	}

}
