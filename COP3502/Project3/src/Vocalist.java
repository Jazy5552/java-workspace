
public class Vocalist {
	double center; //AKA midRangeFreq
	double[][] melody;
	Guitar guitar;
	
	
	Vocalist(Guitar guitar, double center) {
		this.guitar = guitar; //I got that devilish flow
		this.center = center; //Rock and roll
		generateMelody(); //No halo!
	}
	
	void generateMelody() {
		//Go through the guitar chords and find one that best matches this
		//vocalist's center frequency and make that into the melody
		System.out.printf("Vocalist(): midRangeFreq: %.2f\n", center);
		int songLength = guitar.getSongLength();
		System.out.printf("Vocalist(): songlength: %d\n", songLength);
		double[] chord;
		double magnitude, contender; //Magnitude and contender will be the difference ratio of the center vs the current
		//melody and the the contending chord respectively.
		melody = new double[2][songLength];
		for (int i=0; i<songLength; i++) {
			//Grab every chord form the guitar to compile the melody
			chord = guitar.getChordAsArray(i);
			for (int j=0; j<chord.length-1; j++) {
				//Look for the most compatible string to vocalize
				if (j == 0) melody[0][i] = chord[j]; //Store the very first one
				magnitude = Math.abs((melody[0][i] / center) - 1); //Every day
				contender = Math.abs((chord[j] / center) - 1); //I'm shuffling
				if (contender < magnitude) {
					//Contender is closer to the center freq
					melody[0][i] = chord[j];
				}
			}
			//Store the beats time (last entry in chord) in the second row of melody
			melody[1][i] = chord[chord.length-1];
		}
	}
	
	public void sing() {
		//Singggg my pretty little vocalist!
		for (int j=0; j<melody[0].length; j++) {
			//Divide the duration by 4 and loop this whole thing a couple
			//times and this thing starts to get trippy
			StdAudio.playTone(melody[0][j], melody[1][j]);
		}
	}
	
	public String toString() {
		//Return a string with the melody strings on the first row and
		//the beats to play each on the second row
		String result = "";
		for (int i=0; i<melody.length; i++) {
			for (int j=0; j<melody[i].length; j++) {
				if (i == 0)
					result += String.format("%7.2f\t", melody[i][j]); //For the frequencies
				else
					result += String.format("%7.1f\t", melody[i][j]); //For the time
			}
			result += "\n"; //Just a newline
		}
		
		return result;
	}
	
	
	
}
