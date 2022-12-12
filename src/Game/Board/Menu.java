package Game.Board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Time;
import java.util.Objects;

import Game.Direction;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Menu {
    private static final String MOTD_INITIAL = "http";
    private static final String MOTD_GET_URL = "cswebcat.swansea.ac.uk";
    private static final String MOTD_GET_PUZZLE_URL = "/puzzle";
    private static final String MOTD_SOL_GET_URL = "/message?solution=";
    private static final String MOTD_REQUEST_METHOD = "GET";
    private static final String MOTD_APPEND = "CS-230";
    private static final int MOTD_ALPHABET_LENGTH = 26;
    private static final int MOTD_INT_CONVERSION = -1;
    private String messageOfTheDay;
    private int centreButton = 260;

    private Stage gameStage;
    private Timeline tickTimeline;
    private Scene menuScene;
    private Button menuToProfile;
    private Button menuToNewGame;
    private Button menuToLoadGame;
    private Scene profileScene;
    private Button returnToMenu;
    private Button newGameToLevelOne;
    private Button newGameToLevelTwo;
    private Button newGameToLevelThree;
    private Button newGameToLevelFour;
    private Button newGameToLevelFive;
    private Button newGameToLevelSix;
    private Button newGameToLevelSeven;
    private Button newGameToLevelEight;
    private Button newGameToLevelNine;

    private Scene newGame;
    private Scene loadGame;
    private Scene levelOne;Level level1 = new Level("Level1");
    private Scene levelTwo;Level level2 = new Level("Level2");
    private Scene levelThree;Level level3 = new Level("Level3");
    private Scene levelFour;Level level4 = new Level("Level4");
    private Scene levelFive;Level level5 = new Level("Level5");
    //private Scene levelSix;Level level6 = new Level("Level6");
    // private Scene levelSeven;Level level7 = new Level("Level7");
    public Menu(Stage primaryStage){
        gameStage = primaryStage;

        gameStage.setTitle("Jewel Chase");
        gameStage.getIcons().add(new Image("Sprites/Coconut.png", 256, 256, false, true));

        menuScene = createMenu();
        profileScene = createProfile();
        newGame = createNewGame();
        loadGame = createLoadGame();
        levelOne = createlevel(gameStage);

        gameStage.setScene(menuScene);
        gameStage.show();
    }

    //Need to do main menu javafx

    //Updates message of the day
    private void updateMOTD(){
        try{
            String unsolvedPuzzle = getPuzzle();
            String solvedPuzzle = puzzleSolve(unsolvedPuzzle);
            messageOfTheDay = sendSolution(solvedPuzzle);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private String puzzleSolve(String puzzle){
        String possibleLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int moveLetter = -1;
        StringBuilder solution = new StringBuilder();
        for (int i = 0 ; i < puzzle.length() ; i++){
            int alphabetPos = possibleLetters.indexOf(puzzle.charAt(i));
            solution.append(possibleLetters.charAt(Math.floorMod((alphabetPos + ((i + 1) * moveLetter)), MOTD_ALPHABET_LENGTH)));
            moveLetter = moveLetter * MOTD_INT_CONVERSION;
        }
        solution.append(MOTD_APPEND);
        String length = String.valueOf(solution.length());
        solution = new StringBuilder(length + solution);
        return String.valueOf(solution);
    }

    private static String requested(final String path) throws IOException {
        URL url = new URL(MOTD_INITIAL,MOTD_GET_URL, path);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(MOTD_REQUEST_METHOD);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        String reply;
        if(con.getResponseCode() == HttpURLConnection.HTTP_OK){
            reply = in.readLine();
        }
        else{
            reply = null;
        }

        con.disconnect();
        return reply;

    }

    private static String getPuzzle() throws IOException {
        return requested(MOTD_GET_PUZZLE_URL);
    }

    private static String sendSolution(final String answer) throws IOException {
        return requested(MOTD_SOL_GET_URL + answer);
    }

    private Scene createMenu(){
        Group root = new Group();
        menuToProfile = new Button("Profile");
        menuToProfile.setOnAction(e -> switchScenes(profileScene));

        menuToNewGame = new Button("New Game");
        menuToNewGame.setOnAction(e -> switchScenes(newGame));
        menuToNewGame.setLayoutX(centreButton);menuToNewGame.setLayoutY(160);

        menuToLoadGame = new Button("Load Game");
        menuToLoadGame.setOnAction(e -> switchScenes(loadGame));
        menuToLoadGame.setLayoutX(centreButton-2);menuToLoadGame.setLayoutY(195);

        menuScene = new Scene(root, 600, 400, Color.LIGHTCORAL);
        Text banner = new Text("Jewel Chase\n Main Menu");
        banner.setX(220);
        banner.setY(30);
        banner.setFill(Color.WHITE);
        banner.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 25));

        updateMOTD();
        Text motd = new Text(messageOfTheDay);
        motd.setX(0);
        motd.setY(350);
        motd.setFill(Color.WHITE);
        motd.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 12));
        motd.setWrappingWidth(580);
        motd.setTextAlignment(TextAlignment.CENTER);


        root.getChildren().add(motd);
        root.getChildren().add(banner);
        root.getChildren().add(menuToProfile);
        root.getChildren().add(menuToNewGame);
        root.getChildren().add(menuToLoadGame);
        return menuScene;
    }

    private Scene createProfile(){

        Group root = new Group();
        returnToMenu = new Button("Return");
        returnToMenu.setOnAction(e -> switchScenes(menuScene));
        profileScene = new Scene(root, 600, 400,Color.DODGERBLUE);
        Text text = new Text("Jewel Chase\n    Profile");
        text.setX(220);
        text.setY(30);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 25));
        root.getChildren().add(text);
        root.getChildren().add(returnToMenu);
        return profileScene;
    }
    private Scene createNewGame(){

        Group root = new Group();
        returnToMenu = new Button("Return");
        returnToMenu.setOnAction(e -> switchScenes(menuScene));

        newGameToLevelOne = new Button("Level One");
        newGameToLevelOne.setOnAction(e -> switchScenes(levelOne));
        newGameToLevelOne.setLayoutX(centreButton);newGameToLevelOne.setLayoutY(70);

        newGameToLevelTwo = new Button("Level Two");
        newGameToLevelTwo.setOnAction(e -> switchScenes(levelTwo));
        newGameToLevelTwo.setLayoutX(centreButton);newGameToLevelTwo.setLayoutY(100);

        newGameToLevelThree = new Button("Level Three");
        newGameToLevelThree.setOnAction(e -> switchScenes(levelThree));
        newGameToLevelThree.setLayoutX(centreButton);newGameToLevelThree.setLayoutY(130);

        newGameToLevelFour = new Button("Level Four");
        newGameToLevelFour.setOnAction(e -> switchScenes(levelFour));
        newGameToLevelFour.setLayoutX(centreButton);newGameToLevelFour.setLayoutY(160);

        newGameToLevelFive = new Button("Level Five");
        newGameToLevelFive.setOnAction(e -> switchScenes(levelFive));
        newGameToLevelFive.setLayoutX(centreButton);newGameToLevelFive.setLayoutY(190);

        /*newGameToLevelSix = new Button("Level Six");
        newGameToLevelSix.setOnAction(e -> switchScenes(levelSix));
        newGameToLevelSix.setLayoutX(centreButton);newGameToLevelSix.setLayoutY(220);

        newGameToLevelSeven = new Button("Level Seven");
        newGameToLevelSeven.setOnAction(e -> switchScenes(levelSeven));
        newGameToLevelSeven.setLayoutX(centreButton);newGameToLevelSeven.setLayoutY(250);*/

        newGame = new Scene(root, 600, 400,Color.DODGERBLUE);
        Text text = new Text(" Jewel Chase\nSelect a Level");
        text.setX(220);
        text.setY(30);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 25));

        root.getChildren().add(text);
        root.getChildren().add(returnToMenu);
        root.getChildren().add(newGameToLevelOne);
        root.getChildren().add(newGameToLevelTwo);
        root.getChildren().add(newGameToLevelThree);
        root.getChildren().add(newGameToLevelFour);
        root.getChildren().add(newGameToLevelFive);
        return newGame;
    }
    private Scene createLoadGame(){

        Group root = new Group();
        returnToMenu = new Button("Return");
        returnToMenu.setOnAction(e -> switchScenes(menuScene));

        loadGame = new Scene(root, 600, 400,Color.DODGERBLUE);
        Text text = new Text("     Jewel Chase\nSelect a savepoint");
        text.setX(200);
        text.setY(30);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 25));
        root.getChildren().add(text);
        root.getChildren().add(returnToMenu);
        return loadGame;
    }
    private Scene createlevel(Stage primaryStage){
        Pane root = level1.drawInit();
        menuToNewGame = new Button("Return");
        menuToNewGame.setOnAction(e -> switchScenes(newGame));

        levelOne = new Scene(root, level1.getWindowResWidth(), level1.getWindowResHeight() + 32);
        levelOne.addEventFilter(KeyEvent.KEY_PRESSED, this::processKeyEvent);

        tickTimeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> tick()));
        tickTimeline.setCycleCount(Animation.INDEFINITE);
        primaryStage.setScene(levelOne);
        tickTimeline.play();

        return levelOne;
    }

    private void tick() {
        level1.moveAll();
        level1.countdown();
        level1.drawLevel();
        level1.accumulate();
    }
    private void processKeyEvent(KeyEvent event) {
        switch (event.getCode()) {
            case W, UP -> level1.getPlayer().setDirection(Direction.UP);
            case A, LEFT -> level1.getPlayer().setDirection(Direction.LEFT);
            case S, DOWN -> level1.getPlayer().setDirection(Direction.DOWN);
            case D, RIGHT -> level1.getPlayer().setDirection(Direction.RIGHT);
        }
        level1.getPlayer().move(level1.getBoard());
        level1.drawLevel();
        event.consume();
    }
   /*
    public Scene drawGame() {
        Pane root = level1.drawInit();
        Scene scene = new Scene(root, level1.getWindowResWidth(), level1.getWindowResHeight() + 32);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event));

        tickTimeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> tick()));
        tickTimeline.setCycleCount(Animation.INDEFINITE);
        primaryStage.setTitle("Jewel Chase");
        primaryStage.getIcons().add(new Image("Sprites/Coconut.png", 256, 256, false, true));
        primaryStage.setScene(scene);
        primaryStage.show();
        tickTimeline.play();
    }

    */


    public void switchScenes(Scene scene){
        gameStage.setScene(scene);
    }
}

