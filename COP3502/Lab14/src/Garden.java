//package textio;
import java.util.Queue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 * A garden of oak trees
 * Concepts covered: File reading; File writing; using Queue<E>; 
 * pass-by-value; Queue traversal. 
 * @author Dr. Boyer
 *
 */
public class Garden {

	/*************
	 * MEMBER VARIABLES
	 *************/
	//A queue of trees awaiting transplant onto the land
	Queue<OakTree> treesToPlant;

	//The land represented as a grid
	OakTree[][] land;

	//The dimensions of the land 
	int landWidth;
	int landLength;

	/**
	 * MAIN METHOD
	 */	
	public static void main (String [] args) {
		Garden g = new Garden();
		//1: make a new Scanner s on a new File 
		//where the tree info is stored
		try {
			Scanner fileScanner = new Scanner(
					new File("trees.txt"));

			while (fileScanner.hasNextLine()) {
				Scanner lineScanner = new Scanner(fileScanner.nextLine());
				lineScanner.useDelimiter(",");
				String name = lineScanner.next();
				double age = Double.parseDouble(lineScanner.next());

				int height = Integer.parseInt(lineScanner.next().trim());

				g.treesToPlant.add(new OakTree(name, age,height));
				
				lineScanner.close();
			} //while
			fileScanner.close();
		}

		catch (FileNotFoundException e) {
			//ask the user to enter a valid file path
			//keep doing this until a valid one is gotten
		}
		
		catch (InputMismatchException e) {
			//you could tell the user the file isn't
			//in the right format and then quit
		}

		System.out.println("No garden planted yet but here's the queue!");
		System.out.println(g.treesToPlant);

		//Add some baby trees to the queue
		g.treesToPlant.add(new OakTree("Tree1"));
		g.treesToPlant.add(new OakTree("Tree2"));
		g.treesToPlant.add(new OakTree("Tree3"));
		g.treesToPlant.add(new OakTree("Tree21"));
		
		g.printTreeQueue();

		System.out.println("The size of the tree queue is " + g.treesToPlant.size());
		//Write the resulting queue out as a file of trees
		try {
			//create an print writer for writing to a file
			PrintWriter out = new PrintWriter(new FileWriter("tree-output.txt"));

			//output to the file a line
			//out.println(g.treesToPlant);
			for(OakTree tree : g.treesToPlant) {
				out.println(tree.name + ", " + tree.height + ", " + tree.age);
			}

			//close the file (VERY IMPORTANT!)
			out.close();
		}
		catch(IOException e1) {
			System.out.println("Error during reading/writing");
		}

		//6: Change this so that it prints the trees in the same format
		//as the trees.txt file (comma-separated without the brackets etc.)
	}//main

	/*************
	 * METHODS
	 *************/

	public Garden() {
		treesToPlant = new LinkedList<OakTree>();
		landWidth=50;
		landLength=50;
		land = new OakTree[landWidth][landLength];
	}

	/**
	 * Plants every tree in the queue into a randomly 
	 * selected spot in the garden. If there is a tree 
	 * already there, this method moves
	 * on to the next available spot
	 */
	public void plantTreesInGarden() {
		Random r = new Random();
		while(!treesToPlant.isEmpty()) {
			int x, y;
			do {
				x = r.nextInt(landWidth);
				y = r.nextInt(landLength);
			} while(land[x][y] != null); //WARNING May deadlock if land is full!
			
			land[x][y] = treesToPlant.remove();
		}
	}

	/**
	 * Grows every planted oak tree in the garden 
	 * by its default amount
	 */
	public void grow() {

	}

	/**
	 * Adds land to the garden. Doesn't lose the trees
	 * that were already planted
	 * @param newWidth The new garden width, must be 
	 * equal to or larger than old width
	 * @param newLength The new garden length, must be 
	 * equal to or larger than old length
	 */
	public void expandLand(int newWidth, int newLength) {
		if (newWidth < landWidth || newLength < landLength) return;
		OakTree[][] newLand = new OakTree[newWidth][newLength];
		
		//Copy from land into new land
		for (int i = 0; i < landWidth; i++) {
			for (int j = 0; j < landLength; j++) {
				newLand[i][j] = land[i][j];
			}
		}
		
		//Set new variables
		land = newLand;
		landWidth = newWidth;
		landLength = newLength;
	}

	/**
	 * Prints the trees awaiting planting
	 */
	public void printTreeQueue() {
		//Method 1: Print with a for-each loop
		//TODO 7
		System.out.println("For each method: ");
		for (OakTree tree : treesToPlant) {
			System.out.println(tree);
		}

		//Method 2: Print with a for-loop that removes
		//and re-adds each tree
		//TODO 8 
		System.out.println("\nFor loop method: ");
		int z = treesToPlant.size();
		for (int i = 0; i < z; i++) {
			OakTree tree = treesToPlant.remove();
			System.out.println(tree);
			treesToPlant.add(tree);
		}

	}//printTreeQueue

	/**
	 * Trees that are too large can't be transplanted into the garden.
	 * This method removes all trees higher than a threshold and leaves
	 * the rest of the queue intact. 
	 * @param threshold The height above which a tree must be removed from the queue
	 */
	public void removeLargeTreesFromQueue(int threshold) {
		//9: Fill in this method. Remember not to clobber the queue.
		Queue<OakTree> newTreesToPlant = new LinkedList<OakTree>();
		//int z = tressToPlant.size();
		//for(int i = 0; i < z; i++){
		//
		//}
		while (!treesToPlant.isEmpty()) {
			OakTree tree = treesToPlant.remove();
			if (tree.height < threshold) { //or equal to?
				newTreesToPlant.add(tree);
			}
		}
		
		treesToPlant = newTreesToPlant;
	}

} //class
