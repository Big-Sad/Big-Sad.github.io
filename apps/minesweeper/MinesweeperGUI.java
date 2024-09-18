//Elizabeth Sellers
//IMPORTS//
import javax.swing.*;
//import javax.swing.plaf.metal.MetalButtonUI;
//import javax.swing.plaf.metal.MetalToggleButtonUI;

import java.awt.*;
import java.awt.event.*;

public class MinesweeperGUI implements ActionListener, ItemListener{
	//CREATE GUI OBJECTS//
	JFrame myFrame = new JFrame();
	JPanel contentPane = new JPanel();
	JMenuBar menu = new JMenuBar();
	JMenu options = new JMenu("Options");
	JMenuItem newGame = new JMenuItem("New Game");
	JMenu difficulty = new JMenu("Difficulty");
		JMenuItem easy = new JMenuItem("Easy");
		JMenuItem medium = new JMenuItem("Medium");
		JMenuItem hard = new JMenuItem("Hard");
	JMenu changeColors = new JMenu("Change Theme");
		JMenuItem pastel = new JMenuItem("Pastel");
		JMenuItem dark = new JMenuItem("Dark");
		JMenuItem neon = new JMenuItem("Neon");
	JMenuItem exit = new JMenuItem("Exit");
	JMenu flagsLeft = new JMenu();
	JMenu spaceMaker = new JMenu(" ");
		
	//MODE SWITCH//
//	ImageIcon flag = new ImageIcon("C:\\Users\\eliza\\eclipse-workspace\\java\\Project - Minesweeper\\src\\Flag.png");
//	ImageIcon bomb = new ImageIcon("C:\\Users\\eliza\\eclipse-workspace\\java\\Project - Minesweeper\\src\\Bomb.png");
	
	ImageIcon flag = new ImageIcon(MinesweeperGUI.class.getResource("Flag.png"));
	ImageIcon bomb = new ImageIcon(MinesweeperGUI.class.getResource("Bomb.png"));
	
	JToggleButton flags = new JToggleButton(bomb, false);
	
	
	//COLOR SCHEMES//
	//Pastel//
		//Main//
		Color pastelHiddenDark    = new Color(61,  126, 148);
		Color pastelHiddenLight   = new Color(56,  119, 141);
		Color pastelRevealedDark  = new Color(244, 210, 184);
		Color pastelRevealedLight = new Color(255, 221, 194);
		Color pastelBorder = new Color(85, 85, 85);
			Color pastel1 = new Color(141, 188, 232);
			Color pastel2 = new Color(99,  194, 103);
			Color pastel3 = new Color(233, 132, 147);
			Color pastel4 = new Color(199, 161, 231);
			Color pastel5 = new Color(239, 177,  99);
			Color pastel6 = new Color(162, 223, 243);
			Color pastel7 = new Color(130, 130, 130);
			Color pastel8 = new Color(180, 180, 180);
		//Dark//
		Color darkHiddenDark     = new Color(57, 57, 57);
		Color darkHiddenLight    = new Color(63, 63, 63);
		Color darkRevealedDark   = new Color(99, 99, 99);
		Color darkRevealedLight  = new Color(89, 89, 89);
		Color darkBorder = new Color(150, 150, 150);
			Color dark1 = new Color(29,  42,  151);
			Color dark2 = new Color(20,  55,   19);
			Color dark3 = new Color(151, 35,   35);
			Color dark4 = new Color(110, 35,  144);
			Color dark5 = new Color(226, 175,   0);
			Color dark6 = new Color(35,  151, 148);
			Color dark7 = new Color(124, 124, 124);
			Color dark8 = new Color(154, 154, 154);
		//Neon//
		Color neonHiddenDark     = new Color(37, 37, 37);
		Color neonHiddenLight    = new Color(46, 46, 46);
		Color neonRevealedDark   = new Color(96, 96, 96);
		Color neonRevealedLight  = new Color(86, 86, 86);
			Color neon1 = new Color(0,   15,  255);
			Color neon2 = new Color(0,   244,  41);
			Color neon3 = new Color(236, 0,     0);
			Color neon4 = new Color(164, 25,  255);
			Color neon5 = new Color(255, 233,  24);
			Color neon6 = new Color(24,  255, 252);
			Color neon7 = new Color(198, 198, 198);
			Color neon8 = new Color(237, 237, 237);
		
