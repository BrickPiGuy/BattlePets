package com.example.battlepets;

public class Pet {
    private int id; //primary key
    private String name;
    private String type;
    private int strength;

    //Constructor
    public Pet (int id, String name, String type ,int strength){
        this.id = id;
        this.name = name;
        this.type = type;
        this.strength = strength;
    }

    //Getters
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getStrength() {
        return strength;
    }

    public String getType() {
        return type;
    }

    //Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setType(String type) {
        this.type = type;
    }

    //toString functions

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", strength=" + strength +
                '}';
    }
}
