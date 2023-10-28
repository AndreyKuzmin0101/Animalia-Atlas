package ru.kpfu.itis.kuzmin.animalswebapp.model.models;

public class Category {
    private Integer id;
    private String name;
    private String enName;

    public Category(Integer id, String name, String enName) {
        this.id = id;
        this.name = name;
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

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }
}
