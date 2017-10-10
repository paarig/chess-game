/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame1;

import javafx.scene.paint.Color;

/**
 *
 * @author paari
 */
public class Piece {
    private boolean inplay;
    private int x;
    private int y;
    private int pieceval;
    private String color;
    
    public Piece(boolean inplay, int x, int y, int pieceval,String color){
        this.inplay=inplay;
        this.x=x;
        this.y=y;
        this.pieceval=pieceval;
        this.color=color;
        if(this.color.equals("black")){
            this.pieceval*=-1;
        }
    }

    public void printPiece()
    {
        System.out.printf("Piece.printPiece() x:%d y:%d pieceval:%d color:%s", x,y,pieceval, color);
    }
    
    public int getX(){
        return x;
    }
    
    public void setX(int x){
        this.x=x;
    }
    
    public int getY(){
        return y;
    }
    
    public void setY(int y){
        this.y=y;
    }
    
    public int getPieceval(){
        return pieceval;
    }
    
    public void setPieceval(int pieceval){
        this.pieceval=pieceval;
    }
    
    public String getColor(){
        return color;
    }
    
    public void setColor(String color){
        this.color=color;
    }
    
    
    public boolean isValid(Board board, int startRow, int startCol, int endRow, int endCol){
        if(endRow==startRow&&endCol==startCol){
            return false;//the piece is not able to move as the square that is being selected is the same square which the piece is on
        }
        else if(endRow<0||endRow>7||startRow<0||startRow>7||endCol<0||endCol>7||startCol<0||startCol>7){
            return false;//the piece can't move since the selected square is not on the board
        }
        
        
        else if(board.returnvalue(endRow, endCol)<0&&board.returnvalue(startRow,startCol)<0||board.returnvalue(endRow, endCol)>0&&board.returnvalue(startRow,startCol)>0){
            return false;
        }
        else{
           return true;
        }
    }    
            
    public boolean passesbishopRule(Board board, int startRow, int startCol, int endRow, int endCol){

        if(startRow-endRow==startCol-endCol){
            System.out.println("checking if it is legal to move the bishop to the top left");

            for(int i=1;i< startRow-endRow;i++){
                if(board.isOccupied(startRow - i, startCol - i)==true){
                    return false; //checking if it is legal to move the bishop to the top left
                }
            }
            return true;
        }
        
        else if(startRow-endRow==endCol-startCol){
            System.out.println("checking if it is legal to move the piece to the top right");

            for(int i=1;i<startRow-endRow;i++){
                if(board.isOccupied(startRow-i,startCol+i)==true){
                    return false; //checking if it is legal to move the piece to the top right
                }
            }
            return true;
        }
        
        else if(endRow-startRow==startCol-endCol){
            System.out.println("checking if it is legal to move the piece to the bottom left");

            for(int i=1;i<startCol-endCol;i++){
                if(board.isOccupied(startRow-i,startCol+i)==true){
                    return false; //checking if it is legal to move the piece to the bottom left
                }
            }
            return true;
        }
        
        else if(endRow-startRow==endCol-startCol){
            System.out.println("checking if it is legal to move the piece to the bottom right");

            for(int i=1;i<endCol-startCol;i++){
                if(board.isOccupied(startRow+i,startCol+i)==true){
                    return false; //checking if it is legal to move the piece to the bottom right
                }
            }
            return true;
        }
        
        else{
            return false;
        }
        
    }
    
    
    public boolean passesrookRule(Board board, int startRow, int startCol, int endRow, int endCol){
        
        if(startRow>endRow&&startCol==endCol){
            for(int i=startRow-1;i>endRow;i--){
                if(board.isOccupied(i, startCol)==true){
                    return false; //runs through and makes sure the move is legal if the rook is moving down
                }
            }
            return true;
        }
        
        else if(startRow<endRow&&startCol==endCol){
            for(int i=endRow-1;i>startRow;i--){
                if(board.isOccupied(i, startCol)==true){
                    return false; //runs through and makes sure the move is legal if the rook is moving up
                }
            }
            return true;
        }
        
        else if(startRow==endRow&&startCol>endCol){
            for(int i=startCol-1;i>endCol;i--){
                if(board.isOccupied(startRow,i)==true){
                    return false; //runs through and makes sure the move is legal if the rook is moving left
                }
            }
            return true;
        }
        
        else if(startRow==endRow&&startCol<endCol){
            for(int i=endCol-1;i>startCol;i--){
                if(board.isOccupied(startRow,i)==true){
                    return false; //runs through and makes sure the move is legal if the rook is moving right
                }
            }
            return true;
        }
        else{
            return false;
        }
        
    }
    
    public Board move(Board board, int startRow, int startCol, int endRow, int endCol){
        int tempvalstart=0;
        int tempvalend=0;
        Spot tempspot=new Spot(startRow,startCol,board.returnvalue(startRow,startCol));
        if(isValid(board,startRow,startCol,endRow,endCol)==true&&tempspot.returnPiece().getColor()==board.whocanMove()){
            
            // store previous values to restore if the king is in check
            tempvalstart=board.returnvalue(startRow,startCol);
            tempvalend=board.returnvalue(endRow,endCol);
            // move
            board.setvalue(startRow,startCol,0);
            board.setvalue(endRow,endCol,tempvalstart);

            // Check if the King is in check
            if(board.whocanMove()=="black"){
                Spot spot=board.findPiece(-100);
                King king=(King)spot.returnPiece();
                if(king.isinCheck(board,"black",spot.getRow(),spot.getCol())){
                    board.setvalue(startRow,startCol,tempvalstart);
                    board.setvalue(endRow,endCol,tempvalend);
                    // restore value
                    
                    throw new IllegalStateException("Illegal move");
                }
            }

            else if(board.whocanMove()=="white"){
                Spot spot=board.findPiece(100);
                King king=(King)spot.returnPiece();
                if(king.isinCheck(board,"white",spot.getRow(),spot.getCol())){
                    board.setvalue(startRow,startCol,tempvalstart);
                    board.setvalue(endRow,endCol,tempvalend);                    
                    // restore value
                    
                    throw new IllegalStateException("Illegal move");
                }

            }
        }
        else{
            throw new IllegalStateException("Illegal move");
        }
        
        board.addtowhomoved();

        return board;
    }        
}
