package org.example.model;

public class Recipe {
    private int id;
    private String name;
    private String description;
    private String preparationTime;
    private String difficulty;





    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setPreparationTime(String preparationTime){
        this.preparationTime = preparationTime;
    }

    public void setDifficulty(String difficulty){
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String getPreparationTime(){
        return preparationTime;
    }

    public String getDifficulty(){
        return difficulty;
    }

    public void setId(int id) {
        this.id = id;
    }


}
