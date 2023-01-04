package kz.project1.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.*;

public class Person {
    private int person_id;
    @NotEmpty(message = "Required Field")
    private String name;
    @NotEmpty(message = "Required Field")
    private String surname;
    @Min(value = 1900, message = "Incorrect data")
    private int year;

    public Person() {
    }

    public Person(int id, String name, String surname, int birthYear) {
        this.person_id = id;
        this.name = name;
        this.surname = surname;
        this.year = birthYear;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPerson_id() {
        return this.person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String toString() {
        return Integer.toString(person_id) + ' ' + this.name + ' ' + this.surname;
    }

}
