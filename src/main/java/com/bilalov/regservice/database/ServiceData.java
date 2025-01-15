package com.bilalov.regservice.database;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ServiceData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;
    private String gosnomer;
    private String company;
    private String inn;
    private String place;
    private String name;
    private String comment;

    // Конструкторы, геттеры и сеттеры

    public ServiceData() {}

    public ServiceData(String number, String gosnomer, String company,
                       String inn, String place, String name, String comment) {
        this.number = number;
        this.gosnomer = gosnomer;
        this.company = company;
        this.inn = inn;
        this.place = place;
        this.name = name;
        this.comment = comment;
    }

    // Геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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