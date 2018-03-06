package com.drools.sample.model;

import com.drools.sample.event.Event;

public class Certificates implements Event{

    private String name;
    private String sha2;

    public Certificates(String name, String sha2) {
        this.name = name;
        this.sha2 = sha2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSha2() { return sha2; }

    public void setSha2(String sha2) { this.sha2 = sha2; }
}
