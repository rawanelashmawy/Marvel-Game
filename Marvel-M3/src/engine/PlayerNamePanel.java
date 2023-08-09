package engine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

//import com.sun.prism.Image;

import java.awt.Color;



public class PlayerNamePanel extends JPanel implements ActionListener{
    private MainWindow mainWindow;
    private JLabel P1;
    private JLabel P2;
    private static  JTextField P1name;
    private static  JTextField P2name;
    private JButton playButton;
    private JLabel trial;
    private ImageIcon image; 
    public static final Color VERY_LIGHT_BLUE = new Color(51,153,255);
    /*protected void paintComponent(Graphics g){
    	super.paintComponent (g);
    	g.drawImage((ImageIcon)"C:\\Users\\dell9\\Desktop\\avengers-article.PNG",0,0,null);
    }*/
//    ImageIcon icon= new ImageIcon("C:\\Users\\dell9\\Desktop\\avengers-article.PNG");
//    JLabel thumb= new JLabel();
//    thumb.setIcon(icon);
    
	public PlayerNamePanel(MainWindow mainWindow){
    	this.mainWindow= mainWindow;
    	this.setLayout(null);
    	P1 = new JLabel("First Player Name");
    	P2= new JLabel("Second Player Name");
    	P1.setFont(new Font("Arial", Font.BOLD, 20));
    	P2.setFont(new Font("Arial", Font.BOLD, 20));
    	P1.setBounds(260, 370, 220, 70);
    	P2.setBounds(810, 370, 220, 70);
    	P1.setForeground(Color.WHITE);
    	P2.setForeground(Color.WHITE);
    	this.add(P1);
    	this.add(P2);
    	P1name = new JTextField ();
    	P2name = new  JTextField ();
    	P1name.setBounds(250, 440, 280, 60);
    	P2name.setBounds(800, 440, 280, 60);
    	P1name.setFont(new Font("Arial",Font.PLAIN, 16));
    	P2name.setFont(new Font("Arial", Font.PLAIN, 16));
    	P1name.setBackground(Color.LIGHT_GRAY);
    	P2name.setBackground(Color.LIGHT_GRAY);
    	this.add(P1name);
    	this.add(P2name);
    	playButton= new JButton("Start Game");
    	playButton.setFont(new Font("Arial", Font.BOLD, 26)); 
    	playButton.setBounds(515,550,250,90);
    	playButton.addActionListener(this);
    	playButton.setBackground(Color.red);
    	playButton.setForeground(Color.WHITE);
    	this.add(playButton);
    	
    	image= new ImageIcon ("wallpaper3.jpg");
		trial = new JLabel();
		trial.setIcon(image);
		trial.setBounds(0,0,1280,720);
		this.add(trial);
    }
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== playButton){
			if (P1name.getText().equals("")|| P2name.getText().equals(""))
				JOptionPane.showMessageDialog(this, "You Must Enter A Name For Both Players", "Error", JOptionPane.ERROR_MESSAGE);
			else 
				mainWindow.switchToChooseTeamPanel();
		
		
	}
		

}

	public static  String getP1name() {
		String s= P1name.getText();
		return s;
	}

	public static  String getP2name() {
		return P2name.getText();
	}}
