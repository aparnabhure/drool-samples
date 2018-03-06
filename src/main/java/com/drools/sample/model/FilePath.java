package com.drools.sample.model;

import com.drools.sample.event.Event;

public class FilePath implements Event{

    private String path;

    public FilePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
