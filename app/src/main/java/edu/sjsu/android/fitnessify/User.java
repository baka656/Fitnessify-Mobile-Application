package edu.sjsu.android.fitnessify;

public class User {
    private String name, mob, email;
    private int weight, height, steps;

    public User() {
    }

    public User(String email, String name, String mob, int weight, int height, int steps) {
        this.name = name;
        this.email = email;
        this.mob = mob;
        this.weight = weight;
        this.height = height;
        this.steps = steps;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMob() {
        return mob;
    }


    public void setMob(String mob) {
        this.mob = mob;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
}

