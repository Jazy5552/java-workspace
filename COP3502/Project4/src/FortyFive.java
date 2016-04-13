
public class FortyFive extends Record {

	public FortyFive(String artist, String albumTitle, int year) {
		super(artist, albumTitle, year);
	}
	
	@Override
	public String toString() {
		//Return a string appropriate to FortyFive's rpm
		return ("45: " + getTitle() + " by " + getArtist() + ", " + getYear());
	}

}
