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


public class Bishop extends Piece{
        public Bishop(boolean inplay, int x, int y, String color){
        super(inplay,x,y,3,color);
    }
    
        
    @Override    
    public boolean isValid(Board board, int startRow, int startCol, int endRow, int endCol){
        int BISHOP=3;
        
        if(super.isValid(board, startRow, startCol, endRow, endCol)==false){
            return false;
        }
        
        else if(Math.abs(board.returnvalue(startRow,startCol))!=BISHOP){
            return false;
        }
            
        else if(super.passesbishopRule(board, startRow, startCol, endRow, endCol)){
            return true;
        }
        
        else{
            return false;
        }
    } 
}
