package com.example.tourist;

public class Comment {
    private String id;
    private String name;
    private String text;

    public Comment(String id, String name, String text) {
        super();
        this.id = id;
        this.name = name;
        this.text = text;
    }

    public Comment() {
        // TODO Auto-generated constructor stub
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "Comment [id=" + id + ", name=" + name + ", text=" + text + "]";
    }
}
