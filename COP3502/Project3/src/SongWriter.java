
public class SongWriter {

	public static void main(String[] args) {
		// Create wonderful music!
		//Extract the 3 numbers (They better be in args or java will have a fit!)
		int strings = Integer.parseInt(args[0]), 
				chords = Integer.parseInt(args[1]);
		double centerFreq = Double.parseDouble(args[2]);
		
		Guitar g = new Guitar(strings, chords);
		
		//Play some chords magic guitar
		g.simulateSong();
		
		//And a vocalist was born!
		Vocalist v = new Vocalist(g, centerFreq);
		
		//What do you have for us vocalist?
		System.out.println(v);
		
		//Sing for us!
		v.sing();
		
		//gg
	}

}
