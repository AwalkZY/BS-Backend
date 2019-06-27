package com.desmond.recycle_backend.controllers;

import com.desmond.recycle_backend.helper.GlobalFunction;
import com.desmond.recycle_backend.models.Response;
import com.desmond.recycle_backend.models.User;
import com.desmond.recycle_backend.repository.UserRepository;
import java.util.HashMap;
import java.util.Map;

public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Map login(Map<String, String[]> request) {
        String username = request.getOrDefault("username", new String[]{""})[0];
        String password = request.getOrDefault("password", new String[]{""})[0];
        Map<String, Object> data = new HashMap<>();
        User user = this.userRepository.findByName(username);
        if (user != null && user.getPassword().equals(GlobalFunction.encrypt("SHA1", password))) {
            data.put("token",user.getToken());
            return new Response(data, "", 200).toMap();
        }
        return new Response(data, "您输入的用户名或密码有误，请重新输入", 401).toMap();
    }

    public Map register(Map<String, String[]> request) {
        String username = request.getOrDefault("username", new String[]{""})[0];
        String password = request.getOrDefault("password", new String[]{""})[0];
        String email = request.getOrDefault("email", new String[]{""})[0];
        Map<String, Object> data = new HashMap<>();
        int duplication = this.userRepository.countByEmail(email) + 10 * this.userRepository.countByName(username);
        if (duplication == 0) {
            String token = GlobalFunction.encrypt("MD5", email);
            this.userRepository.save(new User(username, email, GlobalFunction.encrypt("SHA1", password), token));
            return new Response(data, "", 200).toMap();
        }
        else {
            return new Response(data, duplication < 10 ? "您的邮箱已被注册，请重新尝试。" : "您输入的用户名已被占用，请重新尝试。", 400).toMap();
        }
    }

    public Map editInfo(Map<String, String[]> request) {
        return new HashMap();
    }
}
