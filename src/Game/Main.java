package Game;

import Game.Board.Level;
import Game.Board.Menu;
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

public class Main extends Application {

    Level level = new Level("Level1");
    private Timeline tickTimeline;

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
        Pane root = level.drawInit();
        Scene scene = new Scene(root, level.getWindowResWidth(), level.getWindowResHeight() + 32);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event));

        tickTimeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> tick()));
        tickTimeline.setCycleCount(Animation.INDEFINITE);
        primaryStage.setTitle("Jewel Chase");
        primaryStage.getIcons().add(new Image("Sprites/Coconut.png", 256, 256, false, true));
        primaryStage.setScene(scene);
        primaryStage.show();
        tickTimeline.play();
         */
        //tickTimeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> tick()));
        //tickTimeline.setCycleCount(Animation.INDEFINITE);


        Menu menu = new Menu(primaryStage);
    }

    private void tick() {
        level.moveAll();
        level.countdown();
        level.drawLevel();
        level.accumulate();
    }
    private void processKeyEvent(KeyEvent event) {
        switch (event.getCode()) {
            case W, UP -> level.getPlayer().setDirection(Direction.UP);
            case A, LEFT -> level.getPlayer().setDirection(Direction.LEFT);
            case S, DOWN -> level.getPlayer().setDirection(Direction.DOWN);
            case D, RIGHT -> level.getPlayer().setDirection(Direction.RIGHT);
        }
        level.getPlayer().move(level.getBoard());
        level.drawLevel();
        event.consume();
    }
    public static void main(String[] args) {
        launch(args);
    }


}

