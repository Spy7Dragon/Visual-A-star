package com.brandenhuggins;

public interface Observer {
    public void update( Subject o ) throws InterruptedException;
}

