package Game.Board;

import Game.Direction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

/**
 * A Menu used to create a Menu UI for the Player to interact with.
 *
 * @author Kenny Masekoameng, Luke Smith, Khoi Nguyen Cao.
 */
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
    private Button createsProfile;
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

    private Scene levelScene;
    private Level level;
    private boolean writeLevel = false;

    private Scene newGame;
    private Scene loadGame;

    private Scene levelOne;
    private Level level1 = new Level("Level1");

    private Scene levelTwo;
    private Level level2 = new Level("Level2");

    private Scene levelThree;
    private Level level3 = new Level("Level3");

    private Scene levelFour;
    private Level level4 = new Level("Level4");

    private Scene levelFive;
    private Level level5 = new Level("Level5");

    private Scene levelSix;
    private Level level6 = new Level("Level6");

    private Scene levelSeven;
    private Level level7 = new Level("Level7");

    private Group rootPause = new Group();
    private Scene scenePause = new Scene(rootPause, 500, 300, Color.NAVY);
    private Stage stagePause = new Stage();

    /**
     * Create the Menu and start up the UI.
     *
     * @param primaryStage the Stage the Menu is showing.
     */
    public Menu(Stage primaryStage){
        gameStage = primaryStage;

        gameStage.setTitle("Jewel Chase");
        gameStage.getIcons().add(new Image("Sprites/LogoWhatAmI.png", 256, 256, false, true));

        menuScene = createMenu();
        profileScene = createProfile();
        newGame = createNewGame(gameStage);
        loadGame = createLoadGame();
        gameStage.setScene(menuScene);
        gameStage.show();
    }

    //Need to do main menu javafx

    /**
     * Solve the puzzle and update the Message of the Day.
     */
    private void updateMOTD(){
        try{
            String unsolvedPuzzle = getPuzzle();
            String solvedPuzzle = puzzleSolve(unsolvedPuzzle);
            messageOfTheDay = sendSolution(solvedPuzzle);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Solve the puzzle to get the Message of the Day.
     *
     * @param puzzle the puzzle needed to be solved.
     * @return solved puzzle.
     */
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

    /**
     * Get the puzzle and Message of the Day.
     *
     * @param path the URL to the website.
     * @return the puzzle from the website
     * @throws IOException
     */
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

    /**
     * Get the puzzle from the URL.
     *
     * @return the puzzle pulled from the URL.
     * @throws IOException if input is wrong.
     */
    private static String getPuzzle() throws IOException {
        return requested(MOTD_GET_PUZZLE_URL);
    }

    /**
     * Send the solution to the URL.
     *
     * @param answer the directory to the answers.
     * @return the answers from the URL.
     * @throws IOException if the input is wrong.
     */
    private static String sendSolution(final String answer) throws IOException {
        return requested(MOTD_SOL_GET_URL + answer);
    }

    /**
     * Create the Main Menu when it first starts up.
     *
     * @return the scene need to be presented.
     */
    private Scene createMenu(){
        Group root = new Group();
        menuToProfile = new Button("Profile");
        menuToProfile.setOnAction(e -> switchScenes(profileScene));

        menuToNewGame = new Button("New Game");
        menuToNewGame.setOnAction(e -> switchScenes(newGame));
        menuToNewGame.setLayoutX(centreButton);
        menuToNewGame.setLayoutY(160);

        menuToLoadGame = new Button("Load Game");
        menuToLoadGame.setOnAction(e -> switchScenes(loadGame));
        menuToLoadGame.setLayoutX(centreButton-2);
        menuToLoadGame.setLayoutY(195);

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

    /**
     * Create the Menu to create a new Profile.
     *
     * @return the scene need to be presented.
     */
    private Scene createProfile(){

        Group root = new Group();
        returnToMenu = new Button("Return");
        returnToMenu.setOnAction(e -> switchScenes(menuScene));
        profileScene = new Scene(root, 600, 400,Color.DODGERBLUE);


        TextField profileInput = new TextField();
        profileInput.setPromptText("Enter a name");
        profileInput.setLayoutX(220);
        profileInput.setLayoutY(160);
        profileInput.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Profile profile = new Profile(profileInput.getText());
                try{
                    profile.save();
                }catch(IOException e){
                }
            }

        });

        Text text = new Text("Jewel Chase\n    Profile");
        text.setX(220);
        text.setY(30);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 25));
        root.getChildren().add(text);
        root.getChildren().add(returnToMenu);
        root.getChildren().add(profileInput);
        return profileScene;
    }



    /**
     * Create a window to pick levels and start the level.
     *
     * @param stage the Stage it will be represented on.
     * @return the scene will present the game.
     */
    private Scene createNewGame(Stage stage){
        Group root = new Group();
        returnToMenu = new Button("Return");
        returnToMenu.setOnAction(e -> switchScenes(menuScene));

        /*
        Start the game loop and run the selected level.
         */
        newGameToLevelOne = new Button("Level One");
        newGameToLevelOne.setOnAction(e -> {
            this.writeLevel = true;
            this.levelScene = levelOne;
            this.level = level1;
            switchScenes(levelScene);
            levelScene = createLevel(stage);
        });
        newGameToLevelOne.setLayoutX(centreButton);
        newGameToLevelOne.setLayoutY(70);

        newGameToLevelTwo = new Button("Level Two");
        newGameToLevelTwo.setOnAction(e -> {
            this.writeLevel = true;
            this.levelScene = levelTwo;
            this.level = level2;
            switchScenes(levelScene);
            levelScene = createLevel(stage);
        });
        newGameToLevelTwo.setLayoutX(centreButton);
        newGameToLevelTwo.setLayoutY(100);

        newGameToLevelThree = new Button("Level Three");
        newGameToLevelThree.setOnAction(e -> {
            this.writeLevel = true;
            this.levelScene = levelThree;
            this.level = level3;
            switchScenes(levelScene);
            levelScene = createLevel(stage);
        });
        newGameToLevelThree.setLayoutX(centreButton);
        newGameToLevelThree.setLayoutY(130);

        newGameToLevelFour = new Button("Level Four");
        newGameToLevelFour.setOnAction(e -> {
            this.writeLevel = true;
            this.levelScene = levelFour;
            this.level = level4;
            switchScenes(levelScene);
            levelScene = createLevel(stage);
        });
        newGameToLevelFour.setLayoutX(centreButton);
        newGameToLevelFour.setLayoutY(160);

        newGameToLevelFive = new Button("Level Five");
        newGameToLevelFive.setOnAction(e -> {
            this.writeLevel = true;
            this.levelScene = levelFive;
            this.level = level5;
            switchScenes(levelScene);
            levelScene = createLevel(stage);
        });
        newGameToLevelFive.setLayoutX(centreButton);
        newGameToLevelFive.setLayoutY(190);

        newGameToLevelSix = new Button("Level Six");
        newGameToLevelSix.setOnAction(e -> {
            this.writeLevel = true;
            this.levelScene = levelSix;
            this.level = level6;
            switchScenes(levelScene);
            levelScene = createLevel(stage);
        });
        newGameToLevelSix.setLayoutX(centreButton);
        newGameToLevelSix.setLayoutY(220);

        newGameToLevelSeven = new Button("Level Seven");
        newGameToLevelSeven.setOnAction(e -> {
            this.writeLevel = true;
            this.levelScene = levelSeven;
            this.level = level7;
            switchScenes(levelScene);
            levelScene = createLevel(stage);
        });
        newGameToLevelSeven.setLayoutX(centreButton);
        newGameToLevelSeven.setLayoutY(250);

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
        root.getChildren().add(newGameToLevelSix);
        root.getChildren().add(newGameToLevelSeven);
        return newGame;
    }

    /**
     * Creating the Menu for loading games.
     *
     * @return the scene to present the Menu.
     */
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
    /**
     * Create the window for a Level.
     *
     * @param primaryStage the Stage it will be represented on.
     * @return the scene to present the Level window.
     */
    private Scene createLevel(Stage primaryStage){

        Pane root = level.drawInit();
        menuToNewGame = new Button("Return");
        menuToNewGame.setOnAction(e -> switchScenes(newGame));

        levelScene = new Scene(root, level.getWindowResWidth(), level.getWindowResHeight() + 32);
        levelScene.addEventFilter(KeyEvent.KEY_PRESSED, this::processKeyEvent);

        tickTimeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
            tick();
            if(level.checkEndGame()) {
                tickTimeline.stop();
                switchScenes(createVictoryScreen());
            }
        }));
        tickTimeline.setCycleCount(Animation.INDEFINITE);
        primaryStage.setScene(levelScene);
        tickTimeline.play();

        return levelScene;
    }

    /**
     * Create a Victory screen when you win a game.
     * @return scene to change to when victory.
     */
    private Scene createVictoryScreen() {
        Group group = new Group();
        Scene scene = new Scene(group, level.getWindowResWidth(),level.getWindowResHeight() + 32);
        Button goToLevelScreen = new Button("CONTINUE!");
        group.getChildren().add(goToLevelScreen);
        goToLevelScreen.setLayoutX(level.getWindowResWidth()/2);
        goToLevelScreen.setLayoutY(level.getWindowResHeight()/2);
        goToLevelScreen.setOnAction(e -> {
            switchScenes(newGame);
        });
        return scene;
    }

    /**
     * Create a window to interact with when the Game is paused
     */
    private void createPauseWindow() {
        Button mainMenu = new Button("Main Menu");
        mainMenu.setLayoutX(200);
        mainMenu.setLayoutY(180);

        Button exit = new Button("Exit");
        exit.setLayoutX(200);
        exit.setLayoutY(150);

        Button saveButton = new Button("Save");
        saveButton.setLayoutX(200);
        saveButton.setLayoutY(125);

        Button resume = new Button("Resume");
        resume.setLayoutX(200);
        resume.setLayoutY(100);


        rootPause.getChildren().add(saveButton);
        rootPause.getChildren().add(resume);
        rootPause.getChildren().add(exit);
        rootPause.getChildren().add(mainMenu);

        resume.setOnAction(e -> {
            tickTimeline.play();
            stagePause.close();
        });

        exit.setOnAction(e -> {
            tickTimeline.stop();
            stagePause.close();
            gameStage.close();
        });

        mainMenu.setOnAction(e -> {
            tickTimeline.stop();
            stagePause.close();
            switchScenes(menuScene);
        });

        stagePause.setScene(scenePause);
        stagePause.setTitle("Paused Game");
        stagePause.show();
    }

    /**
     * Tick the timer down and update the Level and redrawing it.
     */
    private void tick() {
        level.moveAll();
        level.interactAll();
        level.refreshBombs();
        level.countdown();
        level.drawLevel();

    }

    /**
     * Check the key pressed and move the Player around.
     * @param event the key pressed.
     */
    private void processKeyEvent(KeyEvent event) {
        if(tickTimeline.getStatus() != Animation.Status.PAUSED) {
            switch (event.getCode()) {
                case W, UP -> level.getPlayer().setDirection(Direction.UP);
                case A, LEFT -> level.getPlayer().setDirection(Direction.LEFT);
                case S, DOWN -> level.getPlayer().setDirection(Direction.DOWN);
                case D, RIGHT -> level.getPlayer().setDirection(Direction.RIGHT);
                case ESCAPE -> {
                    tickTimeline.pause();
                    createPauseWindow();
                }
            }
        } else {
            switch (event.getCode()) {
                case ESCAPE -> {
                    tickTimeline.play();
                    stagePause.close();
                }
            }
        }

        if(tickTimeline.getStatus() != Animation.Status.PAUSED) {
            level.getPlayer().move(level.getBoard());
            level.interactAll();
            level.drawLevel();
            event.consume();
        }
    }

    /**
     * Change the window of the UI.
     * @param scene the scene to switch to.
     */
    public void switchScenes(Scene scene){
        gameStage.setScene(scene);
    }
}

