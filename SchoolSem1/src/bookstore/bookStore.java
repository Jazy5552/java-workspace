package bookstore;

import java.util.ArrayList;
import general.mainClass;

import java.util.Arrays;

public class bookStore {
	
	//TODO Make more user-interactive...
	
	ArrayList<book> books = new ArrayList<book>(0);
	final String name;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		bookStore JC = new bookStore("JCStore");
		book b1 = new book(1998,13,"Harry Potter","Martin Luther King");
		book b2 = new book(1997,14,"La Cucaracha","Juana la Cubana");
		book b3 = new book(1300,15,"When a man meets a Woman","Unknow");
		book b4 = new book(2010,16,"El Ratonsito","La Cucarach");
		JC.addBooks(b1,b2,b3,b4);
		JC.summaryReport();
		JC.searchBooks("Harry Potter");
	}
	
	bookStore(String name){
		this.name = name;
		log(String.format("%s bookstore has been created.", name));
	}
	
	void addBooks(book...n){
		if (n.length>0) for (int i=0;i<n.length;i++){
			books.add(n[i]);
			log(String.format("Book with title: %s has been added to %s bookstore.",n[i].getTitle(),name));
		}
	}
	
	void removeBooks(String...names){
		if (names.length>0) for (int i=0;i<names.length;i++){
			for (int x=0;x<books.size();x++){
				if (books.get(x).getTitle()==names[i]) {
					books.remove(x);
					log(String.format("Book with title: %s has been removed from %s bookstore",names[i],name));
				}
			}
		}
	}
	
	void summaryReport(){
		String[] list = new String[books.size()];
		for (int i=0;i<books.size();i++){
			list[i] = books.get(i).getTitle();
		}
		Arrays.sort(list);
		log("");
		log("Displaying books by title.");
		String out = "";
		for (int i=0;i<list.length;i++){
			if ((i%5==0)&&(i>4)){
				out+=list[i]+" ,";
			} else {
				out+=list[i]+" ,";
			}
		}
		if (out.endsWith(" ,")) out = out.substring(0,out.length()-2);
		log(out);
		log("Book titles displayed.");
		log("");
	}
	
	book searchBooks(String title){
		if ((title==null)||(title=="")){
			mainClass.print("Input book title: ");
			title = mainClass.nextLine();
		}
		for (int i=0;i<books.size();i++){
			if (books.get(i).getTitle().toLowerCase().equals(title.toLowerCase())){
				log(String.format("Book with title: %s was succesfully found!",title));
				return books.get(i);
			}
		}
		log(String.format("Book with title: %s was not found...",title));
		return null;
	}
	
	
	
	void log(String mes){
		mainClass.println(mes);
	}

}
