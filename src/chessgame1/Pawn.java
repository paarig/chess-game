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
public class Pawn extends Piece{
        public Pawn(boolean inplay, int x, int y, String color){
        super(inplay,x,y,1,color);
    }
    
        
    @Override    
    public boolean isValid(Board board, int startRow, int startCol, int endRow, int endCol){
        int BLACKPAWN=-1;
        int WHITEPAWN=1;
        
        if(super.isValid(board, startRow, startCol, endRow, endCol)==false){
            return false; //piece not moving at all
        }
        
        else if(board.returnvalue(startRow,startCol)==BLACKPAWN&&startRow==6&&(startRow-endRow)==(2)&&startCol==endCol&&(board.isOccupied(endRow,endCol))==false&&(board.isOccupied(endRow+1,startCol))==false){
            return true; //black pawn moving two spots ahead
        }
        
        else if(board.returnvalue(startRow,startCol)==WHITEPAWN&&startRow==1&&(endRow-startRow)==(2)&&startCol==endCol&&(board.isOccupied(endRow,startCol))==false&&(board.isOccupied(endRow-1,startCol))==false){
            return true; //white pawn moving two spots ahead
        }
        
        else if(startRow-endRow==(1)&&endCol==startCol&&(board.returnvalue(startRow,startCol)==BLACKPAWN)&&board.isOccupied(endRow, endCol)==false){
            return true; //black pawn moving one spot ahead
        }
        
        else if(endRow-startRow==(1)&&endCol==startCol&&(board.returnvalue(startRow,startCol)==WHITEPAWN)&&board.isOccupied(endRow, endCol)==false){
            return true; //white pawn moving one spot ahead
        }
        
        else if(startRow-endRow==(1)&&Math.abs(startCol-endCol)==1&&(board.returnvalue(startRow,startCol)==BLACKPAWN)&&board.returnvalue(endRow, endCol)>0){
            return true; //black pawn capturing
        }
        
        else if(endRow-startRow==(1)&&Math.abs(startCol-endCol)==1&&(board.returnvalue(startRow,startCol)==WHITEPAWN)&&(board.returnvalue(endRow, endCol)<0)){
            return true; //white pawn capturing
        }
        
        else{
            return false;
        }    
    }
}