	//Active Color Scheme//
	Color hiddenDark = pastelHiddenDark;
	Color hiddenLight = pastelHiddenLight;
	Color revealedDark = pastelRevealedDark;
	Color revealedLight = pastelRevealedLight;
	Color borderColor = pastelBorder;
		Color num1Color = pastel1;
		Color num2Color = pastel2;
		Color num3Color = pastel3;
		Color num4Color = pastel4;
		Color num5Color = pastel5;
		Color num6Color = pastel6;
		Color num7Color = pastel7;
		Color num8Color = pastel8;
		
		
	//UNICODE CHARACTERS FOR COPY PASTE//
	/*
	FLAG ⚑ 🏲 🚩 🏲
	BOMB 💣
	 */
	
	//CREATE COMPUTATIONAL OBJECTS//
	JButton[][] buttons;
	String[][] key;
	int numOfRows;
	int numOfColumns;
	int numOfMines;
	int bombsLeft;
	boolean flagMode = false;
	int difficultyLevel = 0; // 0 = default (0.161985596) | 1 = easy (0.125691358) | 2 = medium (0.17825) | 3 = hard (0.206125)
	boolean hadInitialClick = false;
	
	public MinesweeperGUI() {
		
		
		
		
		
		
		//CREATE FRAME//
		myFrame.setTitle("Minesweeper");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(1000, 800);
		myFrame.setLocationRelativeTo(null);
		
		//ADD OBJECTS TO FRAME//
		myFrame.add(menu);
		menu.add(options);
		options.add(newGame);
		options.add(difficulty);
			difficulty.add(easy);
			difficulty.add(medium);
			difficulty.add(hard);
		options.add(changeColors);
			changeColors.add(pastel);
			changeColors.add(dark);
			changeColors.add(neon);
		options.addSeparator();
		options.add(exit);
		menu.add(Box.createHorizontalGlue());
		menu.add(flagsLeft);
		menu.add(flags);
		menu.add(spaceMaker);
		
		myFrame.setJMenuBar(menu);
		myFrame.add(contentPane);
		
		//ADD ACTION LISTENER//
		exit.addActionListener(this);
		newGame.addActionListener(this);
		easy.addActionListener(this);
		medium.addActionListener(this);
		hard.addActionListener(this);
		pastel.addActionListener(this);
		dark.addActionListener(this);
		neon.addActionListener(this);
		flags.addItemListener(this);
		
		menu.setBackground(Color.WHITE);
		flags.setBackground(null);
		
		flags.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		//flags.setBorder(null);
		
		myFrame.setVisible(true);
		
		gameStart();
	}//ends method - constructor - MinesweeperGUI
	
	
	
	//TOGGLE BUTTON//
	public void itemStateChanged(ItemEvent eve) {  
		
        if (flags.isSelected()) {
        	flags.setIcon(flag);
        	flagMode = true;
        }
        else  {
        	flags.setIcon(bomb);
        	flagMode = false;
        }
    }//ends method - itemStateChanged
	
	
	
