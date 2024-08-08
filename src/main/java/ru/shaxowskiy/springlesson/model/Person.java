package ru.shaxowskiy.springlesson.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public class Person  {
    private int id;

    @NotEmpty(message = "name should be not empty")
    @Size(min = 2, max = 20, message = "name should be between 2 and 30 characters")
    private String name;

    @Min(value = 0, message = "age should be greater than 0")
    private int age;

    @NotEmpty(message = "Email is empty, please write somebody")
    @Email(message = "Email isn't valid")
    private String email;

    public Person(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
