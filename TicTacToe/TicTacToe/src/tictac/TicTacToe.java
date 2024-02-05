package tictac;

import java.util.Scanner;

public class TicTacToe {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		char[][] gameBoard = {{' ', ' ', ' '}, 
				              {' ', ' ', ' '}, 
				              {' ', ' ', ' '}}; 
		
		System.out.println("Please enter your name: ");
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
	    String user = sc.nextLine();
		
		System.out.println("Let's start the game!");
		System.out.println("Choose who should play first: 1. Computer 2. "+user);
		int choice = sc.nextInt();
	
		printGameBoard(gameBoard);
		System.out.println("---------------");
		
		Node obj = new Node(gameBoard, true); 
		
		if(choice == 1) { GamePlay.playcom(obj); }
		else if(choice == 2 ) { GamePlay.playuser(obj); }
		
		
	}
	
	public static void printGameBoard(char[][] gameBoard)
	{
		for(int i = 0 ; i < 3 ; i++)
		{
			for(int j = 0 ; j < 3 ; j++)
			{
				if(j != 2)
				{
				    System.out.print(gameBoard[i][j] + "|");
				}
				else
				{
					System.out.print(gameBoard[i][j]);
				}
			}
			System.out.println();
			if(i != 2)
			{
			    System.out.println("-+-+-");
			}
		}
	}
	
	public static void placePiece(char[][] gameBoard, int pos, String player)
	{
		char symbol = ' ';
		if(player.equals("max"))
		{
			symbol = 'X';
		}
		else if(player.equals("min"))
		{
			symbol = 'O';
		}
		switch(pos)
		{
		    case 1: gameBoard[0][0] = symbol;
		    break;
		    
		    case 2: gameBoard[0][1] = symbol;
		    break;
		    
		    case 3: gameBoard[0][2] = symbol;
		    break;
		    
		    case 4: gameBoard[1][0] = symbol;
		    break;
		    
		    case 5: gameBoard[1][1] = symbol;
		    break;
		    
		    case 6: gameBoard[1][2] = symbol;
		    break;
		    
		    case 7: gameBoard[2][0] = symbol;
		    break;
		    
		    case 8: gameBoard[2][1] = symbol;
		    break;
		    
		    case 9: gameBoard[2][2] = symbol;
		    break;
		    
		    default: break;
		     
		}
	}

}
