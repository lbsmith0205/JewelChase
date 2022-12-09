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

    public final int GRID_REGULATOR = 64;
    private final int WIDTH_HEIGHT= 32, OFFSET_VALUE = WIDTH_HEIGHT;

    private final int OFFSETS_X[] = {0,OFFSET_VALUE,0,OFFSET_VALUE};
    private final int OFFSETS_Y[] = {0,0,OFFSET_VALUE,OFFSET_VALUE};

    int colourLooper = 0;

    public void gutBackground () {
        try {
            File testFile = new File("src/Levels/Level1.txt");
            Scanner in = new Scanner(testFile);
            int windowResX = in.nextInt() * GRID_REGULATOR;
            int windowResY = in.nextInt() * GRID_REGULATOR;
            in.nextLine();
            for (int j=0; j < windowResX*windowResY/(GRID_REGULATOR*GRID_REGULATOR); j++) {
                stringColour.add(in.next());
            }in.close();

            for (int x = 0; x < 150; x++) {
                for (int y = 0; y < 4; y++) {
                    if (stringColour.get(x).charAt(y) == 'R'){
                        tileColChar[x][y] = Color.INDIANRED;
                    }else if (stringColour.get(x).charAt(y) == 'B'){
                        tileColChar[x][y] = Color.ROYALBLUE;
                    }else if (stringColour.get(x).charAt(y) == 'Y'){
                        tileColChar[x][y] = Color.KHAKI;
                    }else if (stringColour.get(x).charAt(y) == 'C'){
                        tileColChar[x][y] = Color.CYAN;
                    }else if (stringColour.get(x).charAt(y) == 'G'){
                        tileColChar[x][y] = Color.SPRINGGREEN;
                    }else if (stringColour.get(x).charAt(y) == 'M'){
                    tileColChar[x][y] = Color.MEDIUMPURPLE;
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
            while (colourLooper < 600) {
                for (int y = 0; y < 10; y++) {
                    for (int x = 0; x < 15; x++) {
                        for (int i = 0; i < 4; i++) {
                            Rectangle t = new Rectangle();
                            t.setX(x * GRID_REGULATOR + OFFSETS_X[i]);
                            t.setY(y *GRID_REGULATOR + OFFSETS_Y[i]);
                            t.setWidth(WIDTH_HEIGHT);
                            t.setHeight(WIDTH_HEIGHT);
                            t.setFill(finalColours[i + colourLooper]);
                            root.getChildren().add(t);
                        }colourLooper += 4;
                    }
                }
            }
            for(int x = 0;x < 10; x++) {
                Line lineHor = new Line();
                lineHor.setStartX(0);
                lineHor.setStartY(GRID_REGULATOR * x);
                lineHor.setEndX(960);
                lineHor.setEndY(GRID_REGULATOR * x);
                lineHor.setStrokeWidth(3);
                lineHor.setStroke(Color.NAVY);
                root.getChildren().add(lineHor);
            }
            for(int x = 0;x < 15; x++) {
                Line lineVer = new Line();
                lineVer.setStartX(GRID_REGULATOR * x);
                lineVer.setStartY(0);
                lineVer.setEndX(GRID_REGULATOR * x);
                lineVer.setEndY(640);
                lineVer.setStrokeWidth(3);
                lineVer.setStroke(Color.NAVY);
                root.getChildren().add(lineVer);
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }


    public Level(){
    }

    //public Color[] getTileColors(){return tileColors;}

    public int [] getOffsetsX(){return OFFSETS_X;}
    public int[] getOffsetY() {return OFFSETS_Y;}



    public static void main(String[] args){
        launch(args);

    }

    public void start(Stage primaryStage) throws Exception{
        getOffsetsX();
        getOffsetY();
        gutBackground();



    }

}
