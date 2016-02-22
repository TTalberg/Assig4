/**  
	Trever Talberg.
	Black Jack Game 
	Date started 2-2-2016 
	Last Revised 2-22-2016
	
   */
   

   
   /*
     Basic instructions :
make a black jack game for Blipz and Chitz and win all the shmeckles 
  
  
  Ive tried all weekend to get it to save to the txt file and I have not figured it out.
  I left a lot of comment out code in there to give an idea of what I was trying
  mostly trying to get the old file to write to 4 arrays then update the arrays with the
  new player data,  then write that data back to the text file..
  its the sort of thing that will come to me about an hour after I turn in the project.
  
  I also see the player stuff is neantly Left Aligned in example... and I didnt have time to look
  for that bit of code.  
 
*/


 import java.util.Random; 	//I'll need to random from the text file...
 import java.io.*;			// the IOException is an error without this...
 import java.util.Scanner;  //we need the keyboard
 import java.util.*;

public class Blackjack

{
 

	public static void main (String[] args) throws IOException
			  
	{
		String playerName, fromFile;     		//for player name input
		boolean playerFound = false;			//for finding the player on the saved list
		boolean gameOver = false;				//for breaking ending the game
		boolean playGame = true;				//for player to continue or End the game.
		boolean playerStays = false;			// poker term for Hit or stay function
		boolean playerBusted = false;			// another term, its a losing condition of the loop
		boolean playerWon = true;				// means the player won the round 
		Player gambler = new Player();			// saves gambler 
		
		Card[] playerHand = new Card[22];		// was going to array a whole deck, but didnt
		Card[] dealerHand = new Card[22];		// was going to array a whole deck, but didnt
		
		int newGamesWon=0;						//int for tracking games won
		int newPlayed=0;						//int for tracking games played
		
		int plyrHand = 0;						//int for tracking the value of cards in players hand
		int delrHand = 0;						//int for tracking the value of cards in dealers hand
		double amountBet;						// double for tracking the money on the line.
		double newCredit = 0;					// double for the amount bet effecting the player
		
		int j;									//this int was used for incrementing 
		int k;									// so was this int... i think
		int playerScore;						//int for the total value of players hand	
		int dealerScore;						// int for total value of dealers hand 
		
		File infile = new File("Name.txt");		// the saved game file I read the data from.
		BufferedReader s1 = new BufferedReader(new FileReader(infile));
		
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Player Please enter your name: > ");
		playerName = (keyboard.readLine());

		
			//this loop is looking for the saved game data and will store it as the players/gamblers data
		while(((fromFile = s1.readLine()) != null) && (!playerFound)) {
			if (playerName.equalsIgnoreCase(fromFile)){
				gambler.setName(playerName);
				gambler.setGamesPlayed(Integer.parseInt(s1.readLine()));
				gambler.setGamesWon(Integer.parseInt(s1.readLine()));
				gambler.setCredit(Double.parseDouble(s1.readLine()));
				playerFound = true;
				System.out.println("\nWelcome back."+ playerName);
				System.out.println("Everything is just how you left it.\n");
			}
		}
			s1.close();
		if (playerFound == false){   //if the player was not found they are new.
			gambler.setName(playerName);
			System.out.println("Have fun on your first time playing Infinite Blackjack.\n");
		}	
			
			
			System.out.println("\nName: "+ gambler.getName()+ "\nYou have won: "+gambler.getWon());
			System.out.print("You have played: "+gambler.getPlayed()+"\nYour account contains: $");
			System.out.println(String.format("%.2f", gambler.getCredit()));
			System.out.print("\nPlay a hand? (y/n) > ");
			playGame = input();
			if (playGame == false){gameOver = true;}
			
		do{
			
			
		
		//run the loop till they quit or go broke, do while loop.
		// player get two card / dealer gets two card,  loop again for HIT
		
	
			
			System.out.print("\nEnter amount to bet > $");
			amountBet = bet(gambler.getCredit());
			System.out.println("PLAYER DEAL");
			
			Card playerCard1 = new Card();
			j = playerCard1.getCardValue();	
			playerCard1.displayCard();
				//System.out.println(j);
			Card playerCard2 = new Card();
			j = j+ playerCard2.getCardValue();
			playerCard2.displayCard();
			System.out.println("\nScore: "+ j);
			playerScore = j;
			j = 0;
				
			//hit or stay method
			do{
				System.out.print("Hit or Stay? > ");
				playerStays = hitStay();
					
					
					if (playerStays == false ){
						playerScore = drawCards(playerScore);
						System.out.println("\n Player Score: "+ playerScore);
						if (playerScore <= 21){
							playerBusted = false;}
							
						if (playerScore > 21){
							playerBusted = true;
							newPlayed = gambler.getPlayed() + 1;
							gambler.setGamesPlayed(newPlayed);
						}
						
					}
			//}while ((playerStays == false) && (playerBusted == false)) ;
			
				if (playerStays == true){   //Player stays and not busted, so its the dealers turn to get cards.
						
						System.out.println("DEALER DEAL");
						
						Card playerCard3 = new Card();
						j = playerCard3.getCardValue();	
						playerCard3.displayCard();
						//System.out.println(j);
						
													// dealers turn to get cards.
						Card playerCard4 = new Card();
						j = j+ playerCard4.getCardValue();
						playerCard4.displayCard();
						
						
						System.out.println("\nScore: "+ j);
						dealerScore = j;
						j = 0;
					while(dealerScore <= 17) {
						System.out.println("\nDealer Hits!" );
						dealerScore = drawCards(dealerScore);
						System.out.println("\nDealer Score: "+ dealerScore);
						} 
					if ((dealerScore > 17)&& (dealerScore <22)){
						if (dealerScore > playerScore){
							System.out.println("\nDealer wins ");
							playerWon = false;
							newPlayed = gambler.getPlayed() + 1;
							gambler.setGamesPlayed(newPlayed);
							//newCredit = gambler.getCredit() - amountBet;
							//gambler.setCredit(newCredit);
							
							
						}
						if (dealerScore < playerScore){
							System.out.println("\nPlayer Wins");
							playerWon = true;
							//newPlayed = gambler.getPlayed() + 1;
							//gambler.setGamesPlayed(newPlayed);
							//newGamesWon = gambler.getWon() + 1;
							//gambler.setGamesWon(newGamesWon);
							
						}
						if (dealerScore == playerScore){
						System.out.println("Push ");
						}
					}
					if (dealerScore >= 22){
						System.out.println("\nDealer busted");
						playerWon = true;
					}
					
						
					}
					
								//outcome conditions...  Player won or lost and the money gets moved.
			}while ((playerStays == false) && (playerBusted == false)) ;
				if (playerBusted){
					System.out.println("Player busted.");
					//newCredit = amountBet- gambler.getCredit() ;
					//gambler.setCredit(newCredit);
					playerWon =  false;
				}
				
				if (playerWon){   //player won and gets the money bet and his scores change
					newCredit = gambler.getCredit()+ amountBet;
					gambler.setCredit(newCredit);
					newPlayed = gambler.getPlayed() + 1;
					gambler.setGamesPlayed(newPlayed);
					newGamesWon = gambler.getWon() + 1;
					gambler.setGamesWon(newGamesWon);
					
					
					}
				if (!playerWon){  //player lost and the money is lost and their scores are changed.
					
					newCredit = gambler.getCredit() - amountBet;
					gambler.setCredit(newCredit);
					newGamesWon = gambler.getWon();
					gambler.setGamesWon(newGamesWon);
					}
				
				
					System.out.println("\nName: "+ gambler.getName()+ "\nYou have won: "+gambler.getWon());
					System.out.println(String.format("You have played: "+gambler.getPlayed()+"\nYour account contains: $%.2f", gambler.getCredit()));
					//System.out.print("You have played: "+gambler.getPlayed()+"\nYour account contains: $%.2f", gambler.getCredit());
					//String.format("%.2f", gambler.getCredit());
					System.out.print("\nContinue playing? (y/n) > ");
					playGame = input();
				
				
					//System.out.println("xxx testing xxx");
			if (playGame == false){
				gameOver = true;
				}
				
		}while ((gameOver ==  false)) ;
		
		/*
		if(playerFound){
			try{
			FileWriter fr = new FileWriter("Names");
			BufferedWriter br = new BufferedWriter(fr);
			PrintWriter out = new PrintWriter(br);
			for (int i=0; i < arraysize.lenght; i++){
				if(plyrNames[i] !=null)
				out.write(plyrNames [i]) ;
				out.write(plyrGames [i]);
				out.write(plyrWinns [i]);
				out.write(plyrMoney [i]);
			
			
					out.write("\n");
		}out.close();
			
			
			
			
		}catch(IOException e){System.out.println("and error was found");}
		
		//this ends the game
			//System.out.print("keep playing? (y/n) > ");
			//playGame = input();
			
		}	
			*/
			
		
		//saveGametoFile();
		/*
		try{
			FileWriter fr = new FileWriter("Names");
			BufferedWriter br = new BufferedWriter(fr);
			PrintWriter out = new PrintWriter(br);
			for (int i=0; i<string.lenght; i++){
				if(plyrNames[i] !=null)
				out.write(plyrNames [i]) ;
				out.write(plyrGames [i]);
				out.write(plyrWinns [i]);
				out.write(plyrMoney [i]);
			
			
					out.write("\n");
		}out.close();
			
			
			
			
		}catch(IOException e){System.out.println("and error was found");}
		
		//this ends the game
			//System.out.print("keep playing? (y/n) > ");
			//playGame = input();
			
			*/
			
		if (playGame == false)
		System.out.print("\nThank you for playing Infinite Blackjack! \n");
	
	}
	
