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
import javafx.scene.shape.Line;
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


    private final int WIDTH_HEIGHT= 32, OFFSET_VALUE = WIDTH_HEIGHT;
    public final int GRID_OFFSET= 64;

    private int offsetsX[] = {0,OFFSET_VALUE,0,OFFSET_VALUE};
    private int offsetY[] = {0,0,OFFSET_VALUE,OFFSET_VALUE};
    private final int FIND_CENTRE= 15;

    public void gutBackground () {
        try {
            File testFile = new File("src/Levels/Level1.txt");
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
                        tileColChar[x][y] = Color.CYAN;
                    }else if (stringColour.get(x).charAt(y) == 'G'){
                        tileColChar[x][y] = Color.SPRINGGREEN;
                    }else if (stringColour.get(x).charAt(y) == 'M'){
                    tileColChar[x][y] = Color.MAGENTA;
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
            Line line = new Line();
            line.setStartX(0);
            line.setStartY(64);
            line.setEndX(960);
            line.setEndY(64);
            line.setStrokeWidth(3);

            root.getChildren().add(line);
            Line line1 = new Line();
            line1.setStartX(0);
            line1.setStartY(64);
            line1.setEndX(960);
            line1.setEndY(64);
            line1.setStrokeWidth(3);
            root.getChildren().add(line);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }


    public Level(){
    }

    //public Color[] getTileColors(){return tileColors;}

    public int [] getOffsetsX(){return offsetsX;}

    public int[] getOffsetY() {return offsetY;}



    public static void main(String[] args){
        launch(args);

    }

    public void start(Stage primaryStage) throws Exception{
        getOffsetsX();
        getOffsetY();
        gutBackground();



}

    }
