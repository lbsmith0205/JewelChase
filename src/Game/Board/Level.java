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
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Level extends Application {
    private ArrayList<Character> characters = new ArrayList<Character>();
    //Arraylist of characters
    private ArrayList<Item>Items = new ArrayList<Item>();
    //Arraylist of items in level
    private Tile [][]tiles = new Tile [15][10]; // Array of tile objects to show board
    public Color[][] tileColChar = new Color[150][4];
    public Color[] finalColours = new Color[600];
    public ArrayList<String> stringColour = new ArrayList<>();
    public ArrayList<Character> charColour = new ArrayList<>();


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



    public void background () {
        try {
            File testFile = new File("src/Levels/Level1.txt");
            Scanner in = new Scanner(testFile);
            int windowResX = in.nextInt() * 64;
            int windowResY = in.nextInt() * 64;
            in.nextLine();
            for (int j=0; j < windowResX*windowResY/(64*64); j++) {
                stringColour.add(in.next());
                for (int i = 0; i < 4; i++) {
                    switch (stringColour.get(j).charAt(i)) {
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
                    }
                    for (int x = 0; x < 15; x++){
                        for (int y = 0; y < 10; y++){
                            Tile tile = new Tile(x*64,y*64,tileColChar);
                            tiles[x][y] = tile;
                        }
                    }
               }
            }
            in.next();
            in.close();
            Group root = new Group();
            Scene scene = new Scene(root, windowResX,windowResY);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Drawing Tests");
            for (int x = 0; x < 15; x++) {
                for (int y = 0; y < 10; y++) {
                    Tile tile = tiles[x][y];
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

 */

    public void gutBackground () {
        try {
            File testFile = new File("src/Levels/Level2.txt");
            Scanner in = new Scanner(testFile);
            int windowResX = in.nextInt() * 64;
            int windowResY = in.nextInt() * 64;
            in.nextLine();
            for (int j=0; j < windowResX*windowResY/(64*64); j++) {
                stringColour.add(in.next());
            }in.close();

            for (int x = 0; x < 150; x++) {
                for (int y = 0; y < 4; y++) {
                    if (stringColour.get(x).charAt(y) == 'R'){
                        tileColChar[x][y] = Color.INDIANRED;
                    }else if (stringColour.get(x).charAt(y) == 'B'){
                        tileColChar[x][y] = Color.DEEPSKYBLUE;
                    }else if (stringColour.get(x).charAt(y) == 'Y'){
                        tileColChar[x][y] = Color.KHAKI;
                    }else if (stringColour.get(x).charAt(y) == 'C'){
                        tileColChar[x][y] = Color.LIGHTCYAN;
                    }else if (stringColour.get(x).charAt(y) == 'G'){
                        tileColChar[x][y] = Color.SPRINGGREEN;
                    }
                }
            }int k = 0;
            while (k < 600) {
                for (int x = 0; x < 150; x++) {
                    for (int y = 0; y < 4; y++) {
                        finalColours[k] = tileColChar[x][y];
                        k++;
                    }
                }
            }
            Group root = new Group();
            Scene scene = new Scene(root, windowResX,windowResY);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Drawing Tests");
int j = 0;
            while (j < 600) {
                for (int y = 0; y < 10; y++) {
                    for (int x = 0; x < 15; x++) {
                        for (int i = 0; i < 4; i++) {
                            Rectangle t = new Rectangle();
                            t.setX(x * 64 + offsetsX[i]);
                            t.setY(y * 64 + offsetY[i]);
                            t.setWidth(WIDTH_HEIGHT);
                            t.setHeight(WIDTH_HEIGHT);
                            t.setFill(finalColours[i + j]);
                            t.setStroke(Color.DARKSLATEGRAY);
                            root.getChildren().add(t);
                        }j += 4;
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
        gutBackground();

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
