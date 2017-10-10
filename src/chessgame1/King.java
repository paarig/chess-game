    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame1;

import java.util.ArrayList;

/**
 *
 * @author paari
 */
public class King extends Piece{
    public King(boolean inplay, int x, int y, String color){
        super(inplay,x,y,100,color);
    }
    
    
    @Override
    public boolean isValid(Board board, int startRow, int startCol, int endRow, int endCol){
        int KING=100;
        
        if(super.isValid(board, startRow, startCol, endRow, endCol)==false){
            return false;
        }
        
        else if(Math.abs(board.returnvalue(startRow,startCol))!=KING){
            return false;
        }
        
        else if(isinCheck(board,this.getColor(),endRow,endCol)==true){
            return false;
        }
        
        else if(Math.abs(startRow-endRow)==0&&Math.abs(startCol-endCol)==1){
            return true;
        }
        
        else if(Math.abs(startRow-endRow)==1&&Math.abs(startCol-endCol)==0){
            return true;
        }
        
        else if(Math.abs(startRow-endRow)==1&&Math.abs(startCol-endCol)==1){
            return true;
        }        
        
        
        else{
            return false;
        }
    }

        //(x,y) is where a King is currently located or where a King is going to move
        public boolean isinCheck(Board board,String color,int x, int y){
        boolean inCheck=false;
        ArrayList<Spot> Spotlist=new ArrayList<Spot>();
        if(color=="white"){
            Spotlist=board.getallPieces("black");
        }
        if(color=="black"){
            Spotlist=board.getallPieces("white");
        }
        
        for(int i=0;i<Spotlist.size();i++){
            if(
            Spotlist.get(i).returnPiece().isValid(board,Spotlist.get(i).returnPiece().getX(),Spotlist.get(i).returnPiece().getY(),x,y)==true){
                inCheck=true;
                break;
                }
        }
        return inCheck;
        }
    
}
