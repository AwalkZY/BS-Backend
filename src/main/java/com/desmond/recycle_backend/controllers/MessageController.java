package com.desmond.recycle_backend.controllers;

import com.desmond.recycle_backend.models.Response;

import java.util.HashMap;
import java.util.Map;

public class MessageController {
    public Map getAllMsg(Map<String, String[]> request){
        Map<String, Object> data = new HashMap<>();

        return new Response(data, "", 200).toMap();
    }
}