	//ACTIONS//
	public void actionPerformed(ActionEvent e) {
		Object control = e.getSource();
		
		//NEW GAME
		if(control == newGame) {
			difficultyLevel = 0;
			clear();
			gameStart();
		}
		//CHANGE DIFFICULTY//
			if(control == easy) {
				difficultyLevel = 1;
				clear();
				gameStart();
			}
			if(control == medium) {
				difficultyLevel = 2;
				clear();
				gameStart();
			}
			if(control == hard) {
				difficultyLevel = 3;
				clear();
				gameStart();
			}
		//CHANGE COLOR SCHEME//
			if(control == pastel) { //PASTEL//
				contentPane.repaint();
				updateColors("pastel");
				for(int i = 0; i < numOfRows; i++) {
					for(int j = 0; j < numOfColumns; j++) {
						colorNums(i, j);
						updateBG(i, j, "pastel");
						buttons[i][j].setBorder(BorderFactory.createLineBorder(borderColor));
						buttons[i][j].repaint();
					}
				}//ends for loop
			}//ends pastel
			if(control == dark) { //DARK THEME//
				updateColors("dark");
				for(int i = 0; i < numOfRows; i++) {
					for(int j = 0; j < numOfColumns; j++) {
						colorNums(i, j);
						updateBG(i, j, "dark");
						buttons[i][j].setBorder(BorderFactory.createLineBorder(borderColor));
						buttons[i][j].repaint();
					}
				}//ends for loop
			}//ends dark
			if(control == neon) { //NEON//
				contentPane.repaint();
				for(int i = 0; i < numOfRows; i++) {
					updateColors("neon");
					for(int j = 0; j < numOfColumns; j++) {
						colorNums(i, j);
						updateBG(i, j, "neon");
						buttons[i][j].setBorder(BorderFactory.createLineBorder(borderColor));
						buttons[i][j].repaint();
					}
				}//ends for loop
			}//ends neon
		//EXIT//
		if(control == exit) {
			myFrame.dispose();
		}
		
		
		
		for(int i = 0; i < buttons.length; i++) {
			for(int j = 0; j < buttons[i].length; j++) {
				if(control == buttons[i][j]) {
						//FIXES ORIGINAL SPOT AND CLEARS OUT AREA//
					if(!hadInitialClick) {
						initialClick(i, j);
					}
					
						//PLACE A FLAG//
					if(flagMode) {
						if(buttons[i][j].getText() == "🏲") {
							buttons[i][j].setText("");
							bombsLeft++;
							flagsLeft.setText("🏲 " + bombsLeft);
						}
						else if (buttons[i][j].getText() == "") {
							buttons[i][j].setText("🏲");
							buttons[i][j].setForeground(num3Color);
							bombsLeft--;
							flagsLeft.setText("🏲 " + bombsLeft);
						}
						
					}//ends flagMode Instructions
					else {
						if(buttons[i][j].getText() == "🏲") {
							//DO NOTHING//
						}//if flag picked, do nothing
						
						//IF A MINE IS PICKED//
						else if(key[i][j].equals("💣")) {
							buttons[i][j].setText(key[i][j]);
							if(buttons[i][j].getBackground() == hiddenLight || buttons[i][j].getBackground() == revealedLight) {
								buttons[i][j].setBackground(revealedLight);
							}
							else {
								buttons[i][j].setBackground(revealedDark);
							}
							
							int restart = JOptionPane.showConfirmDialog(null, "Game Over! \nDo you want to start a new game?", "Exit Dialogue",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE);
							if(restart == JOptionPane.YES_OPTION) {
								
								clear();
								gameStart();
							}
							else if(restart == JOptionPane.NO_OPTION) {
								myFrame.dispose();
							}
							
						}//ends if a mine is picked
						else if(key[i][j].equals(" ")) {
							buttons[i][j].setText(key[i][j]);
							if(buttons[i][j].getBackground() == hiddenLight || buttons[i][j].getBackground() == revealedLight || buttons[i][j].getBackground() == revealedLight) {
								buttons[i][j].setBackground(revealedLight);
							}
							else {
								buttons[i][j].setBackground(revealedDark);
							}
							reveal(i, j);
						}
						else {
							buttons[i][j].setText(key[i][j]);
							colorNums(i, j);
							if(buttons[i][j].getBackground() == hiddenLight || buttons[i][j].getBackground() == revealedLight) {
								buttons[i][j].setBackground(revealedLight);
							}
							else {
								buttons[i][j].setBackground(revealedDark);
							}
						}
					}//ends button click
					
					//CHECK IF THE GAME IS COMPLETE//
					if(checkWin()) {
						int restart = JOptionPane.showConfirmDialog(null, "Congratulations! You Won! \nDo you want to start a new game?", "Exit Dialogue",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
						if(restart == JOptionPane.YES_OPTION) {
							difficultyLevel = 0;
							clear();
							gameStart();
						}
						else if(restart == JOptionPane.NO_OPTION) {
							myFrame.dispose();
						}
					}
				}//ends if statement
			}//ends inner for
		}//ends outer for loop
	}//ends method - actionPerformed
	
	
	
	
	//START GAME AND GET GAME SIZE//
	public void gameStart() {
		//clear();
		hadInitialClick = false;
		
			//variables//
		JTextField rows = new JTextField();
		JTextField columns = new JTextField();
			Object[] message = {
				"Number of Rows:", rows,
				"Number of Columns:", columns
			};
		
		
			//pop-up box//
		int option = JOptionPane.showConfirmDialog(null, message, "Input Size", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			try {
				if(checkIfNum(rows) && checkIfNum(columns)) {
					if (Integer.parseInt(rows.getText()) > 0 && Integer.parseInt(columns.getText()) > 0) {
						numOfRows = Integer.parseInt(rows.getText());
						numOfColumns = Integer.parseInt(columns.getText());
					}//ends if
					else {
						JOptionPane.showMessageDialog(null, "Inputed Values Invalid", "Invalid Inputs", JOptionPane.PLAIN_MESSAGE);
					}//ends inner if-else
				}//ends outer if
				
			} catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Inputed Values Invalid", "Invalid Inputs", JOptionPane.PLAIN_MESSAGE);
			} 
			//ends try-catch
			
			
		}//ends if
		else {
			//myFrame.dispose();
			System.exit(0);
		}//ends if-else
		
		
		
		//CALCULATE THE NUMBER OF MINES//
		if(difficultyLevel == 0) { //DEFAULT
			numOfMines = (int) (Math.ceil((numOfRows*numOfColumns)*0.161985596));
		}
		else if(difficultyLevel == 1) {//EASY
			numOfMines = (int) (Math.ceil((numOfRows*numOfColumns)*0.125691358));
		}
		else if(difficultyLevel == 2) {//MEDIUM
			numOfMines = (int) (Math.ceil((numOfRows*numOfColumns)*0.17825));
		}
		else if(difficultyLevel == 3) {//HARD
			numOfMines = (int) (Math.ceil((numOfRows*numOfColumns)*0.206125));
		}
		//System.out.println(numOfMines);
		flagsLeft.setText("🏲 " + numOfMines);
		bombsLeft = numOfMines;
		
		//CREATE VIEWABLE GRID AND ANSWER KEY//
		buttons = new JButton[numOfRows][numOfColumns];
		key = new String[numOfRows][numOfColumns];
		System.out.println(bombsLeft);

		//CREATE BUTTONS//
		for(int i = 0; i < numOfRows; i++) {
			for(int j = 0; j < numOfColumns; j++) {
				buttons[i][j] = new JButton("");
				key[i][j] = " ";
			}
		}//ends for loop
		
		//System.out.println(buttons[0][0].getSize());
		//System.out.println(buttons[0][0].getWidth());
		
		
		//RANDOMLY PLACE MINES AND ASSIGN NUMBERS//
		placeMines(numOfMines);
		placeNums();
		
		bombsLeft = numOfMines;
		
		int sizeOfFont = 50;
		if(numOfRows < numOfColumns) {
			sizeOfFont = (int) (Math.ceil(800/numOfRows)*0.5);
			sizeOfFont = (int) (Math.ceil(800/numOfRows)*0.5);
		}
		else if(numOfRows > numOfColumns) {
			sizeOfFont = (int) (Math.ceil(800/numOfColumns)*0.5);
		}
		
		//System.out.println(contentPane.getHeight());
		
		//Font buttonFont = new Font("Ariel", Font.PLAIN, 50);
		Font buttonFont = new Font("Ariel", Font.PLAIN, sizeOfFont);
		
		//CUSTOMIZE OBJECTS//
		contentPane.setLayout(new GridLayout(numOfRows, numOfColumns));
		
		
		
		//COUNTER FOR BUTTON INITIAL BACKGROUND COLOR//
		int colorCounter = 0;
		//boolean ifRun = false;
		
		for(int i = 0; i < numOfRows; i++) {
			for(int j = 0; j < numOfColumns; j++) {
				contentPane.add(buttons[i][j]);
				buttons[i][j].setFocusable(false);
				buttons[i][j].setRolloverEnabled(false);
				buttons[i][j].addActionListener(this);
				buttons[i][j].setFont(buttonFont);
				if(colorCounter%2 == 0) {
					//buttons[i][j].setBackground(new Color(61, 126, 148));
					buttons[i][j].setBackground(hiddenDark);
					colorCounter++;
					if(numOfColumns%2 == 0 && (buttons[i][numOfColumns-1].getBackground() == hiddenDark || buttons[i][numOfColumns-1].getBackground() == hiddenLight)) {
						colorCounter++;
					}
				}
				else {
					//buttons[i][j].setBackground(new Color(56, 119, 141));
					buttons[i][j].setBackground(hiddenLight);
					colorCounter++;
					if(numOfColumns%2 == 0 && (buttons[i][numOfColumns-1].getBackground() == hiddenDark || buttons[i][numOfColumns-1].getBackground() == hiddenLight)) {
						colorCounter++;
					}
				}
				buttons[i][j].setForeground(Color.BLACK);
				//buttons[i][j].setBorder(BorderFactory.createLineBorder(new Color(85, 85, 85)));
				buttons[i][j].setBorder(BorderFactory.createLineBorder(borderColor));
			}//ends inner for loop
		}//ends for loop
		//System.out.println("This is the height: " + buttons[0][0].getHeight());
	}//ends method - gameStart
	
	
	
