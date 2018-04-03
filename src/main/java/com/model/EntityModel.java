package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String k;
    private String v;

    public EntityModel(){};

    public EntityModel(String key, String value){
        this.id = id;
        this.k = key;
        this.v = value;
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return k;
    }

    public void setKey(String id) {
        this.k = id;
    }

    public String getValue() {
        return v;
    }

    public void setValue(String value) {
        this.v = value;
    }
}
