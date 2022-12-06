package Game.Board;

import java.util.ArrayList;

import Game.Items.Item;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Level extends Application {
    private ArrayList<Character> characters = new ArrayList<Character>();
    //Arraylist of characters
    private ArrayList<Item>Items = new ArrayList<Item>();
    //Arraylist of items in level
    private Tile [] tiles;// Array of tile objects to show board
    private Color[] tileColors = {Color.INDIANRED,Color.
            SPRINGGREEN,Color.DEEPSKYBLUE,Color.KHAKI};

    private final int WIDTH_HEIGHT= 30, OFFSET_VALUE = WIDTH_HEIGHT;

    private int offsetsX[] = {0,OFFSET_VALUE,0,OFFSET_VALUE};
    private int offsetY[] = {0,0,OFFSET_VALUE,OFFSET_VALUE};
    private final int FIND_CENTRE= 15;


    public Level(){
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
            t.setWidth(WIDTH_HEIGHT);
            t.setHeight(WIDTH_HEIGHT);
            t.setFill(tileColors[i]);
            root.getChildren().add(t);
        }
        Rectangle p = new Rectangle();
        p.setX(tile.getXPosition()+FIND_CENTRE);
        p.setY(tile.getYPosition()+FIND_CENTRE);
        p.setWidth(WIDTH_HEIGHT);
        p.setHeight(WIDTH_HEIGHT);
        root.getChildren().add(p);

        stage.setScene(scene);
        stage.show();



}

    }
