package bookstore;

public class book {
	final int year;
	final int id;
	final String title;
	final String author;
	
	book(int year, int id, String title, String author){ //heheh
		this.year = year;
		this.id = id;
		this.title = title;
		this.author = author;
	}
	
	int getYear(){
		return year;
	}
	
	int getId(){
		return id;
	}
	
	String getTitle(){
		return title;
	}
	
	String getAuthor(){
		return author;
	}

}
