package Game.Board;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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
    private Tile [][]tiles = new Tile [10][15]; // Array of tile objects to show board
    public Color[] tileColChar = new Color[4];

    private final int WIDTH_HEIGHT= 32, OFFSET_VALUE = WIDTH_HEIGHT;

    private int offsetsX[] = {0,OFFSET_VALUE,0,OFFSET_VALUE};
    private int offsetY[] = {0,0,OFFSET_VALUE,OFFSET_VALUE};
    private final int FIND_CENTRE= 15;
/*
    private static Tile constructTile(){
        Tile tile = new Tile(540,330,tileColors);
    }

    private static Queue<ClosedShape> readLineByLine(Scanner in) {
        Queue<ClosedShape> shapeQueue = new Queue<ClosedShape>();
        while (in.hasNextLine()) {
            String curLine = in.nextLine();
            Scanner line = new Scanner(curLine);
            String type = line.next();
            if (type.equalsIgnoreCase("Circle")) {
                Circle circle = constructCircle(line);
                shapeQueue.enqueue(circle);
                System.out.println(circle.toString());
            } else if (type.equalsIgnoreCase("Oval")) {
                Oval oval = constructOval(line);
                shapeQueue.enqueue(oval);
                System.out.println(oval.toString());
            } else if (type.equalsIgnoreCase("Square")){
                Square square = constructSquare(line);
                shapeQueue.enqueue(square);
                System.out.println(square.toString());
            } else if (type.equalsIgnoreCase("Rect")){
                Rect rect = constructRectangle(line);
                shapeQueue.enqueue(rect);
                System.out.println(rect.toString());
            } else if(type.equalsIgnoreCase("Triangle")){
                Triangle triangle = constructTriangle(line);
                shapeQueue.enqueue(triangle);
                System.out.println(triangle.toString());
            }            line.close();
        }
        //read in the shape files and place them on the Queue

        //Right now, returning an empty Queue.  You need to change this.
        in.close();
        return shapeQueue;
    }

 */

    public void background () {
        try {
            File testFile = new File("src/Levels/Level1.txt");
            Scanner in = new Scanner(testFile);
            int windowResX = in.nextInt() * 64;
            int windowResY = in.nextInt() * 64;
            in.nextLine();
            int j = 0;
            while (in.hasNextLine() && j < windowResX*windowResY/(64*64)) {
                String curLine = in.next();
                Scanner line = new Scanner(curLine);
                String curColour = line.next();
                for (int i = 0; i < 4; i++) {
                    switch (curColour.charAt(i)) {
                        case 'R':
                            tileColChar[i] = Color.INDIANRED;
                            break;
                        case 'B':
                            tileColChar[i] = Color.DEEPSKYBLUE;
                            break;
                        case 'Y':
                            tileColChar[i] = Color.KHAKI;
                            break;
                        case 'C':
                            tileColChar[i] = Color.LIGHTCYAN;
                            break;
                        case 'G':
                            tileColChar[i] = Color.SPRINGGREEN;
                            break;
                    }for (int x = 0; x < 15; x++){
                        for (int y = 0; y < 10; y++){
                            Tile tile = new Tile(x*64,y*64,tileColChar);
                            tiles[y][x] = tile;
                        }
                    }
                }j++;

            }
            in.close();
            System.out.print(Arrays.toString(tiles));
            Group root = new Group();
            Scene scene = new Scene(root, windowResX,windowResY);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Drawing Tests");
            for (int x = 0; x < 15; x++) {
                for (int y = 0; y < 10; y++) {
                    Tile tile = tiles[y][x];
                    for (int i = 0; i < 4; i++) {
                        Rectangle t = new Rectangle();
                        t.setX(tile.getXPosition() + offsetsX[i]);
                        t.setY(tile.getYPosition() + offsetY[i]);
                        t.setWidth(WIDTH_HEIGHT);
                        t.setHeight(WIDTH_HEIGHT);
                        t.setFill(tileColChar[i]);
                        root.getChildren().add(t);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
   }


    public Level(){
      /*  Group root = new Group();
        Scene scene = new Scene(root, 1080,720);
        Stage stage = new Stage();
        stage.setTitle("Drawing Tests");

        stage.setScene(scene);
        stage.show();*/
    }

    //public Color[] getTileColors(){return tileColors;}

    public int [] getOffsetsX(){return offsetsX;}

    public int[] getOffsetY() {return offsetY;}



    public static void main(String[] args){
        launch(args);

    }

    public void start(Stage primaryStage) throws Exception{

       // getTileColors();
        getOffsetsX();
        getOffsetY();
        background ();

/*



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
        stage.show();*/



}

    }
