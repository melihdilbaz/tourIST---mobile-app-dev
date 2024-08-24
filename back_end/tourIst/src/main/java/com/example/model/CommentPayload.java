package com.example.model;

public class CommentPayload {
    private String name;
    private String text;

    public CommentPayload(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "CommentPayload{" +
                "name='" + name + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

