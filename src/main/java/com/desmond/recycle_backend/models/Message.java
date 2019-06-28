package com.desmond.recycle_backend.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;
    private Timestamp time;
    private int sender;
    private int receiver;

    public Message(){
        super();
    }

    public Message(String content, int sender, int receiver) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }
}
