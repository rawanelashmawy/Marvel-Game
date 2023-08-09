package engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Component;

import model.world.*;
import engine.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.imageio.ImageIO;

public class ChooseTeamPanel extends JPanel implements ActionListener, ItemListener {
	private MainWindow mainWindow;
	private static JComboBox P1Champion1;
	private static JComboBox P1Champion2;
	private JComboBox P1Champion3;
	private JComboBox P2Champion1;
	private JComboBox P2Champion2;
	private JComboBox P2Champion3;
	private JLabel P1;
	private JLabel P2;
	private JButton Next;
    private JRadioButton AL1;
    private JRadioButton AL2;
    private JRadioButton AL3;
    private JRadioButton BL1;
    private JRadioButton BL2;
    private JRadioButton BL3; 
    private ButtonGroup A;
    private ButtonGroup B;
    private String L1;
    private String L2;
    private  Champion P1C1;
    private  Champion P1C2;
    private  Champion P1C3;
    private  Champion P2C1;
    private  Champion P2C2;
    private  Champion P2C3;
    private  Champion leader1;
    private  Champion leader2;
    private ImageIcon image; 
    private ImageIcon CaptainAmericaIcon;
    private ImageIcon DeadpoolIcon;
    private JLabel imageLabel; 
    private JButton ChampionInfoLabel;
   
	public ChooseTeamPanel(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		this.setLayout(null);
	
		P1 = new JLabel(PlayerNamePanel.getP1name() + "'s" + " Team");
		P2 = new JLabel(PlayerNamePanel.getP2name() + "'s" + " Team");
		P1.setForeground(Color.WHITE);
		P2.setForeground(Color.WHITE);
		P1.setFont(new Font("Arial", Font.BOLD, 20));
		P2.setFont(new Font("Arial", Font.BOLD, 20));
		P1.setBounds(150, 20, 300, 40);
		P2.setBounds(780, 20, 200, 40);
		this.add(P1);
		this.add(P2);
		
		
		String[] y = { "Select a Champion", "Captain America", "Electro",
				"Hulk", "Loki", "Thor", "Deadpool", "Iceman", "Quicksilver",
				"Venom", "Dr Strange", "Hela", "Ironman", "Spiderman",
				"Yellow Jacket" };
		P1Champion1 = new JComboBox<String>(y);
		P1Champion1.setBounds(100, 70, 150, 30);
		P1Champion1.addItemListener(this);
		       P1Champion1.setSelectedIndex(7);
		this.add(P1Champion1);
		P1Champion2 = new JComboBox<String>(y);
		P1Champion2.setBounds(100, 150, 150, 30);
		P1Champion2.addItemListener(this);
		       P1Champion2.setSelectedIndex(1);
		this.add(P1Champion2);
		P1Champion3 = new JComboBox<String>(y);
		P1Champion3.setBounds(100, 230, 150, 30);
		P1Champion3.addItemListener(this);
		        P1Champion3.setSelectedIndex(2);
		this.add(P1Champion3);
		P2Champion1 = new JComboBox<String>(y);
		P2Champion1.setBounds(740, 70, 150, 30);
		P2Champion1.addItemListener(this);
		        P2Champion1.setSelectedIndex(3);
		this.add(P2Champion1);
		P2Champion2 = new JComboBox<String>(y);
		P2Champion2.setBounds(740, 150, 150, 30);
		P2Champion2.addItemListener(this);
		        P2Champion2.setSelectedIndex(4);
		this.add(P2Champion2);
		P2Champion3 = new JComboBox<String>(y);
		P2Champion3.setBounds(740, 230, 150, 30);
		P2Champion3.addItemListener(this);
		        P2Champion3.setSelectedIndex(5);
		this.add(P2Champion3);
		
		AL1= new JRadioButton("Team Leader");
		AL1.setBounds(260, 70, 150, 30);
		AL1.setSelected(true);
		this.add(AL1);
		AL2= new JRadioButton("Team Leader");
		AL2.setBounds(260, 150, 150, 30);
		this.add(AL2);
		AL3= new JRadioButton("Team Leader");
		AL3.setBounds(260, 230, 150, 30);
		this.add(AL3);
		A= new ButtonGroup();
		A.add(AL1);
		A.add(AL2);
		A.add(AL3);
		BL1= new JRadioButton("Team Leader");
		BL1.setBounds(900, 70, 150, 30);
		BL1.setSelected(true);
		this.add(BL1);
		BL2= new JRadioButton("Team Leader");
		BL2.setBounds(900, 150, 150, 30);
		this.add(BL2);
		BL3= new JRadioButton("Team Leader");
		BL3.setBounds(900, 230, 150, 30);
		this.add(BL3);
		B= new ButtonGroup();
		B.add(BL1);
		B.add(BL2);
		B.add(BL3);
		
		
		
		ChampionInfoLabel = new JButton("Select champion to view info");
		ChampionInfoLabel.setFont(new Font("Arial", Font.BOLD, 20));
		ChampionInfoLabel.setBounds(340,280,600,250);
		ChampionInfoLabel.setBackground(Color.BLACK);
		ChampionInfoLabel.setForeground(Color.BLUE);
		this.add(ChampionInfoLabel);
		
		

		Next = new JButton("Let's Play!");
		Next.setFont(new Font("Arial", Font.BOLD, 20)); 
    	Next.setBounds(515,550,250,90);
    	Next.addActionListener(this);
    	this.add(Next);
    	
    	
		imageLabel = new JLabel();
		imageLabel.setIcon( new ImageIcon ("wallpaper3.jpg"));
		imageLabel.setBounds(0,0,1280,720);
		this.add(imageLabel);
	}
	