	//CHECK IF INPUTTED VALUES ARE A NUM//
	public boolean checkIfNum(JTextField num) {
		String input = num.getText();
		
		for(int i = 0; i < input.length(); i++) {
			if(input.charAt(i) > 47 && input.charAt(i) < 58) {
			} else {
				return false;
			}
		}//ends for loop
		
		return true;
	}//ends checkIfNum
	
	
	//PLACE MINES//
	public void placeMines(int numPlacing) {
		
		//INITIALLIZE RANDOM NUMBER STORAGE//
		int randRows;
		int randCols;
		
		//CREATE 2D ARRAY & PLACE MINES RANDOMLY//
		for(int i = 0; i < numPlacing; i++) {
			randRows = (int) (Math.floor(Math.random()*((numOfRows-1)-0+1)+0));
			randCols = (int) (Math.floor(Math.random()*((numOfColumns-1)-0+1)+0));
			
			if(!key[randRows][randCols].equals("💣")) {
				key[randRows][randCols] = "💣";
			}
			else {
				i++;
			}				

		}//ends for loop

		
	}//ends method - placeMines
	
	
	
	//PLACE NUMBERS//
	public void placeNums() {
		int minesDetected;
			
		for(int i = 0; i < numOfRows; i++) {
			for(int j = 0; j < numOfColumns; j++) {
				minesDetected = 0;
				
				if(!key[i][j].equals("💣")) {
					//TOP LEFT//
					try {
						if(key[i-1][j-1].equals("💣")) {
							minesDetected++;
						}
					}
					catch(Exception ArrayIndexOutOfBoundsException) {
						
					}//ends try catch
					
					
					//TOP MIDDLE//
					try {
						if(key[i-1][j].equals("💣")) {
							minesDetected++;
						}
					}
					catch(Exception ArrayIndexOutOfBoundsException) {
						
					}//ends try catch
					
					
					//TOP RIGHT//
					try {
						if(key[i-1][j+1].equals("💣")) {
							minesDetected++;
						}
					}
					catch(Exception ArrayIndexOutOfBoundsException) {
						
					}//ends try catch
					
					
					//CENTER LEFT//
					try {
						if(key[i][j-1].equals("💣")) {
							minesDetected++;
						}
					}
					catch(Exception ArrayIndexOutOfBoundsException) {
						
					}//ends try catch
					
					
					//CENTER RIGHT//
					try {
						if(key[i][j+1].equals("💣")) {
							minesDetected++;
						}
					}
					catch(Exception ArrayIndexOutOfBoundsException) {
						
					}//ends try catch
					
					
					//BOTTOM LEFT//
					try {
						if(key[i+1][j-1].equals("💣")) {
							minesDetected++;
						}
					}
					catch(Exception ArrayIndexOutOfBoundsException) {
						
					}//ends try catch
					
					
					//BOTTOM MIDDLE//
					try {
						if(key[i+1][j].equals("💣")) {
							minesDetected++;
						}
					}
					catch(Exception ArrayIndexOutOfBoundsException) {
						
					}//ends try catch
					
					
					//BOTTOM RIGHT//
					try {
						if(key[i+1][j+1].equals("💣")) {
							minesDetected++;
						}
					}
					catch(Exception ArrayIndexOutOfBoundsException) {
						
					}//ends try catch
				}//ends if statement
				
				if(minesDetected > 0) {
					key[i][j] = minesDetected + "";
					//buttons[i][j].setText(minesDetected + "");
				}
				
			}//ends inner for
		}//ends outer for
		
		
	}//ends method - placeNums
	
	
	
