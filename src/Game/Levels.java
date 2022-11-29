package Game;

import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Group;
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
        Scene scene = new Scene(root, 600,600);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }



}
