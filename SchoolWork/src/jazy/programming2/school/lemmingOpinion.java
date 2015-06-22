/**
 * Author's Comments
 * Free to use, take, and modify
 * Courtesy of Jazy5552
 * (Got headache while making this, hence all the comments to get a bit organized...)
 */

package jazy.programming2.school;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File; //Make a file
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter; //Print to file

public class lemmingOpinion {
	final boolean debug = false; //Had to use to ease headache...
	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		@SuppressWarnings("unused") //Yeah stfu!
		lemmingOpinion lo = new lemmingOpinion("C:\\lemmingOpinion.txt");
	}
	
	lemmingOpinion(String location)throws IOException {
		System.out.printf("Lemming Opinion class started!\n");
		read(location);
	}
	
	void read(String location)throws IOException,FileNotFoundException{ //It gives me a headache to look at this... Not to mention the pain it took to make :(
		ArrayList<Integer> list = new ArrayList<Integer>(0);
		int num = 0;
		File file = null;
		Scanner text = null;
		try{
			file = new File(location);
			text = new Scanner(file);
			num = Integer.parseInt(text.next());
			for (int i=0;i<=num;i++) list.add(0); //Set all to zero
		}catch(FileNotFoundException e){
			System.out.printf("CREATING NEW FILE, YOURS DOESN'T EXIST!!\n");
			write(location);
			read(location);
			return;
		}
		int index,value;
		if (debug) System.out.printf("DEBUG- %d amount!\nBench1\n",num); /**debug*/
		while(true){
			if (!text.hasNextLine()||!text.hasNext()) break;
			index = Integer.parseInt(text.next());
			value = list.get(index);
			list.set(index,value+=Integer.parseInt(text.next())); //Add their new values (Incase there is double entries)
			if (debug) System.out.printf("DEBUG- Bench %d %d\n",index, value); /**debug*/
		}
		String[] tmp = new String[getHighest(list)+1];
		for (int i=0;i<=getHighest(list);i++){ tmp[i] = ""; //i=COUNT
			for (int ii=0;ii<=list.size()-1;ii++){ //ii=ID
				if ((list.get(ii)!=0)&&(i==list.get(ii))) { //list[ID] = COUNT
					tmp[i]+=" " + ii + ","; //tmp[COUNT] = SIMILAR IDs
					if (debug) System.out.printf("DEBUG- Bench2 %s have SAME\n", tmp[i]); /**debug*/
				}
			}
			if ((tmp[i]!="")&&(tmp[i].endsWith(","))) {
				tmp[i] = tmp[i].substring(0,tmp[i].length()-1);
				if (debug) System.out.printf("DEBUG- Bench3.2 %s\n", tmp[i]); /**debug*/
			}
		}
		int total = 0;
		for (int i=0;i<=tmp.length-1;i++){
			if (tmp[i]!="") {
				System.out.printf("Student(s) %s share the same opinion\n", tmp[i]);
				total+=1;
			}
		}
		System.out.printf("There are a total of %d opinions", total);
		text.close(); //Can't forget that...
	}
	
	void write(String location)throws FileNotFoundException, IOException{ //File creater (Like a Boss)
		FileWriter file = new FileWriter(location);
		PrintWriter out = new PrintWriter(file);
		Random gen = new Random();
		int amount = gen.nextInt(8)+7;
		if (debug) System.out.printf("DEBUG- BenchW1 Amount = %s\n", amount); /**debug*/
		out.println(amount);
		
		for (int i=1;i<=amount;i++) {
			out.println(String.format("%d %d", i, gen.nextInt(8)+1));
		}
		
		int ran = gen.nextInt(4);
		for (int i=1;i<=ran;i++){
			out.println(String.format("%d %d", gen.nextInt(amount-1)+1,gen.nextInt(8)+1)); //Simulate some that had more than one entry :}
		}
		
	out.close(); //Can't forget that...
	System.out.printf("File written!\n\n");
	
	}
	
	int getHighest(ArrayList<Integer> al){ //Returns largest number in Array
		 int g = 0;
		 for (int i=0;i<=al.size()-1;i++) {
			 if (al.get(i)>g) g=al.get(i);
		 }
		return g;
	}

}
