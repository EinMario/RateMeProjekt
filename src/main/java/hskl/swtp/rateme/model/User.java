package hskl.swtp.rateme.model;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

public class User {
    private int id;
    private String username;
    private String mail;
    private String firstname;
    private String lastname;
    private String street;
    private String streetNr;
    private String zip;
    private String city;
    private byte[] password;
    private Timestamp create;
    private Timestamp modify;

    public User(int id, String username, String mail, String firstname, String lastname, String street, String streetNr, String zip, String city, byte[] password) {
        this.setId(id);
        this.setUsername(username);
        this.mail = mail;
        this.firstname = firstname;
        this.lastname = lastname;
        this.street = street;
        this.streetNr = streetNr;
        this.zip = zip;
        this.city = city;
        this.password = password;

        Date d = new Date();
        Timestamp s = new Timestamp(d.getTime());

        this.setCreate(s);
        this.setModify(s);
    }

    public User(int id, String username, String mail, String firstname, String lastname, String street, String streetNr, String zip, String city, byte[] password, Timestamp create, Timestamp modify) {
        this.setId(id);
        this.setUsername(username);
        this.mail = mail;
        this.firstname = firstname;
        this.lastname = lastname;
        this.street = street;
        this.streetNr = streetNr;
        this.zip = zip;
        this.city = city;
        this.password = password;
        this.setCreate(create);
        this.setModify(modify);
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getStreetNr() {
        return streetNr;
    }

    public void setStreetNr(String streetNr) {
        this.streetNr = streetNr;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public Timestamp getCreate() {
        return create;
    }


    public void setCreate(Timestamp create) {
        this.create = create;
    }


    public Timestamp getModify() {
        return modify;
    }


    public void setModify(Timestamp modify) {
        this.modify = modify;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", mail='" + mail + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", street='" + street + '\'' +
                ", streetNr='" + streetNr + '\'' +
                ", zip='" + zip + '\'' +
                ", city='" + city + '\'' +
                ", password=" + Arrays.toString(password) +
                ", create=" + create +
                ", modify=" + modify +
                '}';
    }
}
