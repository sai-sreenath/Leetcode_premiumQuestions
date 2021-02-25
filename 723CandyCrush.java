class Solution {
    public int[][] candyCrush(int[][] board) {
        
        //mark -ve for all that are +3 repeat vertical/horizontal_left
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                int value = Math.abs(board[i][j]);
                
                //check vertical
                if(i>=2 && value==Math.abs(board[i-1][j]) && value==Math.abs(board[i-2][j]))
                    board[i][j]=board[i-1][j]=board[i-2][j]=value* -1;
                
                //check horizontal
                if(j>=2 && value==Math.abs(board[i][j-1]) && value==Math.abs(board[i][j-2]))
                    board[i][j] = board[i][j-1] = board[i][j-2] = value*-1;
            }
        }
     
        
        //crush and Drop vertically - traverse from bottom right
        boolean recursiveCall = false;
        for(int i=board.length-1; i>=0; i--)
        {
            for(int j=board[0].length-1;j>=0;j--)
            {
                if(board[i][j]>=0)
                    continue;
                
                crushAndDropVertically(board,i,j);
                recursiveCall = true;
            }
        }
        
        //print board
        //return board
        return recursiveCall? candyCrush(board) : board;
    }
    
    void crushAndDropVertically(int[][] board, int i, int j)
    {
        //find +ve upwards
        int k = i-1;
        for(;k>=0;k--)
        {
            if(board[k][j]>0)
                break;
        }
        
        //keep making +ve drop
        while(k>=0)
        {
            board[i][j] = board[k][j];
            k--;
            i--;
        }
        
        //Now fill in zeros vertically
        while(i>=0)
        {
            board[i][j]=0;
            i--;
        }
    }
    
}
