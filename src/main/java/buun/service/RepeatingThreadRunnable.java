package buun.service;

import buun.exception.ExceptionCatcher;

public abstract class RepeatingThreadRunnable implements Runnable{

    private final Thread thread;
    private boolean running;

    protected RepeatingThreadRunnable(String name){
        this.thread = new Thread(this::repeat, name);
    }

    private void repeat(){
        while (isRunning()) run();
    }

    public boolean start(){
        if(isRunning()) return false;
        this.running = true;
        thread.start();
        return true;
    }

    public boolean stop() {
        if (!running) return false;
        new ExceptionCatcher<>(thread::interrupt).tryCatch();
        running = false;
        return false;
    }

    public boolean isRunning(){
        return this.running;
    }
}
