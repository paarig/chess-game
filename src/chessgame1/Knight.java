/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame1;

/**
 *
 * @author paari
 */
public class Knight extends Piece{
        public Knight(boolean inplay, int x, int y, String color){
        super(inplay,x,y,2,color);
    }
    
    @Override                
    public boolean isValid(Board board, int startRow, int startCol, int endRow, int endCol){
        int KNIGHT=2;
        
        if(super.isValid(board, startRow, startCol, endRow, endCol)==false){
            return false;
        }
        
        else if(Math.abs(board.returnvalue(startRow,startCol))!=KNIGHT){
            return false;
        }     
        
        else if(Math.abs(startRow-endRow)==2&&Math.abs(startCol-endCol)==1){
            return true; //knight moves down or up 2 and moves left or right 1
        }
        
        else if(Math.abs(startRow-endRow)==1&&Math.abs(startCol-endCol)==2){
            return true; //knight moves down or up 1 and moves left or right 2
        }
        else{
            return false;
        }
    }
}
