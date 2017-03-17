package com.company.recipes.model;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public BaseEntity(){
        id = null;
    }
}
