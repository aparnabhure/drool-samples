package com.drools.sample.model;

import com.drools.sample.event.Event;

public class FileHash implements Event{

    private String hash;

    public FileHash(String hash) {
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
