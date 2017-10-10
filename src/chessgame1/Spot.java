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
public class Spot {
    int row;
    int col;
    Piece piece;
    
    public Spot(int row, int col,int piecevalue){
        this.row=row;
        this.col=col;
        this.piece=null;
        piecefinder(piecevalue);
    }

    private void piecefinder(int piecevalue){
        int PAWN=1;
        int KNIGHT=2;
        int BISHOP=3;
        int ROOK=5;
        int QUEEN=9;
        int KING=100;        
        String color = null;

        if(piecevalue<0){
            color = "black";
        }

        if(piecevalue>0){
            color = "white";
        }              

        if(piecevalue!=0){
            if(Math.abs(piecevalue)==PAWN){
                this.piece=new Pawn(true,row,col,color);
            }
            if(Math.abs(piecevalue)==KNIGHT){
                this.piece=new Knight(true,row,col,color);
            }
            if(Math.abs(piecevalue)==BISHOP){
                this.piece=new Bishop(true,row,col,color);
            }
            if(Math.abs(piecevalue)==ROOK){
                this.piece=new Rook(true,row,col,color);
            }
            if(Math.abs(piecevalue)==QUEEN){
                this.piece=new Queen(true,row,col,color);
            }
            if(Math.abs(piecevalue)==KING){
                this.piece=new King(true,row,col,color);
            }
        }
    }
        
    public int getRow(){
        return this.row;
    }
    
    public int getCol(){
        return this.col;
    }
      
    public Piece returnPiece(){
        return this.piece;
    }
}

