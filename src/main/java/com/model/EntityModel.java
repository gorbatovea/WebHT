package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EntityModel {
    @Id
    private Integer id;
    private String v;

    public EntityModel(){};

    public EntityModel(Integer id, String value){
        this.id = id;
        this.v = value;
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return v;
    }

    public void setValue(String value) {
        this.v = value;
    }
}
