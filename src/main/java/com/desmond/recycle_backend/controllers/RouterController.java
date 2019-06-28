package com.desmond.recycle_backend.controllers;

import com.desmond.recycle_backend.helper.Constant;
import com.desmond.recycle_backend.repository.BookRepository;
import com.desmond.recycle_backend.repository.NeedRepository;
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
    private final BookRepository bookRepository;
    private final NeedRepository needRepository;
    private UserController userController;
    private BookController bookController;
    private NeedController needController;

    @Autowired
    public RouterController(UserRepository userRepository, BookRepository bookRepository, NeedRepository needRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.needRepository = needRepository;
        userController = new UserController(userRepository);
        needController = new NeedController(userRepository, needRepository);
        bookController = new BookController(bookRepository, userRepository, needRepository);
    }

    @RequestMapping(value = "/session", method = RequestMethod.POST)
    public Map login(HttpServletRequest request) {
        try {
            return userController.login(GlobalFunction.request2Map(request));
        } catch(Exception e) {
            return Constant.responseWhenError;
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Map register(HttpServletRequest request) {
        try {
            return userController.register(GlobalFunction.request2Map(request));
        } catch (Exception e) {
            return Constant.responseWhenError;
        }
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public Map addBook(HttpServletRequest request) {
        try {
            return bookController.addBook(GlobalFunction.request2Map(request));
        } catch (Exception e) {
            return Constant.responseWhenError;
        }
    }

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public Map getBook(HttpServletRequest request){
        try {
            return bookController.getAllBook(GlobalFunction.request2Map(request));
        } catch (Exception e) {
            e.printStackTrace();
            return Constant.responseWhenError;
        }
    }

    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    public Map buyBook(HttpServletRequest request) {
        try {
            return bookController.buyBook(GlobalFunction.request2Map(request));
        } catch (Exception e) {
            return Constant.responseWhenError;
        }
    }

    @RequestMapping(value = "/need", method = RequestMethod.GET)
    public Map getNeed(HttpServletRequest request) {
        try{
            return needController.getNeed(GlobalFunction.request2Map(request));
        } catch (Exception e) {
            return Constant.responseWhenError;
        }
    }

    @RequestMapping(value = "/need", method = RequestMethod.POST)
    public Map addNeed(HttpServletRequest request) {
        try {
            return needController.addNeed(GlobalFunction.request2Map(request));
        } catch (Exception e) {
            return Constant.responseWhenError;
        }
    }
}
