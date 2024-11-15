package com.pluralsight.Accounts;

import com.pluralsight.Customers.Customer;
import com.pluralsight.iterfaces.IEncoded;

import java.util.UUID;

public class Account implements IEncoded {
    private String id;
    private Customer customer;

    public Account(Customer customer) {
        this.id = UUID.randomUUID().toString();
        this.customer = customer;
    }

    public Account(String id,Customer customer) {
        this.id = id;
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    @Override
    public String encodedString() {
        return id + "|" + customer.encodedString() + "\n";
    }

    @Override
    public String toString() {
        return "Account{" +
                "\nid=" + id +
                "\ncustomer=" + customer +
                "\n}";
    }
}
