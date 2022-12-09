package Game;

import Game.Board.Level;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {
    static GameLoop gameLoop = new GameLoop("Level3");
    Level level = gameLoop.getLevel();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = level.drawLevel();

        Scene scene = new Scene(root, level.getWindowResWidth(), level.getWindowResHeight() + 40);

        primaryStage.setScene(scene);
        primaryStage.show();

    }



    public static void main(String[] args) {
        //new Thread(new GameLoop("Level3")).start();
        new Thread(gameLoop).start();
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

