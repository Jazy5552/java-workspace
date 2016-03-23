import java.util.Random;

public class Guitar {
	int strings;
	int chords;
	double[][] song;
	
	Guitar(int strings, int chords) {
		this.strings = strings; //Yo!
		this.chords = chords; //I'm running through these
		generateSong();
	}
	
	void generateSong() {
		//Uses strings and chords to fill in the song array
		//with random doubles between 27.5 Hz (inclusive) and 4186 Hz (exclusive)
		//and an extra row with the number of beats to be played 1 (inclusive) to 3 (exclusive)
		System.out.printf("Guitar(): Generated new guitar with %d strings. Song length is %d chords.\n", strings, chords);
		
		Random r = new Random();
		double min = 27.5,
				max = 4186,
				range = max - min;
		
		song = new double[strings+1][chords]; //+1 for beats row
		for (int i=0; i<strings; i++) {
			for (int j=0; j<chords; j++) {
				song[i][j] = (r.nextDouble()*range)+min; //Generate random double within range
				System.out.printf("%7.2f\t",song[i][j]);
			}
			System.out.println();
		}
		
		//Now generate the beats row
		for (int j=0, i=strings; j<chords; j++) {
			song[i][j] = (r.nextDouble()*2)+1;
			System.out.printf("%7.1f\t",song[i][j]);
		}
		System.out.println("\n"); //Just 2 new line
	}
	
	public void simulateSong() {
		//Simulate the playing of the song by printing out the chord (strings) in one row with dots
		//at the end showing the beats that chord will play for. Also the program will hold for that
		//number of beats as if the chord was actually played.
		System.out.println("Guitar.simulateSong()");
		
		String currentChord;
		for (int j=0; j<chords; j++) {
			currentChord = "";
			for (int i=0; i<strings; i++) {
				currentChord += String.format("%7.2f\t",song[i][j]);
			}
			//Remove that last space
			currentChord = currentChord.substring(0, currentChord.length()-1);
			//Print the chord and wait
			System.out.print(currentChord);
			for (int w=0; w<Math.round(song[strings][j]); w++) {
				//Wait for however many seconds the beats value says on that last row of song
				nowStop(1000);
				System.out.print("."); //Print the lil'dot
			}
			System.out.println(); //Just a new line
		}
		System.out.println(); //Just a new line
	}
	
	public double[] getChordAsArray(int chord) {
		//Return an array of strings (a chord) from the song as specified by the
		//chord index. If index is invalid then return null
		if (chord >= 0 && chord < chords) {
			//valid chord index
			double[] c = new double[strings+1]; //+1 to include the time (last row of song)
			for (int i=0; i<strings+1; i++) {
				c[i] = song[i][chord];
			}
			return c;
		}
		//Invalid chord
		return null;
	}
	
	private void nowStop(int dur) {
		//Hating's bad!
		try { //One more shot for us!
			Thread.sleep(1000); //Another round!
		} catch (Exception e) { //Please fill up my cup!
			//Don't mess around!
		} //We just wana see ya
	} //SHAKE DAT!
	
	//Just a getter
	public int getSongLength() {
		return chords;
	}
}
