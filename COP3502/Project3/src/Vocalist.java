
public class Vocalist {
	double center; //AKA midRangeFreq
	double[][] melody;
	Guitar guitar;
	
	
	Vocalist(Guitar guitar, double center) {
		this.guitar = guitar;
		this.center = center;
		generateMelody();
	}
	
	void generateMelody() {
		//TODO Go through the guitar chords and find one that best matches this
		//vocalist's center frequency and make that into the melody
		
	}
	
	public void sing() {
		//TODO Singggg my pretty little vocalist!
		
	}
	
	public String toString() {
		//TODO Return a string with the melody strings on the first row and
		//the beats to play each on the second row
		
		return null;
	}
	
	
	
}
