package ru.shaxowskiy.springlesson.people.model;

import javax.validation.constraints.*;


public class Book {
    private int id;

    @Size(min = 2, max = 30, message = "Size of title should be between 2 and 30 characters")
    private String title;

    @Size(min = 2, max = 30, message = "Size of author should be between 2 and 30 characters")
    private String author;

    @Min(value = 1900, message = "Year must be at least 1900")
    @Max(value = 2100, message = "Year must be at most 2100")
    private int publication_year;

    @NotEmpty(message = "isbn should not empty")
    @Pattern(regexp = "\\d{13}", message = "Your isbn should be have a 13 digits")
    private String isbn;

    private int person_id;

    public Book(int id, String title, String author, int year, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publication_year = year;
        this.isbn = isbn;
    }

    public Book(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(int publication_year) {
        this.publication_year = publication_year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //@UniqueElements(message = "isbn should be unique element")
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }
}
