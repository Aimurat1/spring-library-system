package kz.project1.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Book {
    private int book_id;
    @NotEmpty(message = "Required Field")
    private String name;
    @NotEmpty(message = "Required Field")
    private String author;
    @Min(value = 1900, message = "Incorrect data")
    private int year;
    private int person_id;

    public Book() {
    }

    public Book(int book_id, String name, String author, int year, int person_id) {
        this.book_id = book_id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.person_id = person_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getBook_id() {
        return this.book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getPerson_id() {
        return this.person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String toString() {
        return this.name + " " + this.author;
    }

}
