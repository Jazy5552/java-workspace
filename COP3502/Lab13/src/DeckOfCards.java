import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class DeckOfCards {
	public static void main(String[] args) {
		long start, end;
		Card deck[] = new Card[52]; //Assuming standard deck
		String[] tmp;
		Scanner in;
		try {
			in = new Scanner(new File("Cards.txt"));
		
			for (int i=0; i<deck.length; i++) {
				tmp = in.nextLine().split(","); //Split rank and suit
				deck[i] = new Card(Integer.parseInt(tmp[0]), tmp[1].trim()); //Convert rank to int and construct
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("File 'Cards.txt' not found!");
			//e.printStackTrace();
			return;
		}
		
		viewDeck(deck);
		start = System.currentTimeMillis();
		shuffleDeck(deck);
		end = System.currentTimeMillis();
		viewDeck(deck);
		System.out.println("Shuffle Runtime: " + (end - start) + "ms");
	}
	
	private static void shuffleDeck(Card deck[]) {
		Random r = new Random(System.currentTimeMillis());
		int ran;
		Card tmp;
		for (int i=0; i<10000; i++) {
			for (int j=0; j<deck.length; j++) {
				//Swap current index with random one
				ran = r.nextInt(deck.length - 1);
				tmp = deck[j];
				deck[j] = deck[ran];
				deck[ran] = tmp;
			}
		}
	}
	
	private static void viewDeck(Card deck[]) {
		System.out.println("Deck of cards:");
		for (int i=0; i<deck.length; i++) {
			System.out.println(deck[i]);
		}
	}
}

class Card {
	private int rank;
	private String suit;
	
	Card(int rank, String suit) {
		this.suit = suit;
		this.rank = rank;
	}
	
	public String toString() {
		String sRank;
		switch (rank) {
		case 1:
			sRank = "Ace";
			break;
		case 11:
			sRank = "Jack";
			break;
		case 12:
			sRank = "Queen";
			break;
		case 13:
			sRank = "King";
			break;
		default:
			sRank = Integer.toString(rank);
		}
		return (sRank + " of " + suit);
	}
}
