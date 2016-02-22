
public class Player
					
{
	
	String name;
	double money;
	int gamesPlayed;
	int gamesWon;
	//int ranking;
	
	Player(){
		name = "unknown";
		gamesWon = 0;
		money = 100;
		gamesPlayed = 0;
	}

	Player(String N, double M, int gp, int gw){
		name = N;
		money = M;
		gamesPlayed = gp;
		gamesWon = gw;
	}
	
	//mutators go here
	void setName(String N){
		name = N;
	}
	void setGamesPlayed(int played){
		gamesPlayed = played;
	}
	void setGamesWon(int won){
		gamesWon = won;
	}
	void setCredit(double credits){
		money = credits;
	}
	
	//getters
	public String getName(){
		return name;
	}
	public int getWon(){
		return gamesWon;
	} 
	public int getPlayed(){
		return gamesPlayed;
	}
	public double getCredit(){
		return money;
	}
	
	//class methods
	public void played(int played){
		gamesPlayed += played;	
	}
	public void won(int winner){
		gamesWon += winner;
	}
	public boolean money(double credits){
		money += credits;
		if (money <= 0) return true;
		else return false;	
	}
}

