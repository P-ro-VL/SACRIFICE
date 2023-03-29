package vn.provl.api.screen;

import vn.provl.Sacrifice;

public abstract class ScreenThread extends Thread {

    private boolean isRunning = true;

    public ScreenThread(String screenID) {
        setName(screenID + "-screen-thread");
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public abstract void clock();

    /////////// FPS TICKING. USING DELTA TIME METHOD ///////////
    double drawInterval = 1000000000 / Sacrifice.FPS_LIMIT;
    private double delta = 0;
    private long lastTimestamp = System.nanoTime();

    @Override
    public void run() {
        while(isRunning()){
            long currentTimestamp = System.nanoTime();
            delta += (currentTimestamp - lastTimestamp) / drawInterval;
            lastTimestamp = currentTimestamp;

            try {
                if(delta >= 1){
                    clock();
                    delta--;
                }
            } catch (Exception ex){
                setRunning(false);
                throw new RuntimeException("An unexpected error has occurred in thread " + getName() + ". This thread " +
                        "has stopped running!");
            }
        }
    }
}
