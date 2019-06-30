package com.desmond.recycle_backend.models;

import javax.persistence.*;
import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import static com.desmond.recycle_backend.helper.GlobalFunction.getBase64Img;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String password;
    private String token;
    private String avatar;
    private Timestamp created_at;
    private Timestamp updated_at;

    public User(){
        super();
    }

    public User(String name, String email, String password, String token, String avatar) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.token = token;
        this.avatar = avatar;
        this.created_at = this.updated_at = new Timestamp(new Date().getTime());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Map toMap(){
        Map<String, Object> result = new HashMap<>();
        result.put("name",this.name);
        result.put("email", this.email);
        result.put("avatar", getBase64Img(this.avatar));
        result.put("id", this.id);
        return result;
    }
}
