import java.awt.Font;
import java.util.Random;

import edu.princeton.cs.introcs.StdDraw;


/**
 * My game of numeric Tic Tac Toe
 * Aim of the game is to get any row column or diagonal to add up to 15 on your turn,
 * computer gets all odd numbers between 1 and 9 and goes first, player gets all even numbers between 1 and 9.
 * 
 * 
 * @author Chris Bodels
 * 
 * Last updated: 28 January 2016
 *
 */
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
					for(int i = 0; i < 3; i++)
				       {
				    	   if(board[i][0] + board[i][1] + board[i][2] == 15 && board[i][0] != 0 && board[i][1] !=0 && board [i][2] != 0)
				    	   {
				    		   cpuWin = true;
				    	   }
				    	   if(board[0][i] + board[1][i] + board[2][i] == 15 && board[0][i] != 0 && board[1][i] !=0 && board [2][i] != 0)
				    	   {
				    		   cpuWin = true;
				    	   }
				       }
				       if((board[0][0] + board[1][1] + board[2][2]) == 15 && board[0][0] != 0 && board[1][1] !=0 && board [2][2] != 0)
				       {
				       		cpuWin = true;
				       }
				       if((board[0][2] + board [1][1] + board[2][0]) == 15 && board[2][0] != 0 && board[1][1] !=0 && board [0][2] != 0)
				       {
				       		cpuWin = true;
				       }
					
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
		boolean winningMoveFound = false;
		boolean winningMoveFoundInCol = false;
		boolean winningMoveFoundInRow = false;
		boolean winningMoveFoundInDiagonal1 = false;
		boolean winningMoveFoundInDiagonal2 = false;
		boolean playerWinningMoveFound = false;
		boolean playerWinningMoveFoundInCol = false;
		boolean playerWinningMoveFoundInRow = false;
		boolean playerWinningMoveFoundInDiagonal1 = false;
		boolean playerWinningMoveFoundInDiagonal2 = false;
		int winningNum = 0;
		Random numberGen = new Random();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		int col = 0, row = 0;
		
		
		//CHECKS HERE TO TRY GET WINNING MOVE
		if(move != 0)
		{
			//checks columns for winning move
			for(int i = 0; i < 3; i++)
			{
				if(board[i][0] == 0 || board[i][1] == 0 || board[i][2] == 0)
				{
					if(board[i][0] + board[i][1] != 0 && board[i][0] + board[i][2] != 0 && board[i][2] + board[i][1] != 0)
					{
						int targetNum = (board[i][0] + board[i][1] + board[i][2]);
						for(int j = 0; j < 5; j++)
						{
							if(cpuNums[j] != 0)
							{
								if(15 - targetNum == cpuNums[j])
								{
									winningMoveFound = true;
									winningMoveFoundInCol = true;
									winningNum = cpuNums[j];
									col = i;
									break;
								}
							}
						}
					}
				}
				if(winningMoveFound)
				{
					break;
				}
			}
			//checks row for winning move
			if(!winningMoveFound)
			{
				for(int i = 0; i < 3; i++)
				{
					if(board[0][i] == 0 || board[1][i] == 0 || board[2][i] == 0)
					{
						if(board[0][i] + board[1][i] != 0 && board[0][i] + board[2][i] != 0 && board[1][i] + board[2][i] != 0)
						{
							int targetNum = (board[0][i] + board[1][i] + board[2][i]);
							for(int j = 0; j < 5; j++)
							{
								if(cpuNums[j] != 0)
								{
									if(15 - targetNum == cpuNums[j])
									{
										winningMoveFound = true;
										winningMoveFoundInRow = true;
										winningNum = cpuNums[j];
										row = i;
										break;
									}
								}
							}
						}
					}
					if(winningMoveFound)
					{
						break;
					}
				}
			}
			//checks diagonal 1 for winning move
			if(!winningMoveFound)
			{
				if(board[0][0] == 0 || board[1][1] == 0 || board[2][2] == 0)
				{
					if(board[0][0] + board[1][1] != 0 && board[0][0] + board[2][2] != 0 && board[1][1] + board[2][2] != 0)
					{
						int targetNum = (board[0][0] + board[1][1] + board[2][2]);
						for(int j = 0; j < 5; j++)
						{
							if(cpuNums[j] != 0)
							{
								if(15 - targetNum == cpuNums[j])
								{
									winningMoveFound = true;
									winningMoveFoundInDiagonal1 = true;
									winningNum = cpuNums[j];
									break;
								}
							}
						}
					}
				}
			}
			//checks diagonal 2 for winning move
			if(!winningMoveFound)
			{
				if(board[2][0] == 0 || board[1][1] == 0 || board[0][2] == 0)
				{
					if(board[2][0] + board[1][1] != 0 && board[2][0] + board[0][2] != 0 && board[1][1] + board[0][2] != 0)
					{
						int targetNum = (board[2][0] + board[1][1] + board[0][2]);
						for(int j = 0; j < 3; j++)
						{
							if(cpuNums[j] != 0)
							{
								if(15 - targetNum == cpuNums[j])
								{
									winningMoveFound = true;
									winningMoveFoundInDiagonal2 = true;
									winningNum = cpuNums[j];
									break;
								}
							}
						}
					}
				}
			}
		}
		
		if(winningMoveFound)
		{
			String number = "" + winningNum + "";
			if(winningMoveFoundInCol)
			{
				if(board[col][0] == 0)
				{
					double x = col * .33 + .15;
					StdDraw.text(x, .375, number);
					board[col][0] = winningNum;
				}
				if(board[col][1] == 0)
				{
					double x = col * .33 + .15;
					StdDraw.text(x, .625, number);
					board[col][1] = winningNum;
				}
				if(board[col][2] == 0)
				{
					double x = col * .33 + .15;
					StdDraw.text(x, .875, number);
					board[col][2] = winningNum;
				}
			}
			if(winningMoveFoundInRow)
			{
				if(board[0][row] == 0)
				{
					double y = row * .25  +.375;
					StdDraw.text(.15, y, number);
					board[0][row] = winningNum;
				}
				if(board[1][row] == 0)
				{
					double y = row * .25  +.375;
					StdDraw.text(.48, y, number);
					board[1][row] = winningNum;
				}
				if(board[2][row] == 0)
				{
					double y = row * .25  +.375;
					StdDraw.text(.81, y, number);
					board[2][row] = winningNum;
				}
			}
			if(winningMoveFoundInDiagonal1)
			{
				if(board[0][0] == 0)
				{
					StdDraw.text(.15, .375, number);
					board[0][0] = winningNum;
				}
				if(board[1][1] == 0)
				{
					StdDraw.text(.48, .625, number);
					board[1][1] = winningNum;
				}
				if(board[2][2] == 0)
				{
					StdDraw.text(.81, .875, number);
					board[2][2] = winningNum;
				}
			}
			if(winningMoveFoundInDiagonal2)
			{
				if(board[0][2] == 0)
				{
					StdDraw.text(.15, .875, number);
					board[0][2] = winningNum;
				}
				if(board[1][1] == 0)
				{
					StdDraw.text(.48, .625, number);
					board[1][1] = winningNum;
				}
				if(board[2][0] == 0)
				{
					StdDraw.text(.81, .375, number);
					board[2][0] = winningNum;
				}
			}
		}
		
		if(!winningMoveFound && move != 0)
		{
			//checks columns for winning move for player if no winning move for computer can be found
			for(int i = 0; i < 3; i++)
			{
				if(board[i][0] == 0 || board[i][1] == 0 || board[i][2] == 0)
				{
					if(board[i][0] + board[i][1] != 0 && board[i][0] + board[i][2] != 0 && board[i][2] + board[i][1] != 0)
					{
						int targetNum = (board[i][0] + board[i][1] + board[i][2]);
						for(int j = 0; j < 4; j++)
						{
							if(playerNums[j] != 0)
							{
								if(15 - targetNum == playerNums[j])
								{
									playerWinningMoveFound = true;
									playerWinningMoveFoundInCol = true;
									col = i;
									break;
								}
							}
						}
					}
				}
				if(playerWinningMoveFound)
				{
					break;
				}
			}
			
			if(!playerWinningMoveFound)
			{
				//checks columns for winning move for player if no winning move for computer can be found
				for(int i = 0; i < 3; i++)
				{
					if(board[0][i] == 0 || board[1][i] == 0 || board[2][i] == 0)
					{
						if(board[0][i] + board[1][i] != 0 && board[0][i] + board[2][i] != 0 && board[2][i] + board[1][i] != 0)
						{
							int targetNum = (board[0][i] + board[1][i] + board[2][i]);
							for(int j = 0; j < 4; j++)
							{
								if(playerNums[j] != 0)
								{
									if(15 - targetNum == playerNums[j])
									{
										playerWinningMoveFound = true;
										playerWinningMoveFoundInRow = true;
										row = i;
										break;
									}
								}
							}
						}
					}
					if(playerWinningMoveFound)
					{
						break;
					}
				}
			}
			if(!playerWinningMoveFound)
			{
				//checks diagonal1 for winning move for player if no winning move for computer can be found
				if(board[0][0] == 0 || board[1][1] == 0 || board[2][2] == 0)
				{
					if(board[0][0] + board[1][1] != 0 && board[0][0] + board[2][2] != 0 && board[1][1] + board[2][2] != 0)
					{
						int targetNum = (board[0][0] + board[1][1] + board[2][2]);
						for(int j = 0; j < 4; j++)
						{
							if(playerNums[j] != 0)
							{
								if(15 - targetNum == playerNums[j])
								{
									playerWinningMoveFound = true;
									playerWinningMoveFoundInDiagonal1 = true;
									break;
								}
							}
						}
					}
				}
			}
			
			if(!playerWinningMoveFound)
			{
				//checks diagonal2 for winning move for player if no winning move for computer can be found
				if(board[2][0] == 0 || board[1][1] == 0 || board[0][2] == 0)
				{
					if(board[2][0] + board[1][1] != 0 && board[2][0] + board[0][2] != 0 && board[1][1] + board[0][2] != 0)
					{
						int targetNum = (board[2][0] + board[1][1] + board[0][2]);
						for(int j = 0; j < 4; j++)
						{
							if(playerNums[j] != 0)
							{
								if(15 - targetNum == playerNums[j])
								{
									playerWinningMoveFound = true;
									playerWinningMoveFoundInDiagonal2 = true;
									break;
								}
							}
						}
					}
				}
			}
			
			if(playerWinningMoveFound)
			{
				int chosenNumber = randomCPUNumSelector();
				String number = "" + chosenNumber + "";
				if(playerWinningMoveFoundInCol)
				{
					if(board[col][0] == 0)
					{
						double x = col * .33 + .15;
						StdDraw.text(x, .375, number);
						board[col][0] = chosenNumber;
					}
					if(board[col][1] == 0)
					{
						double x = col * .33 + .15;
						StdDraw.text(x, .625, number);
						board[col][1] = chosenNumber;
					}
					if(board[col][2] == 0)
					{
						double x = col * .33 + .15;
						StdDraw.text(x, .875, number);
						board[col][2] = chosenNumber;
					}
				}
				if(playerWinningMoveFoundInRow)
				{
					if(board[0][row] == 0)
					{
						double y = row * .25  +.375;
						StdDraw.text(.15, y, number);
						board[0][row] = chosenNumber;
					}
					if(board[1][row] == 0)
					{
						double y = row * .25  +.375;
						StdDraw.text(.48, y, number);
						board[1][row] = chosenNumber;
					}
					if(board[2][row] == 0)
					{
						double y = row * .25  +.375;
						StdDraw.text(.81, y, number);
						board[2][row] = chosenNumber;
					}
				}
				if(playerWinningMoveFoundInDiagonal1)
				{
					if(board[0][0] == 0)
					{
						StdDraw.text(.15, .375, number);
						board[0][0] = chosenNumber;
					}
					if(board[1][1] == 0)
					{
						StdDraw.text(.48, .625, number);
						board[1][1] = chosenNumber;
					}
					if(board[2][2] == 0)
					{
						StdDraw.text(.81, .875, number);
						board[2][2] = chosenNumber;
					}
				}
				if(playerWinningMoveFoundInDiagonal2)
				{
					if(board[0][2] == 0)
					{
						StdDraw.text(.15, .875, number);
						board[0][2] = chosenNumber;
					}
					if(board[1][1] == 0)
					{
						StdDraw.text(.48, .625, number);
						board[1][1] = chosenNumber;
					}
					if(board[2][0] == 0)
					{
						StdDraw.text(.81, .375, number);
						board[2][0] = chosenNumber;
					}
				}
			}
		}
		
		else if(!winningMoveFound && !playerWinningMoveFound)
		{
			boolean validSpaceFound = false;
			int chosenNumber = randomCPUNumSelector();
			String number = "";
			number = "" + chosenNumber + "";

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
			double x = col * .33 + .15;
			double y = row * .25 + .375;
			StdDraw.text(x, y, number);
		}
	}
	
	public void playerMove()
	{
		boolean mousePressed = false;
		boolean numAvailable = false;
		int numCol = 1243, selectedNum = 1234;
		
		System.out.println("\nPlease select an available number from the selection.");
		do{
			
			if(StdDraw.mousePressed() && StdDraw.mouseY() < .21)
			{
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

               col = (int) (StdDraw.mouseX() * 3);

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

               mousePressed = true;

           }

       }while(!mousePressed || board[col][row] != 0);

       board[col][row] = selectedNum;   // valid move (empty slot)
       double x = col * .33 + 0.15;
       double y = row * .25 + .375;
       StdDraw.text(numX, .1, "X");
       String number = "" + selectedNum +"";
       StdDraw.text(x, y,  number);
       
       for(int i = 0; i < 3; i++)
       {
    	   if(board[i][0] + board[i][1] + board[i][2] == 15 && board[i][0] != 0 && board[i][1] !=0 && board [i][2] != 0)
    	   {
    		   playerWin = true;
    	   }
    	   if(board[0][i] + board[1][i] + board[2][i] == 15 && board[0][i] != 0 && board[1][i] !=0 && board [2][i] != 0)
    	   {
    		   playerWin = true;
    	   }
       }
       if((board[0][0] + board[1][1] + board[2][2]) == 15 && board[0][0] != 0 && board[1][1] !=0 && board [2][2] != 0)
       {
       		playerWin = true;
       }
       if((board[0][2] + board [1][1] + board[2][0]) == 15 && board[2][0] != 0 && board[1][1] !=0 && board [0][2] != 0)
       {
       		playerWin = true;
       }
	}
	
	public int randomCPUNumSelector()
	{
		int chosenNumber = 1243;
		boolean validNumFound = false;
		Random numberGen = new Random();
		while(!validNumFound)
		{
			int randNum = numberGen.nextInt(5);
			if(cpuNums[randNum] != 0)
			{
				chosenNumber = cpuNums[randNum];
				cpuNums[randNum] = 0;
				validNumFound = true;
			}
		}
		return chosenNumber;
	}
}
