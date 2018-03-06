package com.drools.sample.model;

import com.drools.sample.event.Event;

public class Publisher implements Event{

    private String name;
    private String policy;

    public Publisher(String name, String policy) {
        this.name = name;
        this.policy = policy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPolicy() { return policy; }

    public void setPolicy(String policy) { this.policy = policy; }
}
