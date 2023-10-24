package ru.kpfu.itis.kuzmin.animalswebapp.dto;

public class AnimalDTO {
    private String name;
    private String description;
    private String image;
    private String enName;

    public AnimalDTO(String name, String description, String image, String enName) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.enName = enName;
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

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }
}
