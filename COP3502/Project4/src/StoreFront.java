import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * This is a skeleton code provided for students' convenience. 
 * The public methods specified here are required. The private methods are optional.
 * Do not change names of member variables for this class. Do not change public method signatures.
 * All of those items will be tested by the teaching staff's JUnit testing. 
 * The private methods here (or ones that you create as part of your own design) will not be
 * JUnit tested. 
 * @author Dr. Boyer
 *
 */
public class StoreFront {
	private Queue<Record> repairQueue = new LinkedList<Record>();
	private RecordStack inventory45s = new RecordStack();
	private RecordStack inventoryLPs = new RecordStack();

	/**
	 * This main method takes two command-line arguments. 
	 * First it takes a string of the full path to a file from which Lucy's
	 * repair queue should be initialized.
	 * Second it takes a string of the full path to a file from which Lucy's
	 * inventory stacks be initialized. That file has LPs and 45s interleaved.
	 * The main method deals with that to create the two inventory stacks.
	 * @param args The full path to a file for the repair queue and a file for inventory
	 */
	public static void main(String[] args) {
		
		//You can name this whatever you want, but this line of code is left here
		//to help you see that you'll instantiate a StoreFront object inside this main method
		StoreFront lucysStore = new StoreFront();
		
		//Here's a helpful hint of how to initialize the repair queue. 
		//You call the methods for this class from the instantiated StoreFront object
		//whether you call it lucysStore or something else is up to you. 
		lucysStore.repairQueue = InventoryPopulator.getInitialRepairQueue(args[0]);
		//Split the main inventory
		RecordStack wholeInventory = InventoryPopulator.getInitialInventory(args[1]);
		lucysStore.splitInventories(wholeInventory);
		
		//Code that displays the menu and gets Lucy's response until she wants to quit
		//Main Loop
		System.out.println("Welcome Lucy Starnight!\n");
		int choice;
		Record r;
		Scanner in = new Scanner(System.in);
		do {
			//Show menu and get choice
			choice = lucysStore.displayMenu();
			
			switch(choice) {
			case 1: //Add new album to be repaired
				r = lucysStore.getRecordInfoFromLucy();
				lucysStore.addAlbumToRepair(r);
				break;
			case 2: //Repair an album
				lucysStore.repairAlbum();
				break;
			case 3: //View repair queue
				System.out.println(lucysStore.printRepairQueue());
				break;
			case 4: //Add a new album to be sold (to the stack)
				r = lucysStore.getRecordInfoFromLucy();
				lucysStore.addAlbumToSell(r);
				break;
			case 5: //Sell an LP album
				lucysStore.sellLP();
				break;
			case 6: //Sell a 45 album
				lucysStore.sell45();
				break;
			case 7: //View sales inventory
				System.out.println(lucysStore.displayInventory());
			case 8: //Begone!
				break;
			default:
				System.out.println("Invalid input. Try again.");
			}
			
		} while(choice != 8);
		
		System.out.println("Farewell Lucy!");
		in.close();
	} 

	private void splitInventories(RecordStack wholeInventory) {
		// Split the wholeInventory into the 2 appropriate inventories according to record type
		//WARNING Stack order will be reversed!
		//Who cares, not like it's toString does anything :'(
		while (!wholeInventory.isEmpty()) {
			Record r = wholeInventory.pop();
			if (r instanceof LP)
				inventoryLPs.push(r);
			else
				inventory45s.push(r); //May get weird records
		}
	}

	/**
	 * StoreFront constructor. Initializes the repairQueue, inventory45s, and inventoryLPs
	 * to empty structures.
	 */
	public StoreFront() {
		//Nothing
	}

	/**
	 * Adds the specified album to the end of the repair queue.
	 * Since there is no limit to the size of the repair queue this method just adds the record.
	 * @param r The record to add to the repair queue
	 */
	public void addAlbumToRepair(Record r) {
		repairQueue.add(r);
		System.out.println(r + "\nAlbum added to repair queue!");
	}

	/**
	 * Removes the album at the head of the repair queue.
	 * If the repair queue was already empty, this does nothing. 
	 */
	public void repairAlbum() {
		Record r = repairQueue.remove(); //OSHA Would be disappointed (May throw exception)
		System.out.println(r + "\nAlbum repaired!");
	}

