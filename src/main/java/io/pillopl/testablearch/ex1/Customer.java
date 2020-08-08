package io.pillopl.testablearch.ex1;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {

    @Id
    private Long id;

    private String name;

    private int numberOfRentals;

    Customer() {
    }

    Customer(Long id, String name, int numberOfRentals) {
        this.id = id;
        this.name = name;
        this.numberOfRentals = numberOfRentals;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfRentals(int numberOfRentals) {
        this.numberOfRentals = numberOfRentals;
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public int getNumberOfRentals() {
        return numberOfRentals;
    }

//    good practise of Command Query Separation (not CQRS - command query responsibility segregation  btw)
//     here boolean just signifies that the command was succesfully executed or not so don't be dogmatic about it being void
    public boolean rentAnotherCar() {
        if (getNumberOfRentals() < 3) {
            this.setNumberOfRentals(numberOfRentals+1);
            return true;
        }
        return false;
    }


}
