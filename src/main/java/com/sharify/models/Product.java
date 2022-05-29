package com.sharify.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String category;
    @NotBlank(message = "Please input the title")
    @Size( max = 30, min=2, message = "The title length need to be from 5 to 300 characters")
    private String title;
    @PositiveOrZero(message = "Price cannot be lower than 0")
    @NotNull(message = "Price field cannot be empty")
    private Float price;
    @PositiveOrZero(message = "Bail cannot be lower than 0")
    @NotNull(message = "Bail field cannot be empty")
    private Float bail;

    @Size( max = 30, min=2, message = "The name length need to be from 2 to 30 characters")
    @NotBlank(message = "Please input your name")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must consist only from latin characters")
    private String seller;
    @NotBlank(message = "Please input your address")
    private String address;
    @Pattern(regexp = "^[a-zA-Z]+$", message = "City must consist only from latin characters")
    @NotBlank(message = "Please input your city")
    private String city;
    @NotNull(message = "Please input your phone number")
    private Integer phoneNumber;
    @NotBlank(message = "Please input your email")
    @Email(message = "Please input your email in valid form example@email.com")
    private String email;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Product() {
    }

    public Product(String category, String title, Float price,
                   Float bail, String seller, String address,
                   String city, Integer phoneNumber, String email) {
        this.category = category;
        this.title = title;
        this.price = price;
        this.bail = bail;
        this.seller = seller;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Float getBail() {
        return bail;
    }

    public void setBail(Float bail) {
        this.bail = bail;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
