package com.bilalov.regservice.models;

public class Car {
    private int id;
    private String number;
    private String gosnomer;
    private String company;
    private String inn;
    private String place;
    private String name;
    private String comment;


    public Car(int id, String number, String gosnomer, String company, String inn, String place, String name, String comment) {
        this.id = id;
        this.number = number;
        this.gosnomer = gosnomer;
        this.company = company;
        this.inn = inn;
        this.place = place;
        this.name = name;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getGosnomer() {
        return gosnomer;
    }

    public void setGosnomer(String gosnomer) {
        this.gosnomer = gosnomer;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}