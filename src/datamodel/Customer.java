package datamodel;

import system.Components;

public class Customer {
    private final String id;
    private String firstName;
    private String lastName;
    private String contact;

    protected Customer(String id, String name, String contact) {
        this.id = id;
        this.contact = contact;
        this.firstName = "";
        this.lastName = name;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
