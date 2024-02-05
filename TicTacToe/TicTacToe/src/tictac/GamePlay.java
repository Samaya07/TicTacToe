package tictac;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GamePlay 
{
	public static void playcom(Node board)
	{
		ArrayList<Node> choice_nodes = new ArrayList<Node>();
		int index = 7;
		System.out.println("Computer's turn");
		int choice_value = minimax(board, 0);
				
		for(Node child : board.childnList)
		{
			if(child.backupValue == choice_value)
			{
				choice_nodes.add(child);
			}
		}
		
		
	    index = (int) (Math.random() * choice_nodes.size()); 
	    
		Node chosen_node = choice_nodes.get(index);
		
		TicTacToe.printGameBoard(chosen_node.state);
		
		Boolean[] resx = win(chosen_node.state);
		if(resx[0])
		{
			System.out.println("Computer won the game!");
			return;
		}
		else if(resx[1])
		{
			System.out.println("Congratulations!! You won the game.");
			return;
		}
		else if(resx[2])
		{
			System.out.println("It's a draw!");
			return;
		}
		
		System.out.println("Your turn to play. Enter the location where you want to insert O");
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int pos = sc.nextInt();
		char[][] edit_board = chosen_node.state;
		
		TicTacToe.placePiece(edit_board, pos, "min");
		
		Node final_board = new Node(edit_board, board.isMax);
		board = final_board;
		
	    TicTacToe.printGameBoard(board.state);
		
		Boolean[] reso = win(board.state);
		if(reso[0])
		{
			System.out.println("Computer won the game!");
			return;
		}
		else if(reso[1])
		{
			System.out.println("Congratulations!! You won the game.");
			return;
		}
		else if(reso[2])
		{
			System.out.println("It's a draw!");
			return;
		}
		
		playcom(board);
		
	}
	
	public static void playuser(Node board)
	{
        System.out.println("Your turn to play. Enter the location where you want to insert X");
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int pos = sc.nextInt();
		char[][] edit_board = board.state;
		
		TicTacToe.placePiece(edit_board, pos, "max");
		
		Node user_board = new Node(edit_board, !board.isMax);
		
	    TicTacToe.printGameBoard(user_board.state);
		
		Boolean[] reso = win(user_board.state);
		if(reso[0])
		{
			System.out.println("Congratulations!! You won the game.");
			return;
		}
		else if(reso[1])
		{
			System.out.println("Computer won the game!");
			return;
		}
		else if(reso[2])
		{
			System.out.println("It's a draw!");
			return;
		}
		
		
		ArrayList<Node> choice_nodes = new ArrayList<Node>();
		int index = 7;
		System.out.println("Computer's turn");
		int choice_value = minimax(user_board, 0);
				
		for(Node child : user_board.childnList)
		{
			if(child.backupValue == choice_value)
			{
				choice_nodes.add(child);
			}
		}
		
	    index = (int) (Math.random() * choice_nodes.size()); 
	    
		Node chosen_node = choice_nodes.get(index);
		
		TicTacToe.printGameBoard(chosen_node.state);
		
		Boolean[] resx = win(chosen_node.state);
		if(resx[0])
		{
			System.out.println("Congratulations!! You won the game.");
			return;
		}
		else if(resx[1])
		{
			System.out.println("Computer won the game!");
			return;
		}
		else if(resx[2])
		{
			System.out.println("It's a draw!");
			return;
		}
		
		board = chosen_node;
		playuser(board);
	}
	
	
	
	public static int minimax(Node state, int n)
	{
		ArrayList<Integer> value = new ArrayList<Integer>();
		Boolean[] res = win(state.state);
		if(n == 3 || state.childList.size() == 0 || res[0] || res[1])
		{
			
			if(res[0])
			{
				state.utility = state.utility + 3;
				state.backupValue = state.utility;
				return state.utility;
			}
			else if(res[1])
			{
				state.utility = state.utility - 3;
				state.backupValue = state.utility;
				return state.utility;
			}
			return state.utility;
		}		
		
		for(char[][] child : state.childList)
		{				
			Node childn = new Node(child,!state.isMax);		
			
			state.childnList.add(childn);
			
			value.add(minimax(childn, n+1));
		}
		
		if(state.isMax)
		{
			state.backupValue = Collections.max(value);
			return Collections.max(value);
		}
		else
		{
			state.backupValue = Collections.min(value);
			return Collections.min(value);
		}
		
	}
	
	public static Boolean[] win(char[][] state)
	{
		boolean winx = false;
		boolean wino = false;
		boolean draw = false;
		int flagd1x = 0;
		int flagd1o = 0;
		int flagd2x = 0;
		int flagd2o = 0;
		int flagf = 0;
		for(int i = 0 ; i < 3 ; i++)
		{
			int flagrx = 0;
			int flagcx = 0;
			int flagro = 0;
			int flagco = 0;
			for(int j = 0 ; j < 3 ; j++)
			{
				if(state[i][j] != 'X') { flagrx = 1; }
				
				if(state[j][i] != 'X') { flagcx = 1; }
	               
	            if(state[i][j] != 'O') { flagro = 1; }
	             
	            if(state[j][i] != 'O') { flagco = 1; }
	            
	            if(state[i][j] == ' ') { flagf = 1; }
				
			}
			if(flagrx == 0 || flagcx == 0) { winx = true; }
	          
	        if(flagro == 0 || flagco == 0) { wino = true; }
	       
		}
		
		if(winx != true)
		{
			if(state[1][1] != 'X') { flagd1x = 1; flagd2x = 1; }
			
			else
			{
			    if(state[0][0] != 'X' || state[2][2] != 'X') { flagd1x = 1; }
			    
			    if(state[0][2] != 'X' || state[2][0] != 'X') { flagd2x = 1; }
			}
			
			if(flagd1x == 0 || flagd2x == 0) { winx = true; }
	     }
		if(wino != true)
		{
			if(state[1][1] != 'O') { flagd1o = 1; flagd2o = 1; }
			
			else
			{
			    if(state[0][0] != 'O' || state[2][2] != 'O') { flagd1o = 1; }
			    
			    if(state[0][2] != 'O' || state[2][0] != 'O') { flagd2o = 1; }
			}
			
			if(flagd1o == 0 || flagd2o == 0) { wino = true; }
	    }
		
		if(flagf == 0)
		{
			draw = true;
		}
		Boolean[] res = new Boolean[3];
		res[0] = winx;
		res[1] = wino;
		res[2] = draw;
		
		return res;
		
			    
	}

}