	//1ST CLICK IS NEVER A BOMB//
	public void initialClick (int xValue, int yValue) {
		
			//MOVE MINE INITIAL PLACE IS NOT 0//
			while(key[xValue][yValue] != " " && key[xValue][yValue] != "") {
				for(int i = 0; i < numOfRows; i++) {
					for(int j = 0; j < numOfColumns; j++) {
						key[i][j] = " ";
					}
				}//ends for loop
				placeMines(numOfMines);
				placeNums();
				//System.out.println("HAD TO REDO");
			}//ends while
			
			//PRINT KEY//
			for(int i = 0; i < numOfRows; i++) {
				for(int j = 0; j < numOfColumns; j++) {
					if(key[i][j] == " ") {
						System.out.print("*  ");
					}
					else {
						System.out.print(key[i][j] + "  ");
					}
				}
				System.out.println();
			}//ends for loop
			System.out.println();
			
			buttons[xValue][yValue].setText(key[xValue][yValue]);
			
			if(buttons[xValue][yValue].getBackground() == hiddenLight || buttons[xValue][yValue].getBackground() == revealedLight) {
				buttons[xValue][yValue].setBackground(revealedLight);
			}
			else {
				buttons[xValue][yValue].setBackground(revealedDark);
			}
			
			reveal(xValue, yValue);
			
		hadInitialClick = true;
	}//ends method - initialClick
	
	
	//REVEAL NOT BOMB SPACES
	
