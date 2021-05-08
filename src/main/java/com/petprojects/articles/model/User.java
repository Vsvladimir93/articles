package com.petprojects.articles.model;

import com.petprojects.articles.audit.Auditable;

import javax.persistence.Entity;

@Entity
public class User extends Auditable<String> {

    private String name;

    private String password;

}
