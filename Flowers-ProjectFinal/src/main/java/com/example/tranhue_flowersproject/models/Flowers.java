package com.example.tranhue_flowersproject.models;

public class Flowers {
    private int id;
    private String name;
    private String image;
    private Manufacturer name_mf;
    private int price;
    private int quantity;
    private String description;

    public Flowers (int id, String name, String image, Manufacturer name_mf, int price, int quantity, String description) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.name_mf = name_mf;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }
    public Flowers (String name, String image, Manufacturer name_mf, int price,int quantity, String description) {
        this.name = name;
        this.image = image;
        this.name_mf = name_mf;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public Flowers(int id, String name, String image, Manufacturer name_mf, String author, int price, int quantity, String description) {
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }
    public Manufacturer getName_mf() {
        return name_mf;
    }

//    public void setName_mf(Class studentClass) {
//
//        this.name_mf = name_mf;
//    }


    public int getPrice() {

        return price;
    }

    public void setPrice(int price) {

        this.price = price;
    }
    public int getQuantity() {

        return quantity;
    }

    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }
    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

}
