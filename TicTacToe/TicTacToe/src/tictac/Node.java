package tictac;

import java.util.ArrayList;
import java.util.Arrays;

public class Node {
	public char[][] state;
	public boolean isMax;
	public int backupValue;
	public int utility;
	public ArrayList<char[][]> childList = new ArrayList<char[][]>();
	public ArrayList<Node> childnList;

	public Node(char[][] state, boolean isMax) {
		this.state = state;
		this.isMax = isMax;
		this.backupValue = 0;
		this.childnList = new ArrayList<Node>();
		
		if(isMax)
		{
			this.utility = evaluate(isMax, state) - evaluate(!isMax, state);
		}
		else
		{
			this.utility = evaluate(!isMax, state) - evaluate(isMax, state);
		}
		
		this.childList = children(isMax, state); 
		
	}

	
    public static ArrayList<char[][]> children(boolean isMax, char[][] state) 
    {
    	char symbol = ' ';
    	if(isMax)
    	{
    		symbol = 'X';
    	}
    	else
    	{
    		symbol = 'O';
    	}
    	ArrayList<char[][]> children = new ArrayList<char[][]>();
    	char[][]  state_copy;
    	state_copy = new char[3][3];
    	for(int i = 0 ; i < 3 ; i++)
    	{
    		for(int j = 0 ; j < 3 ; j++)
    		{
    			if(state[i][j] == ' ')
    			{

    		    	state_copy = new char[3][3];
    				for(int k = 0 ; k < 3 ; k++)
    				{
    				    state_copy[k] = Arrays.copyOf(state[k], state[k].length);
    				}
    				state_copy[i][j] = symbol;
    				
    				children.add(state_copy);
    			}
    		}
    	}
		 
    	return children;
	  
    }
    
    public int evaluate(boolean isMax, char[][] state)
    {
    	int row = 0;
    	int col = 0;
    	int emptyr = 0;
    	int emptyc = 0;
    	char symbol=' ';
    	if(isMax)
    	{
    		symbol = 'O';
    	}
    	else
    	{
    		symbol = 'X';
    	}
    	for(int i = 0 ; i < 3 ; i++)
    	{
    		int flagr = 0;
    		int flagc = 0;
    		int fer = 0;
    		int fec = 0;
    		for(int j = 0 ; j < 3 ; j++)
    		{
                if(state[i][j] != ' ')
                    fer = 1;
                if(state[j][i] != ' ')
                    fec = 1;
                if(state[i][j] == symbol)
                    flagr=1;
                if(state[j][i] == symbol)
                    flagc=1;
    		}
            if(flagr == 0)
                row = row + 1;
            if(flagc == 0)
            	col = col + 1;
            if(fer == 0)
                emptyr += 1;
            if(fec == 0)
                emptyc += 1;
    	}
        int diag = 0;
        int emptyd = 0;
        if(state[1][1] == symbol)
            diag = 0;
        else
        {
            if(state[0][0] != symbol && state[2][2] != symbol)
                diag = diag + 1;
            if(state[0][2] != symbol && state[2][0] != symbol)
                diag = diag + 1;
            if(state[1][1] != ' ')
                emptyd = 0;
            else
            {
                if(state[0][0] == ' ' && state[2][2] == ' ')
                    emptyd = emptyd + 1;
                if(state[0][2] == ' ' && state[2][0] == ' ')
                    emptyd = emptyd + 1;
            }
        }
        int empty = emptyr + emptyc + emptyd;
        int wins = row + col + diag;
        return wins - empty;	
    		
    		
    }
	 

}
