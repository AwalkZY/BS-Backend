package com.desmond.recycle_backend.models;

import java.util.HashMap;
import java.util.Map;

public class Response {
    private Map data;
    private String message;
    private int status;

    public Response(Map data, String message, int status){
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public Map toMap() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", this.data);
        result.put("message", this.message);
        result.put("status", this.status);
        return result;
    }
}
