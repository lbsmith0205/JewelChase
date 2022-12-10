package Game;

import Game.Board.Level;

public class GameLoop implements Runnable{
    private final double tickRate = 1.0d/60.0d;

    private Level level;

    private boolean running;
    private long nextStartTime;
    private int ups, fps;

    public GameLoop(String fileName) {
        this.level = new Level(fileName);
    }

    @Override
    public void run() {
        running = true;
        double accumulator = 0;
        long currentTime = System.currentTimeMillis();
        long lastUpdate = System.currentTimeMillis();
        nextStartTime = System.currentTimeMillis() + 1000;

        while(running) {
            currentTime = System.currentTimeMillis();
            double lastDrawnTime = (currentTime - lastUpdate) /1000d;
            accumulator += lastDrawnTime;
            lastUpdate = currentTime;

            if(accumulator >= tickRate) {
                while(accumulator > tickRate) {
                    update();
                    accumulator -= tickRate;
                }
                render();//draw here
            }
            printStats();

        }
    }

    public void stop(){
        running = false;
    }

    private void render() {
        fps++;
    }

    private void update() {
        ups++;
    }

    private void printStats(){
        if(nextStartTime < System.currentTimeMillis()) {
            System.out.println("Fps: " + fps + "  Ups: " + ups);
            fps = 0;
            ups = 0;
            nextStartTime = System.currentTimeMillis() + 1000;
        }
    }

    public Level getLevel() {
        return this.level;
    }
}
