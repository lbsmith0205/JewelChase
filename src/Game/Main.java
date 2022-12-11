package Game;

import Game.Board.Level;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Main extends Application {

    Level level = new Level("Level3");
    private Timeline tickTimeline;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = level.drawInit();
        Scene scene = new Scene(root, level.getWindowResWidth(), level.getWindowResHeight() + 32);
        //scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event));

        tickTimeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> tick()));
        // Loop the timeline forever
        tickTimeline.setCycleCount(Animation.INDEFINITE);
        primaryStage.setTitle("Jewel Chase");
        primaryStage.setScene(scene);
        primaryStage.show();
        tickTimeline.play();
    }

    private void tick() {
        level.moveAll();
        level.countdown();
        level.drawLevel();
    }
    /*private void processKeyEvent(KeyEvent event) {
        switch (event.getCode()) {
            case W, UP -> Direction.UP;
            case A, LEFT -> direction = Direction.LEFT;
            case S, DOWN -> direction = Direction.DOWN;
            case D, RIGHT -> direction = Direction.RIGHT;
        }*/



    public static void main(String[] args) {
        launch(args);
    }
}

