package com.example.backposturecorrector.user.profile;

public class UserProfileResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private short age;
    private short height;
    private short weight;

    public UserProfileResponse(Long id, short age, short height, short weight) {
        this.id = id;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public UserProfileResponse(Long id, String firstName, String lastName, String email, String password, short age, short height, short weight) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public short getAge() {
        return age;
    }

    public short getHeight() {
        return height;
    }

    public short getWeight() {
        return weight;
    }
}