    public void ChosenLeaders (){
    	if(AL1.isSelected())
    		L1= ((P1Champion1.getSelectedItem()).toString());
        if(AL2.isSelected())
    		L1= ((P1Champion2.getSelectedItem()).toString());
    	if(AL3.isSelected())
    		L1= ((P1Champion3.getSelectedItem()).toString());
    	if(BL1.isSelected())
    		L2= ((P2Champion1.getSelectedItem()).toString());
        if(BL2.isSelected())
    		L2= ((P2Champion2.getSelectedItem()).toString());
    	if(BL3.isSelected())
    		L2= ((P2Champion3.getSelectedItem()).toString());
    	for(int i = 0; i<Game.getAvailableChampions().size();i++){
     		if((Game.getAvailableChampions().get(i).getName()).equals(L1))
     			leader1 = Game.getAvailableChampions().get(i);
     		if((Game.getAvailableChampions().get(i).getName()).equals(L2))
     			leader2 = Game.getAvailableChampions().get(i);   }
    	
    }
    
    public void getChampions (){
     	Game.getAvailableChampions();
     	for(int i = 0; i<Game.getAvailableChampions().size();i++){
     		if((Game.getAvailableChampions().get(i).getName()).equals(P1Champion1.getSelectedItem().toString()))
     			P1C1 = (Champion)  Game.getAvailableChampions().get(i);
     		if((Game.getAvailableChampions().get(i).getName()).equals(P1Champion2.getSelectedItem().toString()))
     			P1C2 = (Champion) Game.getAvailableChampions().get(i);
     		if((Game.getAvailableChampions().get(i).getName()).equals(P1Champion3.getSelectedItem().toString()))
     			P1C3 = (Champion) Game.getAvailableChampions().get(i);
     		if((Game.getAvailableChampions().get(i).getName()).equals(P2Champion1.getSelectedItem().toString()))
     			P2C1 = (Champion) Game.getAvailableChampions().get(i);
     		if((Game.getAvailableChampions().get(i).getName()).equals(P2Champion2.getSelectedItem().toString()))
     			P2C2 = (Champion) Game.getAvailableChampions().get(i);
     		if((Game.getAvailableChampions().get(i).getName()).equals(P2Champion3.getSelectedItem().toString()))
     			P2C3 = (Champion) Game.getAvailableChampions().get(i);
     	}}
    
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Next) {
			if (P1Champion1.getSelectedIndex() == 0
					|| P1Champion2.getSelectedIndex() == 0
					|| P1Champion3.getSelectedIndex() == 0
					|| P2Champion1.getSelectedIndex() == 0
					|| P2Champion2.getSelectedIndex() == 0
					|| P2Champion3.getSelectedIndex() == 0) {
				JOptionPane
						.showMessageDialog(
								this,
								"Each Player shall make a selection for each of his 3 champions",
								"Error", JOptionPane.ERROR_MESSAGE);
			}
			else if (((P1Champion1.getSelectedItem()).toString())
					.equals(((P1Champion2.getSelectedItem()).toString()))
					|| ((P1Champion1.getSelectedItem()).toString())
							.equals(((P1Champion3.getSelectedItem()).toString()))
					||((P1Champion1.getSelectedItem()).toString())
					.equals(((P2Champion1.getSelectedItem()).toString()))
					|| ((P1Champion1.getSelectedItem()).toString())
							.equals(((P2Champion2.getSelectedItem()).toString()))
					|| ((P1Champion1.getSelectedItem()).toString())
							.equals(((P2Champion3.getSelectedItem()).toString()))
					|| ((P1Champion2.getSelectedItem()).toString())
							.equals(((P1Champion3.getSelectedItem()).toString()))
					|| ((P1Champion2.getSelectedItem()).toString())
							.equals(((P2Champion1.getSelectedItem()).toString()))
					|| ((P1Champion2.getSelectedItem()).toString())
					.equals(((P2Champion2.getSelectedItem()).toString()))
					|| ((P1Champion2.getSelectedItem()).toString())
					.equals(((P2Champion3.getSelectedItem()).toString()))
					|| ((P1Champion3.getSelectedItem()).toString())
					.equals(((P2Champion1.getSelectedItem()).toString()))
					|| ((P1Champion3.getSelectedItem()).toString())
					.equals(((P2Champion2.getSelectedItem()).toString()))
					|| ((P1Champion3.getSelectedItem()).toString())
					.equals(((P2Champion3.getSelectedItem()).toString()))
					|| ((P2Champion1.getSelectedItem()).toString())
					.equals(((P2Champion2.getSelectedItem()).toString()))
					|| ((P2Champion1.getSelectedItem()).toString())
					.equals(((P2Champion3.getSelectedItem()).toString()))
					|| ((P2Champion2.getSelectedItem()).toString())
					.equals(((P2Champion3.getSelectedItem()).toString()))){
				JOptionPane.showMessageDialog(this,"A Champion can only be selected once",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				ChosenLeaders ();
				getChampions ();
				 System.out.println(P1C1.getName());
				 mainWindow.switchToGamePanel(P1C1,P1C2,P1C3,P2C1,P2C2,P2C3, leader1, leader2);}

		}
	}

	public void itemStateChanged(ItemEvent e) {

		if (e.getSource() == P1Champion1) {
		SetChampionInfoIcon(P1Champion1);

		} /*else if (e.getSource() == P1Champion2) {
		SetChampionInfoIcon(P1Champion2);
		}
		else if (e.getSource() == P1Champion3) {
		SetChampionInfoIcon(P1Champion3);
		}
		else if (e.getSource() == P2Champion1) {
		SetChampionInfoIcon(P2Champion1);
		}
		else if (e.getSource() == P2Champion2) {
		SetChampionInfoIcon(P2Champion2);
		}
		else if (e.getSource() == P2Champion3) {
		SetChampionInfoIcon(P2Champion3);
		}*/


		}

		public void SetChampionInfoIcon(JComboBox x) {
		this.validate();
		this.repaint();
		if ((x.getSelectedItem().toString()).equals("Captain America")) {
		CaptainAmericaIcon = new ImageIcon("captainInfo.JPG");
		ChampionInfoLabel.setIcon(CaptainAmericaIcon);
		}
		else if ((x.getSelectedItem().toString()).equals("Deadpool")) {
		DeadpoolIcon = new ImageIcon("smInfo.JPG");
		ChampionInfoLabel.setIcon(DeadpoolIcon);

		}
		else if ((x.getSelectedItem().toString()).equals("Dr Strange")) {
		ChampionInfoLabel.setIcon( new ImageIcon("drInfo.JPG"));

		}
		else if ((x.getSelectedItem().toString()).equals("Electro")) {
		ChampionInfoLabel.setIcon( new ImageIcon("electroInfo.JPG"));

		}
		else if ((x.getSelectedItem().toString()).equals("Ghost Rider")) {
		ChampionInfoLabel.setIcon( new ImageIcon("grInfo.JPG"));
		}
		else if ((x.getSelectedItem().toString()).equals("Hela")) {
		ChampionInfoLabel.setIcon( new ImageIcon("helaInfo.JPG"));
		}
		else if ((x.getSelectedItem().toString()).equals("Hulk")) {
		ChampionInfoLabel.setIcon( new ImageIcon("hulkInfo.JPG"));
		}

		// else if ((x.getSelectedItem().toString()).equals("Iceman")) {
		// ChampionInfoLabel.setIcon( new ImageIcon("icemanInfo.JPG"));
		//
		// }
		 else if ((x.getSelectedItem().toString()).equals("Ironman")) {
		 ChampionInfoLabel.setIcon( new ImageIcon("ironInfo.JPG"));
		
		}
		else if ((x.getSelectedItem().toString()).equals("Loki")) {
		ChampionInfoLabel.setIcon( new ImageIcon("lokiInfo.JPG"));
	 }
		else if ((x.getSelectedItem().toString()).equals("Quicksilver")) {
		 ChampionInfoLabel.setIcon( new ImageIcon("qsInfo.JPG"));
		}
		 else if ((x.getSelectedItem().toString()).equals("Spiderman")) {
		 ChampionInfoLabel.setIcon( new ImageIcon("spidermanInfo.JPG"));
		 }
		 else if ((x.getSelectedItem().toString()).equals("Thor")) {
		 ChampionInfoLabel.setIcon( new ImageIcon("thorInfo.JPG"));
		 }
		else if ((x.getSelectedItem().toString()).equals("Venom")) {
		ChampionInfoLabel.setIcon( new ImageIcon("venomInfo.JPG"));
		 }
		else if ((x.getSelectedItem().toString()).equals("Yellow Jacket")) {
		 ChampionInfoLabel.setIcon( new ImageIcon("yellowInfo.JPG"));
		}
		this.validate();
		this.repaint();
		}

	
	public String getL1() {
		return L1;
	}
	public String getL2() {
		return L2;
	}
	public String getP1Champion1(){
		return ((P1Champion1.getSelectedItem()).toString());
	}
	public  String getP1Champion2(){
		return ((P1Champion2.getSelectedItem()).toString());
	}
	public String getP1Champion3(){
		return ((P1Champion3.getSelectedItem()).toString());
	}
	public String getP2Champion1(){
		return ((P2Champion1.getSelectedItem()).toString());
	}
	public String getP2Champion2(){
		return ((P2Champion2.getSelectedItem()).toString());
	}
	public String getP2Champion3(){
		return ((P2Champion3.getSelectedItem()).toString());
	}

	public  Champion getP1C1() {
		return P1C1;
	}

	public  Champion getP1C2() {
		return P1C2;
	}

	public  Champion getP1C3() {
		return P1C3;
	}

	public  Champion getP2C1() {
		return P2C1;
	}

	public  Champion getP2C2() {
		return P2C2;
	}

	public  Champion getP2C3() {
		return P2C3;
	}
	public  Champion getLeader1() {
		return leader1;
	}
	public  Champion getLeader2() {
		return leader2;
	}
	
}
