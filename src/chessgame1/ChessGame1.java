/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author paari
 */
import com.sun.prism.paint.Color;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Light.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseDragEvent;
import static javafx.scene.input.MouseDragEvent.MOUSE_DRAG_ENTERED;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.BROWN;
import static javafx.scene.paint.Color.CHOCOLATE;
import static javafx.scene.paint.Color.KHAKI;
import static javafx.scene.paint.Color.TAN;
import static javafx.scene.paint.Color.WHITE;
import static javafx.scene.paint.Color.web;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.StringConverter;


/**
 *
 * @author gopapaa17
 */

public class ChessGame1 extends Application {
    
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    int startCol,startRow,endCol,endRow;
    Board board=new Board();
    Stage newstage;
    @Override
    public void start(Stage primaryStage) {
        newstage=primaryStage;
        createBoard(primaryStage);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
        EventHandler<MouseEvent> circleOnMousePressedEventHandler = 
        new EventHandler<MouseEvent>() {
 
        @Override
        public void handle(MouseEvent t) {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            orgTranslateX = ((ImageView)(t.getSource())).getTranslateX();
            orgTranslateY = ((ImageView)(t.getSource())).getTranslateY();
            startCol=(int)t.getSceneX()/100;
            startRow=(int)t.getSceneY()/100;
        }
    };
     
    EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = 
        new EventHandler<MouseEvent>() {    
        @Override
        public void handle(MouseEvent t) {
            Label l=new Label(t.getSource().toString());            
            double offsetX = t.getSceneX()- orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;    
            
            endCol=(int)t.getSceneX()/100;
            endRow=(int)t.getSceneY()/100;

            System.out.println("startCol=" + startCol);
            System.out.println("startRow=" + startRow);
            System.out.println("endCol=" + endCol);
            System.out.println("endRow=" + endRow);
            
            Spot newSpot=new Spot(startRow, startCol,board.returnvalue(startRow, startCol));
            
            Piece movingpiece=newSpot.returnPiece();
            movingpiece.printPiece();

            System.out.println("before move");
            board.printboard();

            boolean isIllegalMove = false;
            
            try
            {
                board = movingpiece.move(board, startRow, startCol, endRow, endCol);
            }
            catch(IllegalStateException e)
            {
                System.out.println("illegal move");
                ((ImageView)(t.getSource())).setTranslateX(orgTranslateX);
                ((ImageView)(t.getSource())).setTranslateY(orgTranslateY);
                isIllegalMove = true;
            }

            if(!isIllegalMove)
            {
                createBoard(newstage);
                
                /*
                ((ImageView)(t.getSource())).setTranslateX(newTranslateX);
                ((ImageView)(t.getSource())).setTranslateY(newTranslateY);            
                
                
                
                GridPane.setRowIndex(l,endCol);
                GridPane.setColumnIndex(l,endRow); 
                        */
            }
            
            System.out.println("after move");
            board.printboard();
            
        }
    };
    
    public void createBoard(Stage primaryStage){

        
        GridPane root = new GridPane();
        
        GridPane.setConstraints(root, 10, 10);        
        int initialx=100;
        int initialy=100;
        double xvalue=100;
        double yvalue=100;
        int placeholder=65;

        for(int i=0;i<8;i++){
            initialx=100;
            for(int j=0;j<8;j++){
                Rectangle square=new Rectangle(initialx,initialy,100,100);
                square.setStroke(BLACK);
                if((i%2==0&&j%2==1)||(i%2==1&&j%2==0)){
                    square.setFill(KHAKI);
                }
                else{
                    square.setFill(CHOCOLATE);
                }
                initialx+=100;
                root.add(square,j,i);
            }
            initialy+=100;

        }
            
        
            for(int h=0;h<8;h++){
            char charholder=(char)placeholder;
            String stringholder=String.valueOf(charholder);
            Text xposition=new Text(xvalue,850,stringholder);
            xposition.setTextAlignment(TextAlignment.CENTER);
            xposition.setFont(new Font(50));
            root.add(xposition,h,8);
            placeholder+=1;
            xvalue+=100;
            
        }
            
            for(int k=0;k<8;k++){
            Text yposition=new Text(xvalue,850,String.valueOf(k+1));
            yposition.setTextAlignment(TextAlignment.CENTER);
            yposition.setFont(new Font(50));
            root.add(yposition,8,k);
            placeholder+=1;
            xvalue+=100;
            
        }
        
        Image whitepawn=new Image("whitepawn.png");
        Image blackpawn=new Image("blackpawn.png");
        Image whiteknight=new Image("whiteknight.png");
        Image blackknight=new Image("blackknight.png");
        Image whitebishop=new Image("whitebishop.png");
        Image blackbishop=new Image("blackbishop.png");
        Image whiterook=new Image("whiterook.png");
        Image blackrook=new Image("blackrook.png");
        Image whitequeen=new Image("whitequeen.png");
        Image blackqueen=new Image("blackqueen.png");
        Image whiteking=new Image("whiteking.png");
        Image blackking=new Image("blackking.png");       
        
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(board.returnvalue(j,i)==1){
                    ImageView whitepawn1 = new ImageView();
                    whitepawn1.setImage(whitepawn);
                    whitepawn1.setFitHeight(100);
                    whitepawn1.setFitWidth(100);

                    whitepawn1.setCursor(Cursor.HAND);
                    whitepawn1.setOnMousePressed(circleOnMousePressedEventHandler);
                    whitepawn1.setOnMouseReleased(circleOnMouseDraggedEventHandler);        

                    root.add(whitepawn1,i,j);            
                }
                else if(board.returnvalue(j,i)==-1){
                    ImageView blackpawn1 = new ImageView();
                    blackpawn1.setImage(blackpawn);
                    blackpawn1.setFitHeight(100);
                    blackpawn1.setFitWidth(100);

                    blackpawn1.setCursor(Cursor.HAND);
                    blackpawn1.setOnMousePressed(circleOnMousePressedEventHandler);
                    blackpawn1.setOnMouseReleased(circleOnMouseDraggedEventHandler);        

                    root.add(blackpawn1,i,j);            
                }
                if(board.returnvalue(j,i)==2){
                    ImageView whiteknight1 = new ImageView();
                    whiteknight1.setImage(whiteknight);
                    whiteknight1.setFitHeight(100);
                    whiteknight1.setFitWidth(100);

                    whiteknight1.setCursor(Cursor.HAND);
                    whiteknight1.setOnMousePressed(circleOnMousePressedEventHandler);
                    whiteknight1.setOnMouseReleased(circleOnMouseDraggedEventHandler);        

                    root.add(whiteknight1,i,j);            
                }
                if(board.returnvalue(j,i)==-2){
                    ImageView blackknight1 = new ImageView();
                    blackknight1.setImage(blackknight);
                    blackknight1.setFitHeight(100);
                    blackknight1.setFitWidth(100);

                    blackknight1.setCursor(Cursor.HAND);
                    blackknight1.setOnMousePressed(circleOnMousePressedEventHandler);
                    blackknight1.setOnMouseReleased(circleOnMouseDraggedEventHandler);        

                    root.add(blackknight1,i,j);            
                }
                if(board.returnvalue(j,i)==3){
                    ImageView whitebishop1 = new ImageView();
                    whitebishop1.setImage(whitebishop);
                    whitebishop1.setFitHeight(100);
                    whitebishop1.setFitWidth(100);

                    whitebishop1.setCursor(Cursor.HAND);
                    whitebishop1.setOnMousePressed(circleOnMousePressedEventHandler);
                    whitebishop1.setOnMouseReleased(circleOnMouseDraggedEventHandler);        

                    root.add(whitebishop1,i,j);            
                }
                if(board.returnvalue(j,i)==-3){
                    ImageView blackbishop1 = new ImageView();
                    blackbishop1.setImage(blackbishop);
                    blackbishop1.setFitHeight(100);
                    blackbishop1.setFitWidth(100);

                    blackbishop1.setCursor(Cursor.HAND);
                    blackbishop1.setOnMousePressed(circleOnMousePressedEventHandler);
                    blackbishop1.setOnMouseReleased(circleOnMouseDraggedEventHandler);        

                    root.add(blackbishop1,i,j);            
                }
                if(board.returnvalue(j,i)==5){
                    ImageView whiterook1 = new ImageView();
                    whiterook1.setImage(whiterook);
                    whiterook1.setFitHeight(100);
                    whiterook1.setFitWidth(100);

                    whiterook1.setCursor(Cursor.HAND);
                    whiterook1.setOnMousePressed(circleOnMousePressedEventHandler);
                    whiterook1.setOnMouseReleased(circleOnMouseDraggedEventHandler);        

                    root.add(whiterook1,i,j);            
                }
                if(board.returnvalue(j,i)==-5){
                    ImageView blackrook1 = new ImageView();
                    blackrook1.setImage(blackrook);
                    blackrook1.setFitHeight(100);
                    blackrook1.setFitWidth(100);

                    blackrook1.setCursor(Cursor.HAND);
                    blackrook1.setOnMousePressed(circleOnMousePressedEventHandler);
                    blackrook1.setOnMouseReleased(circleOnMouseDraggedEventHandler);        

                    root.add(blackrook1,i,j);            
                }
                if(board.returnvalue(j,i)==9){
                    ImageView whitequeen1 = new ImageView();
                    whitequeen1.setImage(whitequeen);
                    whitequeen1.setFitHeight(100);
                    whitequeen1.setFitWidth(100);

                    whitequeen1.setCursor(Cursor.HAND);
                    whitequeen1.setOnMousePressed(circleOnMousePressedEventHandler);
                    whitequeen1.setOnMouseReleased(circleOnMouseDraggedEventHandler);        

                    root.add(whitequeen1,i,j);            
                }                
                if(board.returnvalue(j,i)==-9){
                    ImageView blackqueen1 = new ImageView();
                    blackqueen1.setImage(blackqueen);
                    blackqueen1.setFitHeight(100);
                    blackqueen1.setFitWidth(100);

                    blackqueen1.setCursor(Cursor.HAND);
                    blackqueen1.setOnMousePressed(circleOnMousePressedEventHandler);
                    blackqueen1.setOnMouseReleased(circleOnMouseDraggedEventHandler);        

                    root.add(blackqueen1,i,j);            
                }
                if(board.returnvalue(j,i)==100){
                    ImageView whiteking1 = new ImageView();
                    whiteking1.setImage(whiteking);
                    whiteking1.setFitHeight(100);
                    whiteking1.setFitWidth(100);

                    whiteking1.setCursor(Cursor.HAND);
                    whiteking1.setOnMousePressed(circleOnMousePressedEventHandler);
                    whiteking1.setOnMouseReleased(circleOnMouseDraggedEventHandler);        

                    root.add(whiteking1,i,j);            
                }
                if(board.returnvalue(j,i)==-100){
                    ImageView blackking1 = new ImageView();
                    blackking1.setImage(blackking);
                    blackking1.setFitHeight(100);
                    blackking1.setFitWidth(100);

                    blackking1.setCursor(Cursor.HAND);
                    blackking1.setOnMousePressed(circleOnMousePressedEventHandler);
                    blackking1.setOnMouseReleased(circleOnMouseDraggedEventHandler);        

                    root.add(blackking1,i,j);            
                }                 
            }
        }
        primaryStage.setResizable(false);
 
        Scene scene = new Scene(root, 900, 900);
        
        primaryStage.setTitle("Nate and Paari's Chess Hype!");
        primaryStage.setScene(scene);
        primaryStage.show();        
    }
    
}
