package com.example.tourist;

import java.util.ArrayList;
import java.util.List;

public class Destination {
    private String id;

    private String name;
    private String text;
    private String link;
    private String image;

    private Category category;

    private List<Comment> comments = new ArrayList<>();


    public Destination(String id, String name, String text, String link, String image, Category category) {
        super();
        this.id = id;
        this.name = name;
        this.text = text;
        this.link = link;
        this.image = image;
        this.category = category;
    }

    public Destination() {
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Destination [id=" + id + ", name=" + name + ", text=" + text + ", link=" + link + ", image=" + image
                + ", category=" + category + "]";
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
