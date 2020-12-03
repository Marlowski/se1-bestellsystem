package datamodel;

import system.Components;

public class Customer {
    private final String id;
    private String firstName;
    private String lastName;
    private String contact;

    protected Customer(String id, String name, String contact) {
        this.id = id;
        if(contact == null) this.contact = "";
        else this.contact = contact;
        this.firstName = "";
        if(name == null) this.lastName = "";
        else this.lastName = name;
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
        if(firstName == null) this.firstName = "";
        else this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if(lastName == null) this.lastName = "";
        else this.lastName = lastName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        if(contact == null) this.contact = "";
        else this.contact = contact;
    }
}
