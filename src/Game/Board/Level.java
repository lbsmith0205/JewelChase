package Game.Board;

import Game.Characters.*;
import Game.Direction;
import Game.Items.*;
import Game.Items.Item;
import Game.Characters.Character;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * @author Kenny Masekoameng, Luke Smith, Daniel Baxter, Khoi Nguyen Cao
 */
public class Level {
    private static final int SUB_TILE_SIDE = 32;
    private static final int TILE_SIDE = 64;

    private final String levelFilePath;
    private final int windowResWidth;
    private final int windowResHeight;

    private Board board;
    private int width;
    private int height;
    private int time;
    private int score;
    private int levelNo;

    private Canvas boardArea;
    private Canvas topBar;

    private int accumulator = 0;

    public Level(String fileName) {
        this.levelFilePath = "src/Levels/" + fileName + ".txt";
        this.readLevelFile(levelFilePath);

        windowResWidth = this.board.getWidth() * TILE_SIDE;
        windowResHeight = this.board.getHeight() * TILE_SIDE;
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
     *
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
        board.refreshNavGraph();
        fileReader.close();
    }


    /**
     * Reads the level parameters from the top line of the level file.
     *
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
     *
     * @param x
     * @param y
     * @param colours
     * @return
     */
    private Tile parseTile(int x, int y, String colours) {
        char[] coloursList = colours.toCharArray();
        Color[] tileColours = new Color[4];
        for (int i = 0; i < 4; i++) {
            tileColours[i] = charToColour(coloursList[i]);
        }
        return new Tile(x, y, tileColours);
    }

    private Color charToColour(char character) {
        Color colour;
        switch (character) {
            case 'R' -> colour = Color.INDIANRED;
            case 'G' -> colour = Color.SPRINGGREEN;
            case 'B' -> colour = Color.ROYALBLUE;
            case 'Y' -> colour = Color.KHAKI;
            case 'C' -> colour = Color.CYAN;
            case 'M' -> colour = Color.MEDIUMPURPLE;
            default -> colour = Color.WHITE;
        }
        return colour;
    }

    /**
     * Parses line of text file containing items and their locations.
     *
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
     *
     * @param characterLine
     */
    private void parseCharacters(String characterLine) {
        String[] characters = characterLine.split(" ");
        for (String character : characters) {
            Scanner parseCharacter = new Scanner(character);
            parseCharacter.useDelimiter(",");
            int x = parseCharacter.nextInt();
            int y = parseCharacter.nextInt();
            String type = parseCharacter.next();
            char supplementaryChar = parseCharacter.next().charAt(0);
            parseCharacter.close();

            Direction direction;
            switch (supplementaryChar) {
                case 'U' -> direction = Direction.UP;
                case 'L' -> direction = Direction.LEFT;
                case 'D' -> direction = Direction.DOWN;
                case 'R' -> direction = Direction.RIGHT;
                default -> direction = null;
            }

            switch (type) {
                case "P":
                    Player newP = new Player(board.getTile(x, y), direction);
                    board.getTile(x, y).addObjectToTile(newP);
                    break;
                case "FFT":
                    direction = Direction.LEFT;
                    FloorFollowingThief newFFT = new FloorFollowingThief(board.getTile(x, y), direction);
                    newFFT.setFollowingColour(charToColour(supplementaryChar));
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

    public Pane drawInit() {
        BorderPane root = new BorderPane();

        topBar = new Canvas(windowResWidth, SUB_TILE_SIDE);
        root.setTop(topBar);

        boardArea = new Canvas(windowResWidth, windowResHeight);
        root.setBottom(boardArea);

        return root;
    }

    public void drawLevel() {
        GraphicsContext gc = topBar.getGraphicsContext2D();
        gc.clearRect(0, 0, windowResWidth, SUB_TILE_SIDE);
        gc.strokeText("Time: " + time + "s Score: " + score, 0, SUB_TILE_SIDE / 2);

        gc = boardArea.getGraphicsContext2D();
        gc.clearRect(0, 0, windowResWidth, windowResHeight);
        board.drawBoard(gc);

    }

    public void moveAll() {
        ArrayList<Character> characters = board.getAllCharacters();
        for (Character character : characters) {
            if (!(character instanceof Player)) {
                character.move(board);
            }
        }
    }

    public void save() {
        String fileName = "Level" + levelNo + ".txt";
        File saveFile = new File("src/SavedGame/" + fileName);

        try {
            saveFile.createNewFile();
            print(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void print(File file) {
        PrintWriter printWriter = null;
        if(file.exists()) {
            try {
                printWriter  = new PrintWriter(file);
                String firstLine = String.format("%d %d %d %d %d", width, height, time, score, levelNo);
                printWriter.println(firstLine);
                for(int y = 0; y < this.height; y++) {
                    for (int x = 0; x < this.width; x++) {
                        Tile t = this.board.getTile(x, y);
                        String tString = t.getTileInString();
                        printWriter.print(tString + " ");
                    }
                    printWriter.print("\n");
                }
                ArrayList<Item> items = board.getAllItems();
                for(Item t : items) {
                    int xPos = t.getPosition().getXPosition();
                    int yPos = t.getPosition().getYPosition();
                    printWriter.print(xPos + "," + yPos + "," + t.getTypeInString() + " ");
                }
                printWriter.print("\n");

                ArrayList<Character> characters = board.getAllCharacters();
                for(Character c : characters) {
                    int xPos = c.getPosition().getXPosition();
                    int yPos = c.getPosition().getYPosition();
                    printWriter.print(xPos + "," + yPos + "," + c.getTypeInString() +
                            "," + c.getDirectionInString() + " ");
                }
                printWriter.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + file.getName());
            }
        }
    }

    public int getWindowResWidth() {
        return windowResWidth;
    }

    public int getWindowResHeight() {
        return windowResHeight;
    }

    public void countdown() {
        time--;
    }

    public String getLevelFilePath() {
        return this.levelFilePath;
    }

    public Player getPlayer() {
        return board.getPlayer();
    }

    public Board getBoard() {
        return board;
    }

    public int getAccumulatorValue() {
        return this.accumulator;
    }

    public void accumulate() {
        accumulator++;
    }
}
