package Game;

public class GameLoop implements Runnable{
    private boolean running;
    private final double updateRate = 1.0d/60.0d;
    private int ups;

    @Override
    public void run() {
        running = true;
        double accumulator = 0;
        long currentTime = System.currentTimeMillis();
        long lastUpdate = System.currentTimeMillis();

        while(running) {
            currentTime = System.currentTimeMillis();
            double lastDrawnTime = (currentTime - lastUpdate) /1000d;
            accumulator += lastDrawnTime;
            lastUpdate = currentTime;

            while(accumulator > updateRate) {
                update();
                accumulator -= updateRate;
            }

            //draw here
        }
    }

    public void stop(){
        running = false;
    }

    private void update() {
        ups++;
    }
}
