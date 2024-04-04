package ru.kpfu.itis.kuzmin.animalswebapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
public class CommentDTO {
    private Integer userId;
    private String content;
    private Timestamp dateSend;
    private String animalEnName;
}
