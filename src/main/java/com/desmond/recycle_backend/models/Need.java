package com.desmond.recycle_backend.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "need")
public class Need {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double price;
    private String tags;
    private long buyer;
    private Long book_id;
    private Timestamp created_at;
    private Timestamp updated_at;

    public Need() {
        super();
    }

    public Need(String name, double price, String tags, long buyer) {
        this.name = name;
        this.price = price;
        this.tags = tags;
        this.buyer = buyer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public long getBuyer() {
        return buyer;
    }

    public void setBuyer(long buyer) {
        this.buyer = buyer;
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

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }
}
