package ru.kpfu.itis.kuzmin.animalswebapp.dto;

public class UserDTO {
    private String firstName;
    private String lastName;
    private String login;
    private Integer age;
    private String email;

    public UserDTO(String firstName, String lastName, String login, Integer age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.age = age;
        this.email = email;
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
