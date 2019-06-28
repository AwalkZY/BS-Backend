package com.desmond.recycle_backend.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    public User(String name, String email, String password, String token) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.token = token;
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

    public Map toMap() {
        Map<String, String> result = new HashMap<>();
        result.put("name",this.name);
        result.put("email", this.email);
        result.put("avatar", this.avatar);
        result.put("id", String.valueOf(this.id));
        return result;
    }
}
