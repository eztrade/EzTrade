package com.eztrade.eztrade.dataModel;

/**
 * Created by ayog on 1/14/15.
 */
public class Seller {

    private String id;
    private String name;
    private String email;
    private String phone;
    private String zip;

    public Seller(String id, String name, String email, String phone, String zip) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.zip = zip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
