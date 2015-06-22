package general;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
 
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.DataLine.Info;
 
/**
 * 
 * @author Jazy
 * Music must be in wav format!!!
 *
 */

public class musicPlayer {
	private static final boolean dd = true; //Class debug
	private static boolean debug;
	private static boolean empty;
	static String[] music; //Just for display
	static InputStream[] songs; //Holds the songs
	static boolean playing = false; //Records plays
	static int[] played;
	static final String musicFolder = "songs/";
	static JarFile jf;
 
    private final int EXTERNAL_BUFFER_SIZE = 1024*128; // 128Kb?
    //524288
    
    //TODO Fix not playing twice same song
    public static void main(String args[]){
    	musicPlayer mp = new musicPlayer();
    	mp.play(null);
    }
    
	musicPlayer(){ //List the music
		try{debug = mainClass.debug;}catch(Exception e){debug = dd;}
		refreshSongs();
	}
 
    
   public void play(InputStream song) { //If null will shuffle
	   playing = true;
	   	if (empty) {
	   		log("No music in songs folder...");
	   		return;
	   	}
	   	if (debug) {log("play Commenced!");System.out.println("Play Commenced");}
    	int pick;
    	Random r = new Random();;
    	if (song==null){//Shuffle this dope!
    		if (debug) log("Shuffleing!");
			if (songs.length>0) pick = r.nextInt(songs.length);
			else pick = 0;
			song = songs[pick];
			if (played[pick]==1){
    		while(played[pick]==1){try{Thread.sleep(150);}catch(Exception e){} //DELAY
    			if (songs.length>0) pick = r.nextInt(songs.length);
    			else pick = 0;
    			song = songs[pick];
    			if (allPlayed()){ //If so reset them
    				refreshSongs();
    			}
    		}
			}
    		if (!playing) return;
    		log("Song Choosen "+music[pick]);
    	}else{
    		music = new String[1];
    		music[0] = "Defualt Song";
    		pick = 0;
    	}
    	if (debug) log("Song="+music[pick]); 
    	played[pick] = 1;
    	AudioInputStream audioInputStream = null;
    	try {
    		audioInputStream = AudioSystem.getAudioInputStream(song);
    	} catch (Exception e1) {
    		if (debug) {
    			log("Error with audioInputStream: "+e1.getMessage());
    		}
    		return;
    	}
    	AudioFormat audioFormat = audioInputStream.getFormat();
    	Info info = new Info(SourceDataLine.class, audioFormat);
 
    	SourceDataLine dataLine = null;
    	try {
    		dataLine = (SourceDataLine) AudioSystem.getLine(info);
    		dataLine.open(audioFormat, this.EXTERNAL_BUFFER_SIZE);
    	} catch (Exception e1) {
    		if (debug) {
    			log("Error with dataLine: "+e1.getMessage());
    		}
		return;
    	}
    	
    	if (debug) log("dataLine.start() Commencing!:"+music[pick]);
    	dataLine.start(); //Begins writing to audio channel
    	int readBytes = 0;
    	byte[] audioBuffer = new byte[this.EXTERNAL_BUFFER_SIZE];
    	if (debug)log("Working???"); 
    	try {
    		while ((readBytes != -1)) {
    			if(!playing) {
    				dataLine.drain(); //Play what ever is left
    	    		dataLine.close(); //Close that sucker
    	    		return;
    			}
    			readBytes = audioInputStream.read(audioBuffer, 0,
    					audioBuffer.length);
    			if (readBytes >= 0){
    				dataLine.write(audioBuffer, 0, readBytes);
    			}
    		}
    	} catch (IOException e1) {
    		log("Error with writing waves?: "+e1.getMessage());
    		return;
    	} finally {
    		dataLine.drain(); //Play what ever is left
    		dataLine.close(); //Close that sucker
    	}
    	try{Thread.sleep(100);song.close();}catch(Exception e){} //DELAY
    	if (!allPlayed()) {play(null); return;}
    	stop();
    }
   
   private boolean allPlayed(){
	   boolean r = true;
	   for (int i=0;i<played.length;i++){
		   if (played[i]==0) r=false;
	   }
	   return r;
   }
   private void log(String mes){
	   try{
		   mainClass.println(mes); //Adaptation to my mainclass (Can be safely removed)
	   }catch(Exception e){
		   System.out.println(mes);
	   }
   }
   
   public void stop(){
    	playing=false; //Simple and sweet :D
   }
   
   public void kill(){ //Kill switch
	   playing=false;
	   try{jf.close();}catch(Exception e4){}
   }
   
   private void refreshSongs(){
	   	if (new File("/Music").isDirectory()){System.out.println("From Eclipse!");
			music = new String[1];
			songs = new InputStream[1];
			played = new int[1];
			try{
				songs[0] = new FileInputStream(new File("Music/"+musicFolder+"/DONGER.WAV"));
			}catch(Exception e){
				log("ERROR: "+e.getMessage());
				return;
			}
			music[0] = "Baby Got Back";
			played[0] = 0;
			return;
	   }
		
		
		try{
		jf = new JarFile(getJarFilePath());
		if (debug) log(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		}catch(Exception e){System.out.println("Error Finding jar file?: "+e.getMessage());return;}
		Enumeration<JarEntry> entries;
		JarEntry entry;
		entries = jf.entries();
		ArrayList<JarEntry> counter = new ArrayList<JarEntry>(0);
		while(entries.hasMoreElements()){
			entry = entries.nextElement();
			if ((entry.getName().startsWith(musicFolder))&&!entry.getName().equals(musicFolder)){ //Its a song
				if (entry.getName().toLowerCase().endsWith(".wav")){//Definitely a song!
					counter.add(entry);
				}
			}
		}
		if (counter.size()<1){ //No songs were added
			System.out.println("No songs in songs folder...");
			empty = true;
			return;
		}
		empty = false;
		String n;
		music = new String[counter.size()];
		songs = new InputStream[counter.size()];
		played = new int[counter.size()];
		for (int i=0;i<counter.size();i++){
			played[i] = 0; //0=Not played   1=played
			n = counter.get(i).getName().substring(counter.get(i).getName().lastIndexOf("/")+1);
			music[i] = n;
			try {
				songs[i] = jf.getInputStream(counter.get(i));
				if (debug) log("Song added: "+n);
			} catch (Exception e) {
				//Error adding...
				log("Error adding song: "+n+"\n"+e.getMessage());
			}
		}
   }
   
   private String getJarFilePath (){
		String result="";
		int from,to;
		try{
		result = this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().toString();
		}catch(Exception e){log(e.getMessage());return null;} //ERROR
		if (result.contains("jar:file:")){
			from = 9;
		}else if (result.contains("file:")){
			from = 5;
		}else{log("Invalid Excecution!: "+result);return null;} //ERROR
		//We got the path
		//Lets rid the !/
		if (result.contains("!/")){
			to = result.indexOf("!/");
		}else{to=result.length();}
		//Trim it
		result = result.substring(from,to);
		//Lets fix the spaces (%20)
		result = result.replaceAll("%20", " ");
		//We done :D
		if (debug) log("result= "+result);
		return result;
   }
}
