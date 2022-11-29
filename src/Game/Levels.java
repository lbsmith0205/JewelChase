package Game;

import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import static javafx.application.Application.launch;

public class Levels extends Application {
    private ArrayList<Character> characters = new ArrayList<Character>();
    //Arraylist of characters
    private ArrayList<Item>Items = new ArrayList<Item>();
    //Arraylist of items in level
    private Tile [] tiles;// Array of tile objects to show board
    public Levels(){}

    public static void main(String[] args){
        launch(args);

    }

    public void start(Stage primaryStage) throws Exception{

        Group root = new Group();
        Scene scene = new Scene(root, 1080,720);
        Stage stage = new Stage();
        stage.setTitle("Drawing Tests");

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


        stage.setScene(scene);
        stage.show();
    }



}
