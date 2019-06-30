package com.desmond.recycle_backend.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;
    private Timestamp time;
    private long sender;
    private long receiver;

    public Message(){
        super();
    }

    public Message(String content, long sender, long receiver) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.time = new Timestamp(new Date().getTime());
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

    public long getSender() {
        return sender;
    }

    public void setSender(long sender) {
        this.sender = sender;
    }

    public long getReceiver() {
        return receiver;
    }

    public void setReceiver(long receiver) {
        this.receiver = receiver;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> ans = new HashMap<>();
        ans.put("id",this.id);
        ans.put("sender", this.sender);
        ans.put("receiver", this.receiver);
        ans.put("content", this.content);
        return ans;
    }
}
