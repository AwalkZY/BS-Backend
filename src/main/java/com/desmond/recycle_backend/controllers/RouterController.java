package com.desmond.recycle_backend.controllers;

import com.desmond.recycle_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.desmond.recycle_backend.helper.GlobalFunction;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@CrossOrigin
@RestController
public class RouterController {
    private final UserRepository userRepository;
    private UserController userController;

    @Autowired
    public RouterController(UserRepository userRepository) {
        this.userRepository = userRepository;
        userController = new UserController(userRepository);
    }

    @RequestMapping(value = "/session", method = RequestMethod.POST)
    public Map login(HttpServletRequest request) {
        return userController.login(GlobalFunction.request2Map(request));
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Map register(HttpServletRequest request) {
        System.out.println(this.userRepository);
        return userController.register(GlobalFunction.request2Map(request));
    }
}
