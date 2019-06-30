package com.desmond.recycle_backend.models;

import org.apache.commons.codec.binary.Base64;

import javax.persistence.*;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.desmond.recycle_backend.helper.GlobalFunction.getBase64Img;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String ISBN;
    private String description;
    private String image;
    private double original;
    private double current;
    private String tags;
    private Long buyer;
    private long seller;
    private String deliver;
    private Timestamp created_at;
    private Timestamp updated_at;

    public Book(){
        super();
    }

    public Book(String name, String ISBN, String description, String image, double original, double current, String tags, long seller, String deliver) {
        this.name = name;
        this.ISBN = ISBN;
        this.description = description;
        this.image = image;
        this.original = original;
        this.current = current;
        this.tags = tags;
        this.seller = seller;
        this.deliver = deliver;
        this.buyer = 0L;
        this.created_at = this.updated_at = new Timestamp(new Date().getTime());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getOriginal() {
        return original;
    }

    public void setOriginal(double original) {
        this.original = original;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public Long getBuyer() {
        return buyer;
    }

    public void setBuyer(Long buyer) {
        this.buyer = buyer;
    }

    public long getSeller() {
        return seller;
    }

    public void setSeller(long seller) {
        this.seller = seller;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getDeliver() {
        return deliver;
    }

    public void setDeliver(String deliver) {
        this.deliver = deliver;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("name",this.name);
        result.put("ISBN",this.ISBN);
        result.put("description",this.description);
        result.put("original",this.original);
        result.put("current",this.current);
        result.put("tags",this.tags);
        result.put("buyer",this.buyer);
        result.put("seller",this.seller);
        result.put("deliver",this.deliver);
        result.put("id", this.id);
        result.put("image", getBase64Img(this.image));
        return result;
    }
}
