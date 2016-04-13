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
				tmp = in.nextLine().split(",");
				deck[i] = new Card(Integer.parseInt(tmp[0]), tmp[1].trim());
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
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
	private String rank, suit;
	
	Card(int rank, String suit) {
		this.suit = suit;
		switch (rank) {
		case 1:
			this.rank = "Ace";
			break;
		case 11:
			this.rank = "Jack";
			break;
		case 12:
			this.rank = "Queen";
			break;
		case 13:
			this.rank = "King";
			break;
		default:
			this.rank = Integer.toString(rank);
		}
	}
	
	public String toString() {
		return (rank + " of " + suit);
	}
}
