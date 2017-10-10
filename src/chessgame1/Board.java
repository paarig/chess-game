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
public class Board {
    int whomoved=0;
    public int[][] intboard=new int[8][8];
    public Board(){
        for(int i=0;i<8;i++){ 
            intboard[1][i]=1; //creates all white pawns
    }
        for(int i=0;i<8;i++){
            intboard[6][i]=-1; //creates all black pawns
    }
        for(int i=1;i<8;i+=5){
            intboard[0][i]=2; //creates all white knights
    }
        for(int i=1;i<8;i+=5){
            intboard[7][i]=-2; //creates all black knights
    }
        for(int i=2;i<8;i+=3){
            intboard[0][i]=3; //creates all white bishops
    }
        for(int i=2;i<8;i+=3){
            intboard[7][i]=-3; //creates all black bishops
    }
        for(int i=0;i<8;i+=7){
            intboard[0][i]=5; //creates all white rooks
    }
        for(int i=0;i<8;i+=7){
            intboard[7][i]=-5; //creates all black rooks
    }

            intboard[0][3]=9; //creates white queen

    
            intboard[7][3]=-9; //creates black queen

    
            intboard[0][4]=100; //creates white king

     
            intboard[7][4]=-100; //creates black king
    
    }
    
    public boolean isOccupied(int x, int y){
        if(intboard[x][y]!=0){
            return true;
        }
        else{
            return false;
        }
    }
    
    public int returnvalue(int x, int y){
        return intboard[x][y];
    }
    
    public void setvalue(int x,int y,int piecevalue){
        intboard[x][y]=piecevalue;
    }
    
    public void printboard(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(intboard[i][j]!=0){
                    
                System.out.println(i+" "+j+" "+intboard[i][j]);
                }
            }
        }

    }
    
    public ArrayList<Spot> getallPieces(String color){
        ArrayList<Spot> Spotlist=new ArrayList<Spot>();
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                int piecevalue = intboard[i][j];
                
                // Skip empty case
                if(piecevalue == 0) continue;
                
                Spot currentspot=new Spot(i,j,piecevalue);
                if(currentspot.returnPiece().getColor().equals(color.toLowerCase())){
                    Spotlist.add(currentspot);
                }
            }
        }
        return Spotlist;
    }
    
    public String whocanMove(){
        String color=null;
        if(whomoved%2==1){
            color="black";
        }
        else{
            color="white";
        }
        return color;
    }
    
    public void addtowhomoved(){
        whomoved++;
    }
 
    public Spot findPiece(int Pieceval){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(returnvalue(i,j)==Pieceval){
                    return new Spot(i,j,Pieceval);
                }
            }
        }
        throw new IllegalArgumentException("Piece not found");
    }    
}
