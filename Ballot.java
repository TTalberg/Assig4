

//pannel demo



import javax.swing.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class Ballot extends JPanel
 {
	 

    static ArrayList<JButton> buttons = new ArrayList<JButton>();
	
	
	private static final long serialVersionUID = 1L;
	
	
	JLabel _label = new JLabel("");
	String voteNumber;
	
	
	public Ballot(String voteNumber, String voteName, String[] voteButtons)
	{
    setLayout(new GridLayout(0,1));
	this.voteNumber = voteNumber;
	_label.setText(voteName);
	add(_label);
	
		   for (int i=0; i < voteButtons.length; i++){

				JButton button = new JButton (voteButtons[i]);
				button.addActionListener(new VoteListener());
				button.setEnabled(false);
				buttons.add(button);
				add(button);
		
		

		}
	}
		

	 public void resetButtons(){
		 
		 	for (int i=0; i< buttons.size(); i++){
				
			//buttons.get(i).setForeground(Color.null);
					
			}
  
	 }  
	   
	   class VoteListener implements ActionListener{
	   
	   public void actionPerformed (ActionEvent e){
		 JButton button =(JButton) e.getSource();{
			
			resetButtons();
			button.setForeground(Color.red);} 
			}
	}
		
		
   
    public void createVoteTxt(String fileName)
	{
		try
		{
		  File file = new File(fileName);

           if (!file.exists()) 
                {
                file.createNewFile();
               }
		}catch(IOException ex){
		System.out.println("there was an error");
		}
	}
 }	