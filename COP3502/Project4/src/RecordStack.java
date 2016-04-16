
public class RecordStack {
	private Record[] data;
	private int top; // -1 == Empty
	
	public RecordStack() {
		//Create an empty stack
		data = new Record[5]; //Initial size of 5 seems good
		top = -1; //Currently empty
	}
	
	public void push(Record r) {
		//Add Record r to the data array. The data array must be appropriately sized or resized
		//to incorporate the new Record.
		//top must be readjusted to point the new top of the stack
		if ((top + 1) >= data.length) {
			//Stack is full! Make it taller!
			data = enlarge(data); 
			//Holy GarbageCollector, please forgive my sins and clean up my messes
		}
		//Hmm should I shrink the stack when appropriate? Naaa
		
		data[++top] = r; 
	}
	
	public Record pop() {
		//Return the top index of record and re-adjust top
		if (top < 0) {
			//Stack is empty!!!
			return null;
		} else {
			return data[top--]; //Animal
		}
	}
	
	private Record[] enlarge(Record[] rs) {
		//Create a new array of 2 * the size of rs.
		//Then move all the stuff from rs into the new one
		Record[] newRecords = new Record[rs.length*2];
		//Move the contents of rs over
		for (int i=0; i<rs.length; i++) {
			newRecords[i] = rs[i];
		}
		
		return newRecords;
	}
	
	public boolean isEmpty() {
		//Return true if the there is nothing on the stack or false if there is something
		//Top will be < 0 if the stack is empty
		return (top < 0) ? true : false;
	}
	
	public int size() {
		//Is size the capacity of the stack or the number of elements in the stack
		//Return the size of the stack
		return (top + 1);
	}
	
	public String toString() {
		//Not sure what this funtion is supposed to do since the stack should not be printed...
		/*
		//Return the string representation of the stack
		String re = "";
		for (int i=0; i<top+1; i++) {
			re += data[i] + "\n";
		}
		return re;
		*/
		return null;
	}
	
}
