package Game.Board;

import Game.Characters.FloorFollowingThief;
import Game.Characters.FlyingAssassin;
import Game.Characters.Player;
import Game.Characters.SmartThief;
import Game.Direction;
import Game.Items.*;
import Game.Items.Item;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.shape.Rectangle;

public class Level {
    private static final int SQUARE_SIDE = 32, OFFSET_VALUE = SQUARE_SIDE;
    private static final int TILE_SIZE = 64;
    private static final int SQUARES_IN_TILE = 4;

    //Arraylist of items in level
    private ArrayList<Item> Items = new ArrayList<Item>();
    // Array of tile objects to show board
    private Color[][] tileColChar = new Color[150][4];
    private Color[] finalColours = new Color[600];
    private ArrayList<String> stringColour = new ArrayList<>();

    private Canvas tileLayer;
    private Canvas itemCharacterLayer;
    private Board board;
    private int width;
    private int height;
    private int time;
    private int score;
    private int levelNo;

    private final int GRID_OFFSET = 64;
    private final String levelFilePath;

    private final int windowResWidth;
    private final int windowResHeight;

    private int offsetsX[] = {0, OFFSET_VALUE, 0, OFFSET_VALUE};
    private int offsetsY[] = {0, 0, OFFSET_VALUE, OFFSET_VALUE};


    public Level(String fileName) {
        this.levelFilePath = "src/Levels/" + fileName + ".txt";
        this.readLevelFile(levelFilePath);

        windowResWidth = this.board.getWidth() * TILE_SIZE;
        windowResHeight = this.board.getHeight() * TILE_SIZE;
    }

    private Scanner fileReader(String levelFilePath) {
        try {
            File file = new File(levelFilePath);
            return new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file at " + levelFilePath);
            return null;
        }
    }

    /**
     * Converts level file into usable parameters.
     * @param levelFilePath
     */
    private void readLevelFile(String levelFilePath) {
        Scanner fileReader = fileReader(levelFilePath);
        String levelInfo = fileReader.nextLine();
        parseLevelInfo(levelInfo);

        Tile[][] tiles = new Tile[width][height];
        for (int y = 0; y < height; y++) {
            String lineOfTiles = fileReader.nextLine();
            Scanner tileParser = new Scanner(lineOfTiles);
            tileParser.useDelimiter(" ");
            for (int x = 0; x < width; x++) {
                tiles[x][y] = parseTile(x, y, tileParser.next());
            }
            tileParser.close();
        }
        this.board = new Board(width, height, tiles);
        String items = fileReader.nextLine();
        parseItems(items);
        String characters = fileReader.nextLine();
        parseCharacters(characters);
        //board.refreshNavGraph();

        fileReader.close();
    }


    /**
     * Reads the level parameters from the top line of the level file.
     * @param levelInfo
     */
    private void parseLevelInfo(String levelInfo) {
        Scanner paramParser = new Scanner(levelInfo);
        this.width = paramParser.nextInt();
        this.height = paramParser.nextInt();
        this.time = paramParser.nextInt();
        this.score = paramParser.nextInt();
        this.levelNo = paramParser.nextInt();
        paramParser.close();
    }


    /**
     * Converts a single block of colour chars into a Tile object.
     * @param x
     * @param y
     * @param colours
     * @return
     */
    private Tile parseTile(int x, int y, String colours) {
        char[] coloursList = colours.toCharArray();
        Color[] tileColours = new Color[4];
        for (int i = 0; i < 4; i++) {
            switch (coloursList[i]) {
                case 'R' -> tileColours[i] = Color.INDIANRED;
                case 'G' -> tileColours[i] = Color.SPRINGGREEN;
                case 'B' -> tileColours[i] = Color.ROYALBLUE;
                case 'Y' -> tileColours[i] = Color.KHAKI;
                case 'C' -> tileColours[i] = Color.CYAN;
                case 'M' -> tileColours[i] = Color.MEDIUMPURPLE;
            };
        }
        return new Tile(x, y, tileColours);
    }

    /**
     * Parses line of text file containing items and their locations.
     * @param itemLine
     */
    private void parseItems(String itemLine) {
        String[] items = itemLine.split(" ");
        for (String item : items) {
            Scanner parseItem = new Scanner(item);
            parseItem.useDelimiter(",");
            int x = parseItem.nextInt();
            int y = parseItem.nextInt();
            String type = parseItem.next();
            parseItem.close();

            switch (type) {
                case "¢":
                case "$":
                case "Ru":
                case "Di":
                    Loot newLoot = new Loot(board.getTile(x, y), type);
                    board.getTile(x, y).addObjectToTile(newLoot);
                    break;
                case "RGt":
                case "GGt":
                case "BGt":
                    Gate newGate = new Gate(board.getTile(x, y), type);
                    board.getTile(x, y).addObjectToTile(newGate);
                    break;
                case "RL":
                case "GL":
                case "BL":
                    Lever newLever = new Lever(board.getTile(x, y), type);
                    board.getTile(x, y).addObjectToTile(newLever);
                    break;
                case "D":
                    Door newDoor = new Door(board.getTile(x, y));
                    board.getTile(x, y).addObjectToTile(newDoor);
                    break;
                case "Bo":
                    Bomb newBomb = new Bomb(board.getTile(x, y), board);
                    board.getTile(x, y).addObjectToTile(newBomb);
                    break;
                case "Cl":
                    Clock newClock = new Clock(board.getTile(x, y));
                    board.getTile(x, y).addObjectToTile(newClock);
                    break;
            }
        }
    }

