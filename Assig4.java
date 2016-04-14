import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;

public class Assig4 extends JFrame{


	static ArrayList<Ballot> ballotList = new ArrayList<>();
	static JButton logIn;
	static JButton castVote;
	static JTextField name;
	static String storeName = "";
	boolean unlockButtons;
	static Ballot b;
	static String voteNumber;
	
	public Assig4(){
		int size = ballotList.size();
		 //setLayout(new GridLayout(0,0));
		 setLayout(new FlowLayout());
	 	 setVisible(true);	
	     setSize(600, 400);
		 setTitle("eVote Version 1.0");
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		for (int i=0; i < ballotList.size(); i++){
			add(ballotList.get(i));
			
		}
		logIn = new JButton("Log In");
		castVote = new JButton("Cast Vote");
		castVote.setEnabled(unlockButtons);
		ActionListener logInListener = new LogInListener();
		logIn.addActionListener(logInListener);
		
		ActionListener castVoteListener = new CastVoteListener();
		castVote.addActionListener(castVoteListener);
	
		add(logIn);
		add(castVote);
	}
	

	
	
	private static ArrayList<String> readFileToArray() 
	{
		
		 Scanner kBrd = new Scanner(System.in);  		
		 String line = null;
		 ArrayList<String> bData = new ArrayList<String>();
	try
		{

		 System.out.println("Please input the name and location of ballot: ");
		 String fileLocation = kBrd.next();
		 FileReader fileReader = new FileReader(fileLocation);
		 BufferedReader br = new BufferedReader(fileReader);

		 
		 while(( line = br.readLine() ) != null)  {

			 bData.add(line);
	
			}
		br.close();
		}catch(IOException ex){
		System.out.println("there was an error");}	
		
		
		return bData;
	
	}
	
	class LogInListener implements ActionListener{  
		public void actionPerformed(ActionEvent e){ 
		if(e.getSource()== logIn)
			{   String voteIDS;
				voteIDS = JOptionPane.showInputDialog("Please Enter your Voter ID");
				 int voteIDint = Integer.parseInt(voteIDS);
					 
			String line = null;
			String tempLine;
			ArrayList<String> vData = new ArrayList<String>();
			ArrayList<String> tmpArray = new ArrayList<String>();
	try{ 
				boolean status = false;
				String t = "true" ;
				String f = "false";
				File infile = new File("voters.txt");
				BufferedReader b1 = new BufferedReader(new FileReader(infile));
	
				while(( line = b1.readLine() ) != null)  {

			 tempLine = line;
			 String[] StringtmpArray = tempLine.split(":");
			 String tempInt = StringtmpArray[0];
			 int var2 = Integer.parseInt(tempInt);
			 if (var2 == voteIDint)
				{
				 if (StringtmpArray[2].equals(t)) {JOptionPane.showMessageDialog(null, StringtmpArray[1]+ "You have already voted."); } 

				 if (StringtmpArray[2].equals(f)){
					JOptionPane.showMessageDialog(null, StringtmpArray[1]+ "Please cast your vote.");
					
					
					logIn.setEnabled(false);
					castVote.setEnabled(true);
				
					
					for (int i=0; i< b.buttons.size(); i++){
				
						b.buttons.get(i).setEnabled(true);
					
					}
					 
					 
					 
					 
					 
					
					StringtmpArray[2] = "true";
					status = true; }
				}
				if (var2 != voteIDint) 
				{JOptionPane.showMessageDialog(null, "Your name is not on the voting list.");} 
			}
				b1.close();
		
		
				}catch(IOException ex){
				System.out.println("there was an error");}	
			}
		
		}
	}
	
	class CastVoteListener implements ActionListener{  
		public void actionPerformed(ActionEvent e)
		{ if(e.getSource()== castVote)
			{
				
				
				JOptionPane.showMessageDialog(null, "Casting Your Vote" );
	
		
		try{
		  File file = new File(voteNumber);

           if (!file.exists()) 
                {
                file.createNewFile();
               }
		}catch(IOException ex){
		System.out.println("there was an error");
		}
				
				
			}
		}
	}

	
	
	
    public void CreateVoteTxt(String voteNumber)
	{
		try
		{
		  File file = new File(voteNumber);

           if (!file.exists()) 
                {
                file.createNewFile();
               }
		}catch(IOException ex){
		System.out.println("there was an error");
		}
	}
	
	
	
		//renameto method, deleteMothod close Scanner Pwrter
		
	 public static void main (String[] args) throws IOException {
	

		ArrayList<String> bData = new ArrayList<String>();
		bData = readFileToArray();
	    
		
		String tempString = bData.get(0);
		int var1 = Integer.parseInt(tempString);
		
		for (int i= 0; i< var1; i++){
		 tempString = bData.get(i+1);
		 String[] tempArray = tempString.split(":");
		 String[] voteArray = tempArray[2].split(",");
		 
		
		String voteNumber = tempArray[0];
		String voteName   = tempArray[1];
	
		Ballot b = new Ballot(voteNumber,voteName,voteArray);
	 
		ballotList.add(b);
	    }
		
		new Assig4();
	    
	
	}
 }    	