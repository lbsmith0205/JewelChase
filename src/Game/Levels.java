package Game;

import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Levels extends Application {
    private ArrayList<Character> characters = new ArrayList<Character>();
    //Arraylist of characters
    private ArrayList<Item>Items = new ArrayList<Item>();
    //Arraylist of items in level
    private Tile [] tiles;// Array of tile objects to show board
    private Color[] tileColors = {Color.INDIANRED,Color.
            SPRINGGREEN,Color.DEEPSKYBLUE,Color.KHAKI};
    private int offsetsX[] = {0,30,0,30};
    private int offsetY[] = {0,0,30,30};

    public Levels(){
    }

    public Color[] getTileColors(){return tileColors;}

    public int [] getOffsetsX(){return offsetsX;}

    public int[] getOffsetY() {return offsetY;}



    public static void main(String[] args){
        launch(args);

    }

    public void start(Stage primaryStage) throws Exception{

        getTileColors();
        getOffsetsX();
        getOffsetY();


        Group root = new Group();
        Scene scene = new Scene(root, 1080,720);
        Stage stage = new Stage();
        stage.setTitle("Drawing Tests");

        Tile tile = new Tile(540,330,tileColors);
        for (int i = 0; i < 4; i++) {
            Rectangle t = new Rectangle();
            t.setX(tile.getXPosition() + offsetsX[i]);
            t.setY(tile.getYPosition() + offsetY[i]);
            t.setWidth(30);
            t.setHeight(30);
            t.setFill(tileColors[i]);
            root.getChildren().add(t);
        }

        /*
        Rectangle tileVer1 = new Rectangle();
        tileVer1.setX(tile.getXPosition());
        tileVer1.setY(tile.getYPosition());
        tileVer1.setWidth(30);
        tileVer1.setHeight(30);
        tileVer1.setFill(tileColors[0]);

        Rectangle tileVer2 = new Rectangle();
        tileVer2.setX(tile.getXPosition()+30);
        tileVer2.setY(tile.getYPosition());
        tileVer2.setWidth(30);
        tileVer2.setHeight(30);
        tileVer2.setFill(tileColors[1]);

        Rectangle tileVer3 = new Rectangle();
        tileVer3.setX(tile.getXPosition());
        tileVer3.setY(tile.getYPosition()+30);
        tileVer3.setWidth(30);
        tileVer3.setHeight(30);
        tileVer3.setFill(tileColors[2]);

        Rectangle tileVer4 = new Rectangle();
        tileVer4.setX(tile.getXPosition()+30);
        tileVer4.setY(tile.getYPosition()+30);
        tileVer4.setWidth(30);
        tileVer4.setHeight(30);
        tileVer4.setFill(tileColors[3]);

        root.getChildren().add(tileVer1);
        root.getChildren().add(tileVer2);
        root.getChildren().add(tileVer3);
        root.getChildren().add(tileVer4);

        Rectangle tileTest = new Rectangle();
        tileTest.setX(505);
        tileTest.setY(330);
        tileTest.setWidth(30);
        tileTest.setHeight(30);
        tileTest.setFill(Color.INDIANRED);

        Rectangle tileTest2 = new Rectangle();
        tileTest2.setX(540);
        tileTest2.setY(330);
        tileTest2.setWidth(30);
        tileTest2.setHeight(30);
        tileTest2.setFill(Color.SPRINGGREEN);

        Rectangle tileTest3 = new Rectangle();
        tileTest3.setX(505);
        tileTest3.setY(365);
        tileTest3.setWidth(30);
        tileTest3.setHeight(30);
        tileTest3.setFill(Color.DEEPSKYBLUE);

        Rectangle tileTest4 = new Rectangle();
        tileTest4.setX(540);
        tileTest4.setY(365);
        tileTest4.setWidth(30);
        tileTest4.setHeight(30);
        tileTest4.setFill(Color.KHAKI);

        root.getChildren().add(tileTest);
        root.getChildren().add(tileTest2);
        root.getChildren().add(tileTest3);
        root.getChildren().add(tileTest4);
*/
        stage.setScene(scene);
        stage.show();



}

    }
