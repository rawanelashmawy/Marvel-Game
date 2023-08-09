package engine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.abilities.*;
import model.world.*;
import engine.*;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;

public class MainWindow extends JFrame implements ActionListener {

	private PlayerNamePanel playerNamePanel;
	private ChooseTeamPanel chooseTeamPanel;
	private JLabel P1;
	private JLabel P2;
	private ImageIcon image;
	private JPanel gamePanel;
	private JPanel boardPanel;
	private JPanel MoveAttackPanel;
	private JLabel bgpanel;
	private JButton[][] boardGrid;
	private JButton move;
	private JButton attack;
	private JComboBox ChampionAbilities;
	private ImageIcon coverImage;
	private Game game;
	private Player player1;
	private Player player2;
	private Direction direction;
	private JLabel p1nameGameBoard;
	private JLabel p2nameGameBoard;
	private JButton EndTurn;
	private JButton Cast;
	private JLabel pleaseSelect;
	private JTextArea Champinfo;
	private JComboBox Directions;
	private boolean isLeader;
	private String effects;
	private JButton LeaderAbility;
	private JTextArea queue;
	private String order;
	

	public MainWindow() {
		try {
			game.loadAbilities("Abilities.csv");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			game.loadChampions("Champions.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		playerNamePanel = new PlayerNamePanel(this);
		this.getContentPane().add(playerNamePanel);
		this.setSize(1280, 720);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void switchToChooseTeamPanel() {
		String s = playerNamePanel.getP1name();
		P1 = new JLabel(s + "'s" + " Team");
		P2 = new JLabel(playerNamePanel.getP2name() + "'s" + " Team");
		this.getContentPane().remove(playerNamePanel);
		chooseTeamPanel = new ChooseTeamPanel(this);
		this.setSize(1280, 720);
		this.setVisible(true);
		this.getContentPane().add(chooseTeamPanel);
		this.validate();
		this.repaint();
	}

	public void switchToGamePanel(Champion p1c1, Champion p1c2, Champion p1c3,
			Champion p2c1, Champion p2c2, Champion p2c3, Champion leader1,
			Champion leader2) {
		this.getContentPane().remove(chooseTeamPanel);
		player1 = new Player(PlayerNamePanel.getP1name());
		player2 = new Player(PlayerNamePanel.getP2name());
		player1.getTeam().add(p1c1);
		player1.getTeam().add(p1c2);
		player1.getTeam().add(p1c3);
		player2.getTeam().add(p2c1);
		player2.getTeam().add(p2c2);
		player2.getTeam().add(p2c3);
		player1.setLeader(leader1);
		player2.setLeader(leader2);

		game = new Game(player1, player2);

		try {
			game.loadAbilities("Abilities.csv");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			game.loadChampions("Champions.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		gamePanel = new JPanel();
		boardPanel = new JPanel();
		this.setSize(1280, 720);
		gamePanel.setLayout(null);
		boardPanel.setLayout(new GridLayout(5, 5));
		boardPanel.setBounds(50, 50, 650, 630);

		gamePanel.add(boardPanel);
		boardGrid = new JButton[5][5];

		for (int i = game.getBoardheight() - 1; i >= 0; i--) {
			for (int j = 0; j < game.getBoardwidth(); j++) {
				boardGrid[i][j] = new JButton();
				boardGrid[i][j].setBackground(Color.gray);
				boardGrid[i][j].addActionListener(this);
				boardPanel.add(boardGrid[i][j]);

			}
		}
		setBoard();

		/*
		 * MoveAttackPanel = new JPanel(); MoveAttackPanel.setLayout(null);
		 * 
		 * MoveAttackPanel.setBounds(720,50,540,600);
		 * gamePanel.add(MoveAttackPanel);
		 */
		p1nameGameBoard= new JLabel("Player1: " + PlayerNamePanel.getP1name());
		p1nameGameBoard.setFont(new Font("Arial", Font.BOLD, 20));
		p1nameGameBoard.setBounds(750, 20, 150, 100);
		p1nameGameBoard.setForeground(Color.red);
		gamePanel.add(p1nameGameBoard);
		
		p2nameGameBoard= new JLabel("Player2: " + PlayerNamePanel.getP2name());
		p2nameGameBoard.setFont(new Font("Arial", Font.BOLD, 20));
		p2nameGameBoard.setBounds(1040, 20, 150, 100);
		p2nameGameBoard.setForeground(Color.blue);
		gamePanel.add(p2nameGameBoard);
		
		move = new JButton("Move");
		move.setFont(new Font("Gil Sans", Font.BOLD, 30));
		move.setBounds(730, 100 , 200, 100);
		move.addActionListener(this);
		move.setFocusable(false);
		gamePanel.add(move);

		attack = new JButton("Attack");
		attack.setFont(new Font("Arial", Font.BOLD, 30));
		attack.setBounds(1030, 100 , 200, 100);
		attack.addActionListener(this);
		attack.setFocusable(false);
		gamePanel.add(attack);
		
		pleaseSelect= new JLabel("Choose an ability to cast");
		pleaseSelect.setFont(new Font("Arial", Font.BOLD, 15));
		pleaseSelect.setBounds(730, 210 , 210, 25);
		gamePanel.add(pleaseSelect);
		
		ChampionAbilities = new JComboBox();
		ChampionAbilities.setBounds(730, 240, 500, 50);
		CurrentChampionAbilities();
		gamePanel.add(ChampionAbilities);
		
		Cast = new JButton("Cast This Ability");
		Cast.setBounds(1080,295,140,30);
		Cast.addActionListener(this);
		Cast.setFocusable(false);
		gamePanel.add(Cast);
		
		LeaderAbility = new JButton("Use Leader Ability");
		LeaderAbility.setFont(new Font("Arial", Font.BOLD, 20));
		LeaderAbility.setBounds(850,355,240,50);
		LeaderAbility.addActionListener(this);
		LeaderAbility.setFocusable(false);
		gamePanel.add(LeaderAbility);
		
		EndTurn= new JButton("End Turn");
		EndTurn.setFont(new Font("Arial", Font.BOLD, 20));
		EndTurn.setBounds(850,415,240,50);
		EndTurn.addActionListener(this);
		EndTurn.setFocusable(false);
		gamePanel.add(EndTurn);
		
	
		
		Champinfo= new JTextArea ();
		Champinfo.setText(CurrentChampionInfo());
		Champinfo.setEditable(false);
		Champinfo.setToolTipText("Current Champion Details");
		JScrollPane p = new JScrollPane(Champinfo);
		p.setBounds(730, 480, 270, 200);
		gamePanel.add(p);
		
		queue= new JTextArea();
		queue.setText(queueMaker());
		queue.setEditable(false);
		queue.setToolTipText("TurOrder Queue (Champions Turns)");
		JScrollPane q = new JScrollPane(queue);
		q.setBounds(1010, 480, 270, 200);
		gamePanel.add(q);
		
		image = new ImageIcon("wallpaper3.jpg");
		bgpanel = new JLabel();
		bgpanel.setIcon(image);
		bgpanel.setBounds(0, 0, 1280, 720);
		gamePanel.add(bgpanel);
		this.setVisible(true);
		this.getContentPane().add(gamePanel);
		
		
		this.validate();
		this.repaint();

	}
	public String queueMaker(){
		ArrayList<Champion> temp= new ArrayList();
		order="";
		for(int i=0;!game.getTurnOrder().isEmpty(); i++){
			Champion x=(Champion) game.getTurnOrder().remove();
		    temp.add(x);
		order=order+ x.getName()+"\n";
		}
		for(int i=0;i<temp.size(); i++){
			game.getTurnOrder().insert(temp.get(i));
		}
		return order;
	}

	public void setBoard() {
		for (int i = game.getBoardheight() - 1; i >= 0; i--) {
			for (int j = 0; j < game.getBoardwidth(); j++) {
				if (game.getBoard()[i][j] instanceof Champion) {
					Champion champion = (Champion) game.getBoard()[i][j];
					if (game.getFirstPlayer().getTeam().contains(champion)) {
						boardGrid[i][j].setBackground(Color.red);
						setChampionImages(champion);
					}
					if (game.getSecondPlayer().getTeam().contains(champion)) {
						boardGrid[i][j].setBackground(Color.blue);
						setChampionImages(champion);
					}
					if (champion == game.getCurrentChampion()) {
						boardGrid[i][j].setBackground(Color.green);
						setChampionImages(champion);
					}
					if(champion == player1.getLeader() || champion == player2.getLeader())
						 isLeader= true;
					 else
						isLeader=false;
					effects="<html>";
					for(int k=0; k<champion.getAppliedEffects().size(); k++){
						effects=effects+"<br>";
						effects+=champion.getAppliedEffects().get(k).getName()+"<br";
						effects+= "duration: "+champion.getAppliedEffects().get(k).getDuration()+"<br></html>";
					}
					String tooltiptext="<html>";
					tooltiptext= tooltiptext+"Champion Name: "+ champion.getName()+"<br>";
					tooltiptext= tooltiptext+"Location: "+ champion.getLocation()+"<br>";
					tooltiptext= tooltiptext+"Type: "+ game.getType(champion)+"<br>";
					tooltiptext= tooltiptext+"isLeader:"+ isLeader+"<br>";
					tooltiptext= tooltiptext+ "Current HP:"+champion.getCurrentHP()+"<br>";
					tooltiptext= tooltiptext+ "Current Mana: "+champion.getMana()+"<br>";
					tooltiptext= tooltiptext+ "Champion Speed: " + champion.getSpeed()+"<br>";
					tooltiptext= tooltiptext+ "Max action points per turn: "+ champion.getMaxActionPointsPerTurn()+"<br>";
					tooltiptext= tooltiptext+ "Attack damage: "+ champion.getAttackDamage()+"<br>";
					tooltiptext= tooltiptext+ "Attack range: " + champion.getAttackRange()+"<br>";
					tooltiptext= tooltiptext+ "Effects:" +"<br>"; 
					for(int k=0; k<champion.getAppliedEffects().size(); k++){
						tooltiptext= tooltiptext+"<br>";
						tooltiptext+=champion.getAppliedEffects().get(k).getName()+"<br";
						tooltiptext+= "duration: "+champion.getAppliedEffects().get(k).getDuration()+"<br></html>";
					}
					//+ effects +"<br></html>";
					
					
					
					boardGrid[i][j].setToolTipText(tooltiptext);
				} else if (game.getBoard()[i][j] instanceof Cover) {
					Cover c = (Cover) game.getBoard()[i][j];
					coverImage = new ImageIcon("cover.png");
					boardGrid[i][j].setIcon(coverImage);
					boardGrid[i][j].setToolTipText("Current HP:"+c.getCurrentHP()+"        "+ "Location:"+ c.getLocation());
					
				}
				else {
					boardGrid[i][j].setBackground(Color.gray);
					boardGrid[i][j].setIcon(null);
				}

			}
		}
	}

	public void setChampionImages(Champion c) {
		Point p = c.getLocation();
		if (c.getName().equals("Captain America"))
			boardGrid[p.x][p.y].setIcon(new ImageIcon("CA.png"));
		if (c.getName().equals("Electro"))
			boardGrid[p.x][p.y].setIcon(new ImageIcon("electro.png"));
		if (c.getName().equals("DeadPool"))
			boardGrid[p.x][p.y].setIcon(new ImageIcon("DP.png"));
		if (c.getName().equals("Dr Strange"))
			boardGrid[p.x][p.y].setIcon(new ImageIcon("Doctor Strange.png"));
		if (c.getName().equals("Ghost Rider"))
			boardGrid[p.x][p.y].setIcon(new ImageIcon("GR.png"));
		if (c.getName().equals("Hela"))
			boardGrid[p.x][p.y].setIcon(new ImageIcon("Hela.png"));
		if (c.getName().equals("Hulk"))
			boardGrid[p.x][p.y].setIcon(new ImageIcon("Hulk.png"));
		if (c.getName().equals("Iceman"))
			boardGrid[p.x][p.y].setIcon(new ImageIcon("iceman.png"));
		if (c.getName().equals("Ironman"))
			boardGrid[p.x][p.y].setIcon(new ImageIcon("ironman.png"));
		if (c.getName().equals("Loki"))
			boardGrid[p.x][p.y].setIcon(new ImageIcon("Loki.png"));
		if (c.getName().equals("Quicksilver"))
			boardGrid[p.x][p.y].setIcon(new ImageIcon("quicksilver.png"));
		if (c.getName().equals("Spiderman"))
			boardGrid[p.x][p.y].setIcon(new ImageIcon("Spiderman.png"));
		if (c.getName().equals("Thor"))
			boardGrid[p.x][p.y].setIcon(new ImageIcon("thor.png"));
		if (c.getName().equals("Venom"))
			boardGrid[p.x][p.y].setIcon(new ImageIcon("Venom.png"));
		if (c.getName().equals("Yellow Jacket"))
			boardGrid[p.x][p.y].setIcon(new ImageIcon("yellow jckt.png"));
	}

	public void CurrentChampionAbilities() {
		ChampionAbilities.removeAllItems();
		ArrayList<String> s = new ArrayList<String>();
		for (int i = 0; i < game.getCurrentChampion().getAbilities().size(); i++) {
			s.add(game.getCurrentChampion().getAbilities().get(i).getName());
		}
		for (int i = 0; i < s.size(); i++) {
			ChampionAbilities.addItem(s.get(i));
		}
	}


	public String  CurrentChampionInfo(){
		String info = "Current Player : " ;
				if(game.getFirstPlayer().getTeam().contains(game.getCurrentChampion())){
					info = info + game.getFirstPlayer().getName() + "\n\n";
				} else{
					info = info + game.getSecondPlayer().getName() + "\n\n";
				}
				Champion c =game.getCurrentChampion();
				info= info +"Current Champion Info:" + "\n" +"Name: " + c.getName() + "\n" + "Attack Damage: " + c.getAttackDamage()+
						"\n" + "Attack Range: " + c.getAttackRange() + "\n" +"Current Health:" + c.getCurrentHP() +"\n" +"Mana= " + c.getMana() + 
						"\n" + "Current Action Points:" + c.getCurrentActionPoints() +"\n\n" +"Abilities:- " +"\n";
				for(int i =0; i< c.getAbilities().size();i++){
					Ability a = c.getAbilities().get(i);
					info = info +"\n" + (i+1)+ ") Ability Name: " + a.getName() + "\n"+ "Manacost: "+ a.getManaCost()+ "\n"+ "BaseCoolDown: " + a.getBaseCooldown()+ "\n"+ "RequiredActionPoints: " + a.getRequiredActionPoints() + "\n"+ "AreaoFEffect: " + a.getCastArea()+ "\n";
				}
				return info;
				
	}
	

	public static void main(String[] args) {
		MainWindow w = new MainWindow();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == move) {
			String[] y= {"UP", "DOWN", "LEFT", "RIGHT"};
			String n = (String)JOptionPane.showInputDialog(null, "Choose Direction", "Cast Ability Directional", JOptionPane.QUESTION_MESSAGE,null,y, y[0]);
		
			if(n.equals("UP"))
				direction = Direction.UP;
			if(n.equals("DOWN"))
				direction = Direction.DOWN;
			if(n.equals("LEFT"))
				direction = Direction.LEFT;
			if(n.equals("RIGHT"))
				direction = Direction.RIGHT;
			try {
				game.move(direction);
				setBoard();
			} catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(this,
						"You don't have enough resources to do this attack",
						"Oops..", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} catch (UnallowedMovementException e1) {
				JOptionPane.showMessageDialog(this,
						"This movement is not allowed",
						"Oops..", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			

		}
		if (e.getSource() == attack) {
			String[] y= {"UP", "DOWN", "LEFT", "RIGHT"};
			String n = (String)JOptionPane.showInputDialog(null, "Choose Direction", "Attack Direction", JOptionPane.QUESTION_MESSAGE,null,y, y[0]);
		
			if(n.equals("UP"))
				direction = Direction.UP;
			if(n.equals("DOWN"))
				direction = Direction.DOWN;
			if(n.equals("LEFT"))
				direction = Direction.LEFT;
			if(n.equals("RIGHT"))
				direction = Direction.RIGHT;
			
			try {
				game.attack(direction);
				setBoard();
			} catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(this,
						"You don't have enough resources to do this attack",
						"Oops..", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} catch (ChampionDisarmedException e1) {
				JOptionPane.showMessageDialog(this,
						"This Champion is Disarmed",
						"Oops..", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} catch (InvalidTargetException e1) {
				JOptionPane.showMessageDialog(this,
						"This Target is invalid",
						"Oops..", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
		 if(e.getSource() == EndTurn){ 
			 game.endTurn(); 
			 setBoard();
			 CurrentChampionAbilities();
			 queue.setText(queueMaker());
			Champinfo.setText(CurrentChampionInfo());
			 }
		 if(e.getSource() == LeaderAbility){
			 try {
				game.useLeaderAbility();
			} catch (LeaderNotCurrentException e1) {
				JOptionPane.showMessageDialog(this,
						"Only Team Leader can do this",
						"Oops..", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} catch (LeaderAbilityAlreadyUsedException e1) {
				JOptionPane.showMessageDialog(this,
						"LeaderAbilityAlreadyUsed",
						"Oops..", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			 
				
		 }
		 if(e.getSource() == Cast){
			int index= ChampionAbilities.getSelectedIndex();
			Ability temp= game.getCurrentChampion().getAbilities().get(index);
			try {
				if(temp.getCastArea()== AreaOfEffect.DIRECTIONAL){
				String[] y= {"UP", "DOWN", "LEFT", "RIGHT"};
				String n = (String)JOptionPane.showInputDialog(null, "Choose Direction", "Attack Direction", JOptionPane.QUESTION_MESSAGE,null,y, y[0]);
			
				if(n.equals("UP"))
					direction = Direction.UP;
				if(n.equals("DOWN"))
					direction = Direction.DOWN;
				if(n.equals("LEFT"))
					direction = Direction.LEFT;
				if(n.equals("RIGHT"))
					direction = Direction.RIGHT;
			
					game.castAbility(temp, direction);}
					else if(temp.getCastArea()== AreaOfEffect.SINGLETARGET){
						JTextField t1= new JTextField();
						JTextField t2= new JTextField();
						Object[] Texts ={"Enter Target's X & Y coordinates as displayed when you hover on the target", "x coordinate", t1, "y coordinate" , t2};
						int x= Integer.parseInt(t1.getText());
						int y= Integer.parseInt(t2.getText());
						try {
							game.castAbility(temp, x, y);
						} catch (InvalidTargetException e1) {
							JOptionPane.showMessageDialog(this,
									"This Target is invalid",
									"Oops..", JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
					}
					else
						game.castAbility(temp);
				setBoard();
				 CurrentChampionAbilities();
				 queue.setText(queueMaker());
				Champinfo.setText(CurrentChampionInfo());
						
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException e1) {
					JOptionPane.showMessageDialog(this,
							"Error",
							"Oops..", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		 if(game.checkGameOver() != null){
			 JOptionPane.showMessageDialog(this,
						"The Winner is"+game.checkGameOver().getName() ,
						"Game Over", JOptionPane.INFORMATION_MESSAGE);
			 this.dispose();
		 }
		 }
		
		 

	}

	


