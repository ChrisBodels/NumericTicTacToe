import java.awt.Font;
import java.util.Random;

import edu.princeton.cs.introcs.StdDraw;


public class NumericTicTacToe {
	
	private int move = 0;
	private boolean cpuWin = false, playerWin = false;
	private int[][] board;
	private int[] cpuNums, playerNums;
	private int[] availableNums;
	
	public NumericTicTacToe()
	{
		
	}
	
	public static void main(String[] args)
	{
		NumericTicTacToe app = new NumericTicTacToe();
		app.setup();
	}
	
	public void setup()
	{
		availableNums = new int[4];
		board = new int[3][3];
		cpuNums = new int[5];
		playerNums = new int[4];
		
		//sets up blank board
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				board[i][j] = 0;
			}
		}
		
		//sets up cpuNums and playerNums array
		int playerNumber = 2;
		int cpuNumber = 1;
		for(int i = 0; i < 5; i++)
		{
			cpuNums[i] = cpuNumber;
			cpuNumber += 2;
			if(i < 4)
			{
				playerNums[i] = playerNumber;
				availableNums[i] = playerNumber;
				playerNumber += 2;
			}
		}
		
		// Setup graphics and draw empty board
		StdDraw.setCanvasSize(512, 683);
		StdDraw.setPenRadius(0.02);		// draw thicker lines
		StdDraw.line(0, 0.5, 1, 0.5); //lower horizontal line
		StdDraw.line(0, 0.75, 1, 0.75); //upper horizontal line
		StdDraw.line(0.33, .25, 0.33, 1.0); //left vertical line
		StdDraw.line(0.66, .25, 0.66, 1.0); //right vertical line
		StdDraw.line(.25, 0 , .25, .20);
		StdDraw.line(.5, 0 , .5, .20);
		StdDraw.line(.75, 0 , .75, .20);
		StdDraw.line(0, .2, 1, .2);
		StdDraw.line(0, 0, 1, 0);
		StdDraw.line(0, 0, 0, .2);
		StdDraw.line(1, 0, 1, .2);
		StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 48));
		StdDraw.text(.125, .1, "2");
		StdDraw.text(.375, .1, "4");
		StdDraw.text(.625, .1, "6");
		StdDraw.text(.875, .1, "8");
				
		StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 64)); // Font SIZE!
		//StdDraw.text(.625, .1, "X"); //test
				
		game();
	}
	
	public void game()
	{
		for(move = 0; move < 9; move++)
		{
			if(cpuWin)
			{
				break;
			}
			else if(playerWin)
			{
				System.out.println("You won!!\n\nCongratulations!.");
				break;
			}
			else
			{
				if(move % 2 == 0)
				{
					cpuMove();
				}
				else
				{
					playerMove();
				}
			}
		}
		if(cpuWin)
		{
			//Could change this to print on top of game board rather than in console
			System.out.println("The computer won!\n\nBetter luck next time.");
		}
		if(move == 9 && !cpuWin && !playerWin)
		{
			//Could change this to print on top of game board rather than in console
			System.out.println("It's a draw!\n\nMaybe next time you'll win...");
		}
	}
	
	public void cpuMove()
	{
		Random numberGen = new Random();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		int col = 0, row = 0;
		if(move == 0)
		{
			double x = 124;
			double y = 256;
			col = numberGen.nextInt(3);
			row = numberGen.nextInt(3);
			int randNum = numberGen.nextInt(5);
			int chosenNumber = cpuNums[randNum];
			String number = "" + chosenNumber + "";
			cpuNums[randNum] = 0;
			if(col == 0)
			{
				x = .165;
			}
			else if(col == 1)
			{
				x = .495;
			}
			else if(col == 2)
			{
				x = .825;
			}
			if(row == 0)
			{
				y = .375;
			}
			else if(row == 1)
			{
				y = .625;
			}
			else if(row == 2)
			{
				y = .875;
			}
			board[col][row] = chosenNumber;
			StdDraw.text(x, y, number);
		}
		//CHECKS HERE TO TRY GET WINNING MOVE
		
		
		else
		{
			double x = 124;
			double y = 256;
			int chosenNumber = 1243;
			String number = "";
			boolean validNumFound = false, validSpaceFound = false;
			while(!validNumFound)
			{
				int randNum = numberGen.nextInt(5);
				if(cpuNums[randNum] != 0)
				{
					chosenNumber = cpuNums[randNum];
					number = "" + chosenNumber + "";
					cpuNums[randNum] = 0;
					validNumFound = true;
				}
			}
			
			while(!validSpaceFound)
			{
				col = numberGen.nextInt(3);
				row = numberGen.nextInt(3);
				if(board[col][row] == 0)
				{
					board[col][row] = chosenNumber;
					validSpaceFound = true;
				}
			}
			if(col == 0)
			{
				x = .165;
			}
			else if(col == 1)
			{
				x = .495;
			}
			else if(col == 2)
			{
				x = .825;
			}
			if(row == 0)
			{
				y = .375;
			}
			else if(row == 1)
			{
				y = .625;
			}
			else if(row == 2)
			{
				y = .875;
			}
			StdDraw.text(x, y, number);
		}
	}
	
	public void playerMove() //NEED TO CHANGE ALL THIS TO ACCOMODATE NEW BOARD
	{
		boolean mousePressed = false;
		boolean numAvailable = false;
		int numCol = 1243, numRow = 1243, selectedNum = 1234;
		
		System.out.println("\nPlease select an available number from the selection.");
		do{
			
			if(StdDraw.mousePressed() && StdDraw.mouseY() < .21)
			{
				//numCol = (int) (StdDraw.mouseX() * 2.5);
				if(StdDraw.mouseX() > 0 && StdDraw.mouseX() < .25)
				{
					numCol = 0;
				}
				else if(StdDraw.mouseX() > .24 && StdDraw.mouseX() < .5)
				{
					numCol = 1;
				}
				else if(StdDraw.mouseX() > .49 && StdDraw.mouseX() < .75)
				{
					numCol = 2;
				}
				else
				{
					numCol = 3;
				}
				
				if(availableNums[numCol] != 0)
				{
					selectedNum = availableNums[numCol];
					availableNums[numCol] = 0;
					numAvailable = true;
				}
				mousePressed = true;
			}
			
		}while(!mousePressed || !numAvailable);
		
		double numX = .5;
		if(numCol == 0)
		{
			numX = .125;
		}
		else if(numCol == 1)
		{
			numX = .375;
		}
		else if(numCol == 2)
		{
			numX = .625;
		}
		else
		{
			numX = .875;
		}
		StdDraw.text(numX, .1, "O");
		 mousePressed = false;
		 int col = 5, row = 5;
		 
		 System.out.println("\nPlease select where you would like to put that number.\n");
		 
       do {

           if (StdDraw.mousePressed() && StdDraw.mouseY() > .24) {

               col = (int) (StdDraw.mouseX() * 3);	//FINE

               if(StdDraw.mouseY() > .24 && StdDraw.mouseY() < .5)
               {
            	   row = 0;
               }
               else if(StdDraw.mouseY() > .49 && StdDraw.mouseY() < .75)
               {
            	   row = 1;
               }
               else if(StdDraw.mouseY() > .74)
               {
            	   row = 2;
               }
               //row = (int) (StdDraw.mouseY() * 3);

               mousePressed = true;

           }

       }while(!mousePressed || board[col][row] != 0);

       board[col][row] = selectedNum;   // valid move (empty slot)
       double x = col * .33 + 0.15;
       double y = .5;
       if(row == 0)
       {
    	   y = .375;
       }
       else if(row == 1)
       {
    	   y = .625;
       }
       else
       {
    	   y = .875;
       }
       StdDraw.text(numX, .1, "X");
       String number = "" + selectedNum +"";
       //double y = row * .53 + 0.15;
       StdDraw.text(x, y,  number);
	}
	
}