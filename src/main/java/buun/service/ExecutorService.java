package buun.service;

import buun.exception.ExceptionCatcher;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public abstract class ExecutorService<K, V> extends RepeatingThreadRunnable implements Consumer<V>{

    private final Map<K, V> queue;
    private final Map<K, V> priorityQueue;
    private final int timeOutMillis;

    protected ExecutorService(String name, int timeOutMillis) {
        super(name);
        this.queue = new ConcurrentHashMap<>();
        this.priorityQueue = new ConcurrentHashMap<>();
        this.timeOutMillis = timeOutMillis;
    }

    public void update(){
        priorityQueue.values().forEach(this);
        priorityQueue.clear();
        queue.values().forEach(this);
        queue.values().clear();
    }

    public ExecutorService<K, V> enqueue(K key, V value){
        return enqueue(key, value, false);
    }

    public ExecutorService<K, V> enqueue(K key, V value, boolean priority){
        if(priority) priorityQueue.put(key, value);
        else queue.put(key, value);
        return this;
    }

    @Override
    public void run(){
        update();
        new ExceptionCatcher<>(() -> Thread.sleep(timeOutMillis)).tryCatch();
    }
}