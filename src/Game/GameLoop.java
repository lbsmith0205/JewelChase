package Game;

public class GameLoop implements Runnable{
    private boolean running;
    private final double updateRate = 1.0d/60.0d;

    private long nextStartTime;
    private int ups, fps;

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

            while(accumulator > updateRate) {
                update();
                accumulator -= updateRate;
            }
            render();
            printStats();
            //draw here
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
}
