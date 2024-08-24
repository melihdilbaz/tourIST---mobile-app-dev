package com.example.tourist;

public class Category {
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return name; // This will be the text displayed in the ArrayAdapter
    }
}
