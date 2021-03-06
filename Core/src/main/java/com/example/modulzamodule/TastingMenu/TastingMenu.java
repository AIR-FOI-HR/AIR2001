package com.example.modulzamodule.TastingMenu;

public class TastingMenu {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String duration;
    private String description;
    private String location;
    private String id;

    public String getId() {
        return id;
    }

    public TastingMenu(
            String id,
            String name,
            String duration,
            String description,
            String location
    )
    {
        this.id = id;
        this.name= name;
        this.description=description;
        this.duration = duration;
        this.location = location;
    }




}
