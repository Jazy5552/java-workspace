
public class LP extends Record {

	public LP(String artist, String albumTitle, int year) {
		super(artist, albumTitle, year);
	}
	
	@Override
	public String toString() {
		//Return a string appropriate to LP's rpm
		return ("LP: " + getTitle() + " by " + getArtist() + ", " + getYear());
	}

}
