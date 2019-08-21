package model;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private Long id;
    private String name;
    private String surname;
    private int age;
    private List<Contact> contacts = new ArrayList<>();

    public Customer(String name, String surname) {
        this.name = name;
        this.surname = surname;

    }

    public Customer(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void addContact(Contact contact){
        this.contacts.add(contact);
    }


}