    /**
     * Parses line of text file containing characters, their starting locations and starting directions.
     * @param characterLine
     */
    private void parseCharacters(String characterLine) {
        String[] characters = characterLine.split(" ");
        for (String character : characters) {
            Scanner parseItem = new Scanner(character);
            parseItem.useDelimiter(",");
            int x = parseItem.nextInt();
            int y = parseItem.nextInt();
            String type = parseItem.next();
            String directionChar = parseItem.next();
            parseItem.close();

            Direction direction;
            switch (directionChar) {
                case "U" -> direction = Direction.UP;
                case "L" -> direction = Direction.LEFT;
                case "D" -> direction = Direction.DOWN;
                case "R" -> direction = Direction.RIGHT;
                default -> direction = null;
            }

            switch (type) {
                case "P":
                    Player newP = new Player(board.getTile(x, y), direction);
                    board.getTile(x, y).addObjectToTile(newP);
                    break;
                case "FFT":
                    FloorFollowingThief newFFT = new FloorFollowingThief(board.getTile(x, y), direction);
                    board.getTile(x, y).addObjectToTile(newFFT);
                    break;
                case "FA":
                    FlyingAssassin newFA = new FlyingAssassin(board.getTile(x, y), direction);
                    board.getTile(x, y).addObjectToTile(newFA);
                    break;
                case "ST":
                    SmartThief newST = new SmartThief(board.getTile(x, y), direction);
                    board.getTile(x, y).addObjectToTile(newST);
                    break;
            }
        }

    }

    /*public void background() {
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
                            t.setY(y * 64 + offsetsY[i]);
                            t.setWidth(SQUARE_SIDE);
                            t.setHeight(SQUARE_SIDE);
                            t.setFill(finalColours[i + j]);
                            t.setStroke(Color.DARKSLATEGRAY);
                            root.getChildren().add(t);
                        }
                        j += 4;
                    }
                }
            }
            for(int x = 0;x < 10; x++) {
                Line lineHor = new Line();
                lineHor.setStartX(0);
                lineHor.setStartY(64*x);
                lineHor.setEndX(15*64);
                lineHor.setEndY(64*x);
                lineHor.setStrokeWidth(3);
                lineHor.setStroke(Color.NAVY);
                root.getChildren().add(lineHor);
            }
            for(int x = 0;x < 15; x++) {
                Line lineVer = new Line();
                lineVer.setStartX(64 * x);
                lineVer.setStartY(0);
                lineVer.setEndX(64 * x);
                lineVer.setEndY(10*64);
                lineVer.setStrokeWidth(3);
                lineVer.setStroke(Color.NAVY);
                root.getChildren().add(lineVer);
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }*/

    public Pane drawLevel() {
        BorderPane root = new BorderPane();

        StackPane gameBoard = new StackPane();
        root.setBottom(gameBoard);

        tileLayer = new Canvas(windowResWidth, windowResHeight);
        gameBoard.getChildren().add(tileLayer);

        Board temp = this.board;
        int boardSize = temp.getWidth() * temp.getHeight();
        int numOfSquares = boardSize * SQUARES_IN_TILE;

        GraphicsContext gc = tileLayer.getGraphicsContext2D();
        int j = 0;
        while(j < numOfSquares) {
            for(int y = 0; y < temp.getHeight(); y++) {
                for(int x = 0; x < temp.getWidth(); x++) {
                    for(int i = 0; i < SQUARES_IN_TILE; i++) {
                        gc.setFill(temp.getTile(x,y).getTileColours()[i]);
                        int rectX = x * TILE_SIZE + offsetsX[i];
                        int rectY = y * TILE_SIZE + offsetsY[i];
                        gc.fillRect(rectX, rectY, SQUARE_SIDE, SQUARE_SIDE);
                    }
                    j += 4;
                    gc.setStroke(Color.BLACK);
                    int outlineX = x * TILE_SIZE;
                    int outlineY = y * TILE_SIZE;
                    gc.strokeRect(outlineX, outlineY, TILE_SIZE, TILE_SIZE);
                }
            }
        }

        itemCharacterLayer = new Canvas(windowResWidth, windowResHeight);
        gameBoard.getChildren().add(itemCharacterLayer);

        HBox topBar = new HBox();
        Label timer = new Label("Time: " + this.time);
        Label score = new Label("Score: " + this.score);


        timer.setFont(new Font("Cambria", 22));
        score.setFont(new Font("Cambria", 22));

        topBar.getChildren().add(timer);
        topBar.getChildren().add(score);
        topBar.setAlignment(Pos.CENTER_LEFT);

        root.setTop(topBar);

        return root;
    }


    //public Color[] getTileColors(){return tileColors;}

    public int[] getOffsetsX() {
        return offsetsX;
    }

    public int[] getOffsetY() {
        return offsetsY;
    }

    public int getWindowResWidth() {
        return windowResWidth;
    }

    public int getWindowResHeight() {
        return windowResHeight;
    }
}