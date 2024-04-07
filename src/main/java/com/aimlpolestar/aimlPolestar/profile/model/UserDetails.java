package com.aimlpolestar.aimlPolestar.profile.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;


@Data
@Entity
@Table(name = "users")
public class UserDetails {

    public UserDetails () {
        this.setActive("Y");
    }

    // Add three more fields
    // id and relation and parent_user
    @Id
    @Column (name = "id", unique = true)
    private String id;

    private String relation;




    private String parent_user;

    @Column(name = "mobno", unique = true)
    private String mobno;

    @Column(name = "primaryuser")
    private int primaryuser;

    @Column(name = "fname")
    private String fname;

    @Column(name = "lname")
    private String lname;

    @Column(name = "age")
    private int age;

    @Column(name = "sex")
    private String sex;

    @Column(name = "passwd")
    private String passwd;

    @Column (name = "active")
    private String active = "Y";


}
