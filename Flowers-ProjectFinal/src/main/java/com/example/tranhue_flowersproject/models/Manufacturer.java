package com.example.tranhue_flowersproject.models;

public class Manufacturer {
    private int id;
    private String name_mf;
    public Manufacturer(int id, String name_mf) {
        this.id = id;
        this.name_mf = name_mf;
    }

    public Manufacturer(int id) {
        this.id = id;
    }

    public Manufacturer(String name_mf){

        this.name_mf = name_mf;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getName_mf() {

        return name_mf;
    }

    public void setName_mf(String name_mf) {

        this.name_mf = name_mf;
    }


}
