package com.aimlpolestar.aimlPolestar.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserDetails {

    @Id
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
}