	public void reveal(int x, int y) {
		//count++;
		//System.out.println(count);
		
		
		
		//UPPER LEFT//
		try {
			if(buttons[x-1][y-1].getText().equals("")) {
				if((key[x-1][y-1] == " ")) {
					buttons[x-1][y-1].setText(key[x-1][y-1]);
					if(buttons[x-1][y-1].getBackground() == hiddenLight) {
						buttons[x-1][y-1].setBackground(revealedLight);
					}
					else {
						buttons[x-1][y-1].setBackground(revealedDark);
					}
					colorNums(x-1, y-1);
					reveal(x-1, y-1);
					//System.out.println("INSIDE TRY IF");
				}
				else {
					buttons[x-1][y-1].setText(key[x-1][y-1]);
					if(buttons[x-1][y-1].getBackground() == hiddenLight) {
						buttons[x-1][y-1].setBackground(revealedLight);
					}
					else {
						buttons[x-1][y-1].setBackground(revealedDark);
					}
					colorNums(x-1, y-1);
				}	
			}
		}
		catch (ArrayIndexOutOfBoundsException Exception)  {
		} catch (IndexOutOfBoundsException Exception) {
		}
		
		
		//UPPER MIDDLE//
		try {
			if(buttons[x-1][y].getText().equals("")) {
				if((key[x-1][y] == " ")) {
					buttons[x-1][y].setText(key[x-1][y]);
					if(buttons[x-1][y].getBackground() == hiddenLight) {
						buttons[x-1][y].setBackground(revealedLight);
					}
					else {
						buttons[x-1][y].setBackground(revealedDark);
					}
					colorNums(x-1, y);
					reveal(x-1, y);
				}
				else {
					buttons[x-1][y].setText(key[x-1][y]);
					if(buttons[x-1][y].getBackground() == hiddenLight) {
						buttons[x-1][y].setBackground(revealedLight);
					}
					else {
						buttons[x-1][y].setBackground(revealedDark);
					}
					colorNums(x-1, y);
				}
			}
		}
		catch (ArrayIndexOutOfBoundsException Exception)  {
		} catch (IndexOutOfBoundsException Exception) {
		}
		
		
		//UPPER RIGHT//
		try {
			if(buttons[x-1][y+1].getText().equals("")) {
				if((key[x-1][y+1] == " ")) {
					buttons[x-1][y+1].setText(key[x-1][y+1]);
					if(buttons[x-1][y+1].getBackground() == hiddenLight) {
						buttons[x-1][y+1].setBackground(revealedLight);
					}
					else {
						buttons[x-1][y+1].setBackground(revealedDark);
					}
					colorNums(x-1, y+1);
					reveal(x-1, y+1);
				}
				else {
					buttons[x-1][y+1].setText(key[x-1][y+1]);
					if(buttons[x-1][y+1].getBackground() == hiddenLight) {
						buttons[x-1][y+1].setBackground(revealedLight);
					}
					else {
						buttons[x-1][y+1].setBackground(revealedDark);
					}
					colorNums(x-1, y+1);
				}
			}
		}
		catch (ArrayIndexOutOfBoundsException Exception)  {
		} catch (IndexOutOfBoundsException Exception) {
		}
		
		
		//MIDDLE LEFT//
		try {
			if(buttons[x][y-1].getText().equals("")) {
				if((key[x][y-1] == " ")) {
					buttons[x][y-1].setText(key[x][y-1]);
					if(buttons[x][y-1].getBackground() == hiddenLight) {
						buttons[x][y-1].setBackground(revealedLight);
					}
					else {
						buttons[x][y-1].setBackground(revealedDark);
					}
					colorNums(x, y-1);
					reveal(x, y-1);
				}
				else {
					buttons[x][y-1].setText(key[x][y-1]);
					if(buttons[x][y-1].getBackground() == hiddenLight) {
						buttons[x][y-1].setBackground(revealedLight);
					}
					else {
						buttons[x][y-1].setBackground(revealedDark);
					}
					colorNums(x, y-1);
				}
			}
		}
		catch (ArrayIndexOutOfBoundsException Exception)  {
		} catch (IndexOutOfBoundsException Exception) {
		}
		
		
		//MIDDLE RIGHT//
		try {
			if(buttons[x][y+1].getText().equals("")) {
				if((key[x][y+1] == " ")) {
					buttons[x][y+1].setText(key[x][y+1]);
					if(buttons[x][y+1].getBackground() == hiddenLight) {
						buttons[x][y+1].setBackground(revealedLight);
					}
					else {
						buttons[x][y+1].setBackground(revealedDark);
					}
					colorNums(x, y+1);
					reveal(x, y+1);
				}
				else {
					buttons[x][y+1].setText(key[x][y+1]);
					if(buttons[x][y+1].getBackground() == hiddenLight) {
						buttons[x][y+1].setBackground(revealedLight);
					}
					else {
						buttons[x][y+1].setBackground(revealedDark);
					}
					colorNums(x, y+1);
				}
			}
		}
		catch (ArrayIndexOutOfBoundsException Exception)  {
		} catch (IndexOutOfBoundsException Exception) {
		}
		
		
		//BOTTOM LEFT//
		try {
			if(buttons[x+1][y-1].getText().equals("")) {
				if((key[x+1][y-1] == " ")) {
					buttons[x+1][y-1].setText(key[x+1][y-1]);
					if(buttons[x+1][y-1].getBackground() == hiddenLight) {
						buttons[x+1][y-1].setBackground(revealedLight);
					}
					else {
						buttons[x+1][y-1].setBackground(revealedDark);
					}
					colorNums(x+1, y-1);
					reveal(x+1, y-1);
				}
				else {
					buttons[x+1][y-1].setText(key[x+1][y-1]);
					if(buttons[x+1][y-1].getBackground() == hiddenLight) {
						buttons[x+1][y-1].setBackground(revealedLight);
					}
					else {
						buttons[x+1][y-1].setBackground(revealedDark);
					}
					colorNums(x+1, y-1);
				}
			}
		}
		catch (ArrayIndexOutOfBoundsException Exception)  {
		} catch (IndexOutOfBoundsException Exception) {
		}
		

		//BOTTOM MIDDLE//
		try {
			if(buttons[x+1][y].getText().equals("")) {
				if((key[x+1][y] == " ")) {
					buttons[x+1][y].setText(key[x+1][y]);
					if(buttons[x+1][y].getBackground() == hiddenLight) {
						buttons[x+1][y].setBackground(revealedLight);
					}
					else {
						buttons[x+1][y].setBackground(revealedDark);
					}
					colorNums(x+1, y);
					reveal(x+1, y);
				}
				else {
					buttons[x+1][y].setText(key[x+1][y]);
					if(buttons[x+1][y].getBackground() == hiddenLight) {
						buttons[x+1][y].setBackground(revealedLight);
					}
					else {
						buttons[x+1][y].setBackground(revealedDark);
					}
					colorNums(x+1, y);
				}
			}
		}
		catch (ArrayIndexOutOfBoundsException Exception)  {
		} catch (IndexOutOfBoundsException Exception) {
		}

		
		//BOTTOM RIGHT//
		try {
			if(buttons[x+1][y+1].getText().equals("")) {
				if((key[x+1][y+1] == " ")) {
					buttons[x+1][y+1].setText(key[x+1][y+1]);
					if(buttons[x+1][y+1].getBackground() == hiddenLight) {
						buttons[x+1][y+1].setBackground(revealedLight);
					}
					else {
						buttons[x+1][y+1].setBackground(revealedDark);
					}
					colorNums(x+1, y+1);
					reveal(x+1, y+1);
				}
				else {
					buttons[x+1][y+1].setText(key[x+1][y+1]);
					if(buttons[x+1][y+1].getBackground() == hiddenLight) {
						buttons[x+1][y+1].setBackground(revealedLight);
					}
					else {
						buttons[x+1][y+1].setBackground(revealedDark);
					}
					colorNums(x+1, y+1);
				}
			}	
		}
		catch (ArrayIndexOutOfBoundsException Exception)  {
		} catch (IndexOutOfBoundsException Exception) {
		}

	}//ends method - reveal
	
	
	//CHANGE COLOR VALUES//
	public void updateColors(String type) {
		
		if(type == "pastel") {
			hiddenDark = pastelHiddenDark;
			hiddenLight = pastelHiddenLight;
			revealedDark = pastelRevealedDark;
			revealedLight = pastelRevealedLight;
			borderColor = pastelBorder;
				num1Color = pastel1;
				num2Color = pastel2;
				num3Color = pastel3;
				num4Color = pastel4;
				num5Color = pastel5;
				num6Color = pastel6;
				num7Color = pastel7;
				num8Color = pastel8;
		}//ends pastel
		
		if(type == "dark") {
			hiddenDark = darkHiddenDark;
			hiddenLight = darkHiddenLight;
			revealedDark = darkRevealedDark;
			revealedLight = darkRevealedLight;
			borderColor = darkBorder;
				num1Color = dark1;
				num2Color = dark2;
				num3Color = dark3;
				num4Color = dark4;
				num5Color = dark5;
				num6Color = dark6;
				num7Color = dark7;
				num8Color = dark8;
		}//ends dark
		
		if(type == "neon") {
			hiddenDark = neonHiddenDark;
			hiddenLight = neonHiddenLight;
			revealedDark = neonRevealedDark;
			revealedLight = neonRevealedLight;
			borderColor = darkBorder;
				num1Color = neon1;
				num2Color = neon2;
				num3Color = neon3;
				num4Color = neon4;
				num5Color = neon5;
				num6Color = neon6;
				num7Color = neon7;
				num8Color = neon8;
		}//ends neon
	}//ends updateColors
	
	
	//UPDATE BACKGROUND COLOR//
	public void updateBG (int x, int y, String type) {
		if(type == "pastel") {
			if(buttons[x][y].getBackground() == darkHiddenDark || buttons[x][y].getBackground() == neonHiddenDark) { //BLUE//
				buttons[x][y].setBackground(hiddenDark);
			}
			else if(buttons[x][y].getBackground() == darkHiddenLight || buttons[x][y].getBackground() == neonHiddenLight) { //BLUE//
				buttons[x][y].setBackground(hiddenLight);
			}
			else if(buttons[x][y].getBackground() == darkRevealedDark || buttons[x][y].getBackground() == neonRevealedDark) { //BLUE//
				buttons[x][y].setBackground(revealedDark);
			}
			else if(buttons[x][y].getBackground() == darkRevealedLight || buttons[x][y].getBackground() == neonRevealedLight) { //BLUE//
				buttons[x][y].setBackground(revealedLight);
			}
		}//ends pastel
		
		if(type == "dark") {
			if(buttons[x][y].getBackground() == pastelHiddenDark || buttons[x][y].getBackground() == neonHiddenDark) { //BLUE//
				buttons[x][y].setBackground(hiddenDark);
			}
			else if(buttons[x][y].getBackground() == pastelHiddenLight || buttons[x][y].getBackground() == neonHiddenLight) { //BLUE//
				buttons[x][y].setBackground(hiddenLight);
			}
			else if(buttons[x][y].getBackground() == pastelRevealedDark || buttons[x][y].getBackground() == neonRevealedDark) { //BLUE//
				buttons[x][y].setBackground(revealedDark);
			}
			else if(buttons[x][y].getBackground() == pastelRevealedLight || buttons[x][y].getBackground() == neonRevealedLight) { //BLUE//
				buttons[x][y].setBackground(revealedLight);
			}
		}//ends dark
		
		if(type == "neon") {
			if(buttons[x][y].getBackground() == darkHiddenDark || buttons[x][y].getBackground() == pastelHiddenDark) { //BLUE//
				buttons[x][y].setBackground(hiddenDark);
			}
			else if(buttons[x][y].getBackground() == darkHiddenLight || buttons[x][y].getBackground() == pastelHiddenLight) { //BLUE//
				buttons[x][y].setBackground(hiddenLight);
			}
			else if(buttons[x][y].getBackground() == darkRevealedDark || buttons[x][y].getBackground() == pastelRevealedDark) { //BLUE//
				buttons[x][y].setBackground(revealedDark);
			}
			else if(buttons[x][y].getBackground() == darkRevealedLight || buttons[x][y].getBackground() == pastelRevealedLight) { //BLUE//
				buttons[x][y].setBackground(revealedLight);
			}
		}//ends neon
	}//ends method - update BackGround
	
	
	//COLOR NUMBERS//
	public void colorNums(int x, int y) {
		if(buttons[x][y].getText().equals("1")) { //BLUE//
			buttons[x][y].setForeground(num1Color);
		}
		else if(buttons[x][y].getText().equals("2")) { //GREEN//
			buttons[x][y].setForeground(num2Color);
		}
		else if(buttons[x][y].getText().equals("3") || buttons[x][y].getText().equals("🏲")) { //RED//
			buttons[x][y].setForeground(num3Color);
		}
		else if(buttons[x][y].getText().equals("4")) { //PURPLE//
			buttons[x][y].setForeground(num4Color);
		}
		else if(buttons[x][y].getText().equals("5")) { //YELLOW//
			buttons[x][y].setForeground(num5Color);
		}
		else if(buttons[x][y].getText().equals("6")) { //LIGHT BLUE//
			buttons[x][y].setForeground(num6Color);
		}
		else if(buttons[x][y].getText().equals("7")) { //DARK GREY//
			buttons[x][y].setForeground(num7Color);
		}
		else if(buttons[x][y].getText().equals("8")) { //LIGHT GREY//
			buttons[x][y].setForeground(num8Color);
		}
	}//ends method - colorNums
	
	
	//CHECK IF THE GAME WAS WON//
	public boolean checkWin() {
		for(int i = 0; i < buttons.length; i++) {
			for(int j = 0; j < buttons[i].length; j++) {
				if(!key[i][j].equals("💣")) {
//					if(buttons[i][j].getText() == "*") {
//						
//					}
					if(buttons[i][j].getText() == key[i][j]) {
						
					}
					else {
						return false;
					}
				}
			}//ends inner for
		}//ends outer for loop
		
		return true;
	}//ends method - checkWin
	
	
	
	//NEW GAME | CLEARS BOARD//
	public void clear() {
		Component[] contents = contentPane.getComponents();
		
		for(Component C: contents) {
			if(C instanceof JButton) {
				contentPane.remove(C);
				contentPane.repaint();
			}
		}
		
	}
	
}//ends class - MinesweeperGUI