package ru.kpfu.itis.kuzmin.animalswebapp.model.dto;

public class UserDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String login;
    private Integer age;
    private String email;
    private String image;

    public UserDTO(Integer id, String firstName, String lastName, String login, Integer age, String email, String image) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.age = age;
        this.email = email;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }
    public String getImage() {
        return image;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }
}
