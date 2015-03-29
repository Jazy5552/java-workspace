/**
 * Author's Comments
 * Free to use, take, modify
 * Courtesy of Jazy5552
 * Took me 10 minutes :D
 */
package jazy.programming2.school;

import java.util.ArrayList;

public class hashSet {
	/**
	 * @param args
	 */
	final String[] hash1;
	final String[] hash2;
	public static void main(String[] args) {
		String[] x1 = {"George","Jim","John","Black","Kevin","Michael"};
		String[] x2 = {"George","Katie","Kevin","Michelle","Ryan"};
		hashSet hs = new hashSet(x1,x2);
		System.out.printf("The union of the two sets is %s\n",hs.getUnion());
		System.out.printf("The difference of the two sets is %s\n",hs.getDifference());
		System.out.printf("The intersection of the two sets is %s\n",hs.getIntersection());
	}
	
	hashSet(String[] h1, String[] h2){
		hash1 = h1;
		hash2 = h2;
	}
	
	String getUnion(){
		ArrayList<String> x1 = new ArrayList<String>(0);
		for (int i=0;i<hash1.length;i++){
			if (!x1.contains(hash1[i])) x1.add(hash1[i]);
		}
		for (int i=0;i<hash2.length;i++){
			if (!x1.contains(hash2[i])) x1.add(hash2[i]);
		}
		String x2 = x1.toString(); //Adds [] and ,_s...
		return x2;
	}
	
	String getDifference(){
		ArrayList<String> x1 = new ArrayList<String>(0);
		for (int i=0;i<hash1.length;i++){
			if (!x1.contains(hash1[i])) x1.add(hash1[i]);
		}
		for (int i=0;i<hash2.length;i++){
			if (x1.contains(hash2[i])) x1.remove(hash2[i]);
		}
		String x2 = x1.toString(); //Adds [] and ,_s...
		return x2;
	}
	
	String getIntersection(){
		ArrayList<String> x1 = new ArrayList<String>(0);
		ArrayList<String> x2 = new ArrayList<String>(0); //Waste block :D
		for (int i=0;i<hash1.length;i++){
			if (!x1.contains(hash1[i])) x2.add(hash1[i]);
		}
		for (int i=0;i<hash2.length;i++){
			if (x2.contains(hash2[i])) x1.add(hash2[i]);
		}
		String x3 = x1.toString(); //Adds [] and ,_s...
		return x3;
	}

}
