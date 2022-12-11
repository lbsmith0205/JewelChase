package Game.Board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Menu {
    //Puzzle solution constants
    private static final String MOTD_INITIAL = "http";
    private static final String MOTD_GET_URL = "cswebcat.swansea.ac.uk";
    private static final String MOTD_GET_PUZZLE_URL = "/puzzle";
    private static final String MOTD_SOL_GET_URL = "/message?solution=";
    private static final String MOTD_REQUEST_METHOD = "GET";
    private static final String MOTD_APPEND = "CS-230";
    private static final int MOTD_ALPHABET_LENGTH = 26;
    private static final int MOTD_INT_CONVERSION = -1;

    private Stage gameStage;

    private Scene menuScene;
    private Button menuToProfile;
    private Button menuToNewGame;
    private Button menuToLoadGame;

    private Scene profileScene;
    private Button returnToMenu;

    private Scene newGame;

    private Scene loadGame;

    private int centreButton = 260;


    public Menu(Stage primaryStage){
        gameStage = primaryStage;
        gameStage.setTitle("Jewel Chase");

        menuScene = createMenu();
        profileScene = createProfile();
        newGame = createNewGame();
        loadGame = createLoadGame();

        gameStage.setScene(menuScene);
        gameStage.show();
    }


    // Calls all functions used for the main menu
    public void startMenu(){
        updateMOTD();
    }
    //Need to do main menu javafx

    //Updates message of the day
    private void updateMOTD(){
        String messageOfTheDay;
        try{
            String unsolvedPuzzle = getPuzzle();
            String solvedPuzzle = puzzleSolve(unsolvedPuzzle);
            messageOfTheDay = sendSolution(solvedPuzzle);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


                         // i dont remember how this works...
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

        menuToLoadGame = new Button("Level Select");
        menuToLoadGame.setOnAction(e -> switchScenes(loadGame));
        menuToLoadGame.setLayoutX(centreButton-2);menuToLoadGame.setLayoutY(195);

        menuScene = new Scene(root, 600, 400, Color.LIGHTCORAL);
        Text text = new Text("Jewel Chase\n Main Menu");
        text.setX(220);
        text.setY(30);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 25));
        root.getChildren().add(text);
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
        newGame = new Scene(root, 600, 400,Color.DODGERBLUE);
        Text text = new Text(" Jewel Chase\nSelect a Level");
        text.setX(220);
        text.setY(30);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 25));
        root.getChildren().add(text);
        root.getChildren().add(returnToMenu);
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

    public void switchScenes(Scene scene){
        gameStage.setScene(scene);
    }
}

