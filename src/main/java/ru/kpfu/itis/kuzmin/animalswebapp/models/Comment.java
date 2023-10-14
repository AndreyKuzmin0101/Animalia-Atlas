package ru.kpfu.itis.kuzmin.animalswebapp.models;

import java.sql.Timestamp;

public class Comment {
    private Integer id;
    private Integer userId;
    private String content;
    private Timestamp dateSend;
    private Integer animalId;

    public Comment(Integer id, Integer userId, String content, Timestamp dateSend, Integer animalId) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.dateSend = dateSend;
        this.animalId = animalId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getDateSend() {
        return dateSend;
    }

    public void setDateSend(Timestamp dateSend) {
        this.dateSend = dateSend;
    }

    public Integer getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Integer animalId) {
        this.animalId = animalId;
    }
}
