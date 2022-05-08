package com.shareclub.TwitterCloneBackendJava.role;

public class Role {

    private long id;

    private String name;

    public  Role(){
        this.id = 0;
        this.name = "";
    }

    public Role(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}