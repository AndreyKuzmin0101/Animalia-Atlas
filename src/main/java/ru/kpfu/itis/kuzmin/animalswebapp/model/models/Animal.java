package ru.kpfu.itis.kuzmin.animalswebapp.model.models;

public class Animal {
    private Integer id;
    private String name;
    private String description;
    private String image;
    private String enName;
    private Integer likes;
    public Animal(Integer id, String name, String description, String image, String enName, Integer likes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.enName = enName;
        this.likes = likes;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
