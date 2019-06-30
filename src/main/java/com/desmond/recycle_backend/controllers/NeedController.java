package com.desmond.recycle_backend.controllers;

import com.desmond.recycle_backend.models.Need;
import com.desmond.recycle_backend.models.Response;
import com.desmond.recycle_backend.models.User;
import com.desmond.recycle_backend.repository.NeedRepository;
import com.desmond.recycle_backend.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NeedController {
    private UserRepository userRepository;
    private NeedRepository needRepository;

    NeedController(UserRepository userRepository, NeedRepository needRepository) {
        this.userRepository = userRepository;
        this.needRepository = needRepository;
    }

    Map addNeed(Map<String, String[]> request){
        String name = request.get("name")[0];
        double price = Double.valueOf(request.get("price")[0]);
        long buyer = userRepository.findByToken(request.get("token")[0]).getId();
        String tags = request.get("tags")[0];
        Need need = new Need(name,price,tags,buyer);
        needRepository.save(need);
        return new Response(null, "", 200).toMap();
    }

    Map getNeed(Map<String, String[]> request){
        Map<String, Object> data = new HashMap<>();
        List<Need> needs = needRepository.findAll();
        List<Map<String, Object>> ans = new ArrayList<>();
        needs.forEach(need -> {
            Map<String, Object> needMap = need.toMap();
            User buyer = userRepository.findById(need.getBuyer()).get();
            needMap.put("buyer", buyer.getName());
            ans.add(needMap);
        });
        data.put("info", ans);
        return new Response(data, "", 200).toMap();
    }
}