		/*
public static int saveGametoFile(){
	
	
	
	
	
			

		
		string fileName = "Names.txt";
		StreamReader file = new StreamReader(fileName);
		
		for (int i=0; i < arraysize; i++ ){
			plyrNames[i] = 
		}
			
		
		
		
		boolean savedGameFound = false;
		//Scanner keyboard = new Scanner(System.in);
		File file2 = new File("SavedGame.txt");
		
		Scanner s2 = new Scanner(file2);
		Scanner s1 = new Scanner(file2);
		FileReader file = new FileReader("SavedGame.txt");
		BufferedReader r1 = new BufferedReader(file);
		
		
		String text = "";
		String q = r1.readLine();
		while (q != null)
			{	
			text += q;
			q = r1.readLine();
			} 	
			System.out.println(text);	//testing that its reading the save file
			
		int lines = 0;
		int textFilesize = 0;	
			
			
			while(s1.hasNextLine())
			{
				lines = lines + 1;
				textFilesize = lines;
				s1.next();
			}	
		int arraysize = (textFilesize/4)+1;
		
		
		String[] plyrNames = new String [arraysize];
		int[] plyrGames = new int [arraysize];
		int[] plyrWinns = new int [arraysize];
		double[] plyrMoney = new double [arraysize];
		
		
		Player[] playerArray= new Player[arraysize];
		
		
			for (int i = 0; i < arraysize; i = i + 1)
					{
					plyrNames [i] = s2.next();
					plyrGames [i] = s2.nextInt();
					plyrWinns [i] = s2.nextInt();
					plyrMoney [i] = s2.nextDouble();
					}
					s1.close();
					s2.close();
					
				
		System.out.println(arraysize);			
		System.out.println(textFilesize);
				
		System.out.println("its reading the file");
		
		for (int i=0; i< plyrNames.length; i++){
		System.out.println("the Names of previous players are "+  plyrNames[i]);			
		System.out.println("the games played by them are "+  plyrGames[i]);					
		System.out.println("the number of hands they have won: "+  plyrWinns[i]);	
		System.out.println("the amount of money they have to bet "+  plyrMoney[i]);		
		}
		
		//System.out.print("Player, Please enter your name:");
		String playerPlaying = gambler.getName();
		
		//System.out.println(playerPlaying);
		

		int savedGameSpace=0;
		
		while(((fromFile = s1.readLine()) != null) && (!savedGameFound)) 
	
				{ int i = 0;
					String nameX = plyrNames[i];
					
					if (playerPlaying.equalsIgnoreCase(nameX))
					{
					savedGameSpace = i;
					System.out.println("Your game is saved in slot " + i );
					savedGameFound = true;
					
					}
					else {i++;
						System.out.println("Your game will be saved in slot "+ i );
						}
					
				}
				
		
		
			
		if (savedGameFound)
			{
			
				plyrNames [savedGameSpace] = gambler.getName();
				plyrGames [savedGameSpace] = gambler.getPlayed();
				plyrWinns [savedGameSpace] = gambler.getWon();
				plyrMoney [savedGameSpace] = gambler.getCredit();
			
			}
		if (savedGameFound == false ){
				plyrNames [i] = gambler.getName();
				plyrGames [i] = gambler.getPlayed();
				plyrWinns [i] = gambler.getWon();
				plyrMoney [i] = gambler.getCredit();
				
				
			}
			
		try{
			for (int i = 0; i < arraysize; i++ )
			{
				w1.write(plyrNames [i]+ "\n");
				w1.write(plyrGames [i]+ "\n");
				w1.write(plyrWinns [i]+ "\n");
				w1.write(plyrMoney [i]+ "\n");
				
			}
		
	}catch(IOException ex){System.out.println("there was an error");}

}		
	*/	


		
public static int drawCards(int score) //drawing cards after the first two returning values
	{
		int j=0;
		System.out.print("Card dealt");
		Card playerCard1 = new Card();
		j = playerCard1.getCardValue();	
		playerCard1.displayCard();
		j = j + score;
		return j;
		
	}
		
	
	
public static boolean hitStay()						//Boolean for Hit or Stay
	{
		Scanner answer = new Scanner(System.in); 	// For player input
		String input;								// To store the reply
		boolean status = true;						// To return to main
		boolean invalid = false;					// In case of invalid input
		
		do
		{
		
			input = answer.nextLine();
		
			if (input.equalsIgnoreCase("S")){ // The player wants an encore
				status = true;
				invalid = false;
			}
			else if (input.equalsIgnoreCase("H")){	// The player wants to stop
				status = false;
				invalid = false;
			}
			else{ 	// The player's not paying attention
				invalid = true;
				System.out.println("Please enter H OR S ");
			}
		} while (invalid == true); // Repeat until a valid answer is given
		
		return status; // Return the player's response
	}	
	
public static boolean input()
	{
		Scanner answer = new Scanner(System.in); 	// For player input
		String input;								// To store the reply
		boolean status = true;						// To return to main
		boolean invalid = false;					// In case of invalid input
		
		do
		{
		
			input = answer.nextLine();
		
			if (input.equalsIgnoreCase("Y")){ // The player wants an encore
				status = true;
				invalid = false;
			}
			else if (input.equalsIgnoreCase("N")){	// The player wants to stop
				status = false;
				invalid = false;
			}
			else{ 	// The player's not paying attention
				invalid = true;
				System.out.println("Please enter y OR n ");
			}
		} while (invalid == true); // Repeat until a valid answer is given
		
		return status; // Return the player's response
	}
	
	
public static double bet(double funds)				//code for the bet to be ONLY numbers
	{
		Scanner answer = new Scanner(System.in); 	// For player input
		double number=0;								// To store the reply
		boolean status = true;						// To return to main
		boolean invalid = false;					// In case of invalid input
		String typing;
		invalid = true;
		
	do{
		
			try {
				number = answer.nextDouble();
				
				if (number > 0 && number < funds)
				{ // The player wants an encore
				status = true;
				invalid = false;
				}
				else if (number < 0 || number > funds)
				{	// The player wants to stop
				status = false;
				invalid = true;
				System.out.print("Please enter an amount you can bet with.");
				}
				
				}catch (InputMismatchException e)
				{
				System.out.println("Please enter a number.");
				answer.next();//ditch bad input
				System.out.print("Enter amount to bet > ");
				
				}	
			
	} while (invalid == true); // Repeat until a valid answer is given
		
		return number; // Return the player's response
	}
}

	
		
		
		