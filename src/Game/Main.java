package Game;

import Game.Board.Level;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {
    Level level1 = new Level("Level1");

    @Override
    public void start(Stage primaryStage) throws Exception {
        level1.getOffsetsX();
        level1.getOffsetY();
        Pane root = level1.drawLevel();

        Scene scene = new Scene(root, level1.getWindowResWidth(), level1.getWindowResHeight() + 40);

        primaryStage.setScene(scene);
        primaryStage.show();

    }



    public static void main(String[] args) {
        //new Thread(new GameLoop()).start();
        /*while(true)
        System.out.println(System.currentTimeMillis());*/
        /*System.out.println("Enter: ");
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        Scanner string = new Scanner(line);
        string.useDelimiter(",");
        int i = string.nextInt();
        String s = string.next();

        System.out.println(i + " " + s);*/
        launch(args);
    }
}

