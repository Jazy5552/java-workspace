//package textio;

/**
 * Represents an oak tree.
 * @author Dr. Boyer
 *
 */
public class OakTree {

	//Tree's name 
	String name; 
	
	//Age in years
	double age;
	
	//Height in inches
	int height;
	
	/**
	 * Initializes a brand new oak tree
	 * Height 0 inches and age 0 years 
	 */
	public OakTree(String s) {
		name = s;
		age=0;
		height=0;
	}
	
	/**
	 * Initializes an oak tree 
	 * with given age and height
	 * @param a Initial age in years
	 * @param h Initial height in inches
	 */
	public OakTree(String s, double a, int h) {
		name = s;
		age = a;
		height = h;
	}
	
	/**
	 * Causes this oak tree to grow by 20" and one year
	 */
	public void grow() {
		height+=20;
		age+=1;
	}
	
	/**
	 * Overloaded grow() method which takes how much the tree
	 * grew in how much time
	 */
	public void grow(double years, int inches) {
		age += years;
		height += inches;
	}
	
	/**
	 * Allows the tree's name to change
	 * @param newName the new name to replace the old name
	 */
	public void rename(String newName) {
		name = newName;
	}
	
	/**
	 * Returns a String description of this OakTree
	 */
	public String toString() {
		return name + " is " + age + " years old and " + height
				+ " inches tall";
	}
}
