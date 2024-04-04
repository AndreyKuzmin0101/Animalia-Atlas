package ru.kpfu.itis.kuzmin.animalswebapp.model.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "en_name")
    private String enName;


    @ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER)
    private List<Animal> animals;
}
