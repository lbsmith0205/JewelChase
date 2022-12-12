package Game;

import Game.Board.Level;
import Game.Board.Menu;
import Game.Board.Profile;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Main class to run the Game.
 */
public class Main extends Application {

    Level level = new Level("Level1");
    private Timeline tickTimeline;

    @Override
    public void start(Stage primaryStage) throws Exception {
       Menu menu = new Menu(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }


}