	/**
	 * Displays the repair queue, as a list of single albums per line. 
	 * Each album is displayed precisely as mandated for the Record.toString method. 
	 * There is no new line at the end of the returned String but there is a newline between
	 * each displayed album.
	 * @return
	 */
	public String printRepairQueue() {
		String ret = "";
		for (Record r : repairQueue) { //?
			ret += r + "\n";
		}
		return ret;
	}

	/**
	 * Adds a new album to the top of the appropriate inventory stack.
	 * @param r The LP or 45 to add to the inventory. 
	 */
	public void addAlbumToSell(Record r) {
		if (r instanceof LP) {
			inventoryLPs.push(r);
		} else { //yolo
			inventory45s.push(r);
		}
		System.out.println(r + "\nAlbum added to inventory!");
	}

	/**
	 * Removes the topmost item from the inventory of LPs.
	 * If the inventory is empty, calling this method will cause a runtime exception.
	 * Your code must check for empty before calling this method. 
	 */
	public void sellLP() {
		if (inventoryLPs.isEmpty()) {
			//TODO Look into whether program should exit
			System.out.println("Error! No LP albums available in inventory!");
		} else {
			Record r = inventoryLPs.pop();
			System.out.println(r + "\nAlbum sold!");
		}
	}

	/**
	 * Sells a 45. Removes the topmost 45 from the inventory stack.
	 */
	public void sell45() {
		if (inventory45s.isEmpty()) {
			//Look into whether program should exit: No just print error
			System.out.println("Error! No 45 albums available in inventory!");
		} else {
			Record r = inventory45s.pop();
			System.out.println(r + "\nAlbum sold!");
		}
	}

	/**
	 * This method displays the inventory. It is PROVIDED to students to avoid annoying
	 * test case failure due to small formatting problems. :) 
	 * It is JUnit testable for precise, correct output. 
	 * @return a String representation of the inventory. First LPs then 45s. 
	 * 		   Does not include a new line at the end of this string representation. 
	 */
	public String displayInventory() {
		String LPs = (this.inventoryLPs.size() == 1) ? " LP " : " LPs ";
		String FortyFives = (this.inventory45s.size() == 1) ? " 45." : " 45s.";
		return "You have " + this.inventoryLPs.size() + LPs + "and " + 
		this.inventory45s.size() + FortyFives;	
	}

	/**
	 * This method displays the storefront menu. Dr. Boyer thinks it is convenient to 
	 * display the menu to Lucy, get her choice, and then return that choice from this method.
	 * If your menu items are not ints then you would change the return type. 
	 * You can also just delete this method if it doesn't fit your design.  
	 * @return the selection that Lucy made, as an integer
	 */
	private int displayMenu() {
		@SuppressWarnings("all") //I'm sorry scanner, for the galaxy's sake
		Scanner in = new Scanner(System.in); //Please don't leak
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
		
		try {
			return Integer.parseInt(in.nextLine()); //Savage
		} catch (Exception e) {
			return -1;
		}
	}

	/*
	 * This is a service method within this class, provided only for your convenience. 
	 * You do not have to use it (you can delete this method  and structure your code
	 * however you like, including the ways in which you get the info for the new Record
	 * that Lucy wants to either add to inventory or add to the repair queue). 
	 * It is  up to your discretion how to ask Lucy for each new record's information
	 * This method will NOT be JUnit tested. 
	 * @return a new Record which will either be added to a queue or a stack, depending on context
	 */
	private Record getRecordInfoFromLucy() {
		@SuppressWarnings("all") //I'm sorry scanner, for the galaxy's sake
		Scanner s = new Scanner(System.in); //Please don't leak
		System.out.println("Ok! What is the title of the album?");
		String title = s.nextLine();
		System.out.println("What is the artist name?");
		String artist = s.nextLine();
		System.out.println("What is the album's year?");
		int year = Integer.parseInt(s.nextLine().trim());
		System.out.println("Is it an LP or a 45? Type 'LP' or '45'.");
		String type = s.nextLine();
		if (type.equals("45")) return new FortyFive(artist, title, year);
		else return new LP(artist, title, year); //Yolo
		//TODO Add input checks you animal!
		//Na don't wana deal with nulls
	}

}//class

