
public abstract class Record {
	private String artist, title;
	private int year;
	
	public Record(String artist, String title, int year) {
		this.artist = artist;
		this.title = title;
		this.year = year;
	}
	
	public String toString() {
		//return a string appropriate to FortyFive's rpm
		return null;
	}
	
	/*
	 * Getters
	 */
	public String getArtist() {
		return artist;
	}
	public String getTitle() {
		return title;
	}
	public int getYear() {
		return year;
	}
}
