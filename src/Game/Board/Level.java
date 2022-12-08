package Game.Board;

import Game.Items.Item;

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
    private static final int WIDTH_HEIGHT = 32, OFFSET_VALUE = WIDTH_HEIGHT;

    //Arraylist of items in level
    private ArrayList<Item> Items = new ArrayList<Item>();
    // Array of tile objects to show board
    private Color[][] tileColChar = new Color[150][4];
    private Color[] finalColours = new Color[600];
    private ArrayList<String> stringColour = new ArrayList<>();

    private Board board;
    private int time;
    private int score;

    private final int GRID_OFFSET = 64;
    private final String levelFilePath;

    private int offsetsX[] = {0, OFFSET_VALUE, 0, OFFSET_VALUE};
    private int offsetY[] = {0, 0, OFFSET_VALUE, OFFSET_VALUE};


    public Level(String fileName) {
        this.levelFilePath = "src/Levels/" + fileName + ".txt";
        this.readLineByLine(fileReader(levelFilePath));
    }

    private Scanner fileReader(String filename) {
        File file = new File(filename);

        Scanner in = null;

        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Could not find: " + file);
            System.exit(0);
        }

        return in;
    }

    private void readLineByLine(Scanner in) {
        String levelInfo = in.nextLine();

        Scanner readLevelInfo = new Scanner(levelInfo);
        int width = readLevelInfo.nextInt();
        int height = readLevelInfo.nextInt();

        this.time = readLevelInfo.nextInt();
        this.score = readLevelInfo.nextInt();

        Tile[][] temp2DArray = new Tile[width][height];

        for (int y = 0; y < height; y++) {
            if (in.hasNextLine()) {
                String tileRow = in.nextLine();
                Scanner row = new Scanner(tileRow);

                for (int x = 0; x < width; x++) {
                    if (row.hasNext()) {
                        String t = row.next();
                        Tile temp = readTiles(t, x, y);
                        temp2DArray[x][y] = temp;
                    }
                }
            }
        }
        this.board = new Board(width, height, temp2DArray);

        String items = in.nextLine();
        Scanner itemRow = new Scanner(items);
        itemRow.useDelimiter(",");
        //create Item here

        String characters = in.nextLine();
        Scanner characterRow = new Scanner(characters);
        characterRow.useDelimiter(",");
        //create Character here
    }

    private Tile readTiles(String tile, int x, int y) {
        Color[] colorToAdd = new Color[4];

        for (int i = 0; i < colorToAdd.length; i++) {
            char color = tile.charAt(i);
            colorToAdd[i] = charToColor(color);
        }

        Tile tileMade = new Tile(x, y, colorToAdd);
        return tileMade;
    }

    private Color charToColor(char c) {
        switch (c) {
            case 'R' -> {
                return Color.INDIANRED;
            }
            case 'B' -> {
                return Color.DEEPSKYBLUE;
            }
            case 'G' -> {
                return Color.SPRINGGREEN;
            }
            case 'Y' -> {
                return Color.KHAKI;
            }
            case 'C' -> {
                return Color.CYAN;
            }
            case 'M' -> {
                return Color.MAGENTA;
            }
            default -> {
                return null;
            }
        }
    }

    private Item stringToItem(String i) {
        //Have fun implementing this Dan
    }

    private Character stringToCharacter(String c) {
        //Have fun implementing this too Dan
    }

    public void background() {
        try {
            File testFile = new File("src/Levels/Level4.txt");
            Scanner in = new Scanner(testFile);
            int windowResX = in.nextInt() * 64;
            int windowResY = in.nextInt() * 64;
            in.nextLine();
            for (int j = 0; j < windowResX * windowResY / (64 * 64); j++) {
                stringColour.add(in.next());
            }
            in.close();

            for (int x = 0; x < 150; x++) {
                for (int y = 0; y < 4; y++) {
                    if (stringColour.get(x).charAt(y) == 'R') {
                        tileColChar[x][y] = Color.INDIANRED;
                    } else if (stringColour.get(x).charAt(y) == 'B') {
                        tileColChar[x][y] = Color.DEEPSKYBLUE;
                    } else if (stringColour.get(x).charAt(y) == 'Y') {
                        tileColChar[x][y] = Color.KHAKI;
                    } else if (stringColour.get(x).charAt(y) == 'C') {
                        tileColChar[x][y] = Color.CYAN;
                    } else if (stringColour.get(x).charAt(y) == 'G') {
                        tileColChar[x][y] = Color.SPRINGGREEN;
                    } else if (stringColour.get(x).charAt(y) == 'M') {
                        tileColChar[x][y] = Color.MAGENTA;
                    }
                }
            }
            int k = 0;
            while (k < 600) {
                for (int x = 0; x < 150; x++) {
                    for (int y = 0; y < 4; y++) {
                        finalColours[k] = tileColChar[x][y];
                        k++;
                    }
                }
            }
            Group root = new Group();
            Scene scene = new Scene(root, windowResX, windowResY);
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
                        }
                        j += 4;
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
            root.getChildren().add(line1);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }


    //public Color[] getTileColors(){return tileColors;}

    public int[] getOffsetsX() {
        return offsetsX;
    }

    public int[] getOffsetY() {
        return offsetY;
    }


    public static void main(String[] args) {
        launch(args);

    }

    public void start(Stage primaryStage) throws Exception {
        getOffsetsX();
        getOffsetY();
        background();

    }

}
