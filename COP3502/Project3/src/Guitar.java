
public class Guitar {
	int strings;
	int chords;
	double[][] song;
	
	Guitar(int strings, int chords) {
		this.strings = strings;
		this.chords = chords;
		generateSong();
	}
	
	void generateSong() {
		//TODO Uses strings and chords to fill in the song array
		//with random doubles between 27.5 Hz (inclusive) and 4186 Hz (exclusive)
		//and an extra row with the number of beats to be played 1 (inclusive) to 3 (exclusive)
		
	}
	
	public void simulateSong() {
		//TODO Simulate the playing of the song by printing out the chord (strings) in one row with dots
		//at the end showing the beats that chord will play for. Also the program will hold for that
		//number of beats as if the chord was actually played.
		
	}
	
	public double[] getChordAsArray(int chord) {
		//TODO Return an array of strings (a chord) from the song as specified by the
		//chord index. If index is invalid then return "No such chord!"
		return null;
	}
}
