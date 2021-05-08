package com.podong.game.module.schduling.batch;

public class BatchClass implements Batch{
    private Batch bh;
    @Override
    public void start() {
        bh.start();
    }
    public void setClassName(Batch bh)
    {
        this.bh = bh;
    }
}
