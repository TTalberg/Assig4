import java.util.*;


public class Card

{
	String[] suits = {"h","s","c","d"};
	int[] values = {2,3,4,5,6,7,8,9,10,10,10,10,11};
	String[] types = {"2","3","4","5","6","7","8","9","T","J","Q","K","A"};
	String cardSuit;
	String cardType;
	int cardValue;
	int v;
	
	Card(){
		generateCard();
	}
	
	//getters
	public String getCardSuit(){
		return cardSuit;
	}
	public String getCardType(){
		return cardType;
	}
	public int getCardValue(){
		return cardValue;
	} 
	
	//class Methods
	public void displayCard(){
		System.out.print(" "+ cardType+""+ cardSuit);
	}
	public void generateCard(){
		Random ran = new Random();
		Random val = new Random();
		v = val.nextInt(13);
		cardSuit = suits[ran.nextInt(4)];
		cardValue = values[v];
		cardType = types[v];
		//displayCard();
		//return cardValue,cardSuit;
	}
	
}
	
	
	