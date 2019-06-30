package com.desmond.recycle_backend.controllers;

import com.desmond.recycle_backend.helper.Constant;
import com.desmond.recycle_backend.models.Response;
import com.desmond.recycle_backend.repository.BookRepository;
import com.desmond.recycle_backend.repository.MessageRepository;
import com.desmond.recycle_backend.repository.NeedRepository;
import com.desmond.recycle_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.desmond.recycle_backend.helper.GlobalFunction;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.apache.commons.lang3.RandomStringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

@CrossOrigin
@RestController
public class RouterController {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final NeedRepository needRepository;
    private final MessageRepository messageRepository;
    private UserController userController;
    private BookController bookController;
    private NeedController needController;
    private MessageController messageController;

    @Autowired
    public RouterController(UserRepository userRepository, BookRepository bookRepository, NeedRepository needRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.needRepository = needRepository;
        this.messageRepository = messageRepository;
        userController = new UserController(userRepository);
        needController = new NeedController(userRepository, needRepository);
        bookController = new BookController(bookRepository, userRepository, needRepository);
        messageController = new MessageController(userRepository, messageRepository);
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
            e.printStackTrace();
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
            e.printStackTrace();
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

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public Map addMsg(HttpServletRequest request) {
        try {
            return messageController.addMsg(GlobalFunction.request2Map(request));
        } catch (Exception e) {
            return Constant.responseWhenError;
        }
    }

    @RequestMapping(value = "/message", method = RequestMethod.GET) 
    public Map getAllMsg(HttpServletRequest request) {
        try {
            return messageController.getAllMsg(GlobalFunction.request2Map(request));
        } catch (Exception e) {
            e.printStackTrace();
            return Constant.responseWhenError;
        }
    }

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public Map uploadFile(MultipartRequest request) {
        try {
            MultipartFile file = request.getFile("file");
            String fileName = file.getOriginalFilename();
            String newName = RandomStringUtils.randomAlphanumeric(20)+fileName.substring(fileName.lastIndexOf('.'));
            File newFile = new File("upload/"+newName);
            FileOutputStream fos = new FileOutputStream(newFile);
            fos.write(file.getBytes());
            fos.close();
            Map<String, Object> data = new HashMap<>();
            data.put("filename", newName);
            return new Response(data, "", 200).toMap();
        } catch (Exception e) {
            return Constant.responseWhenError;
        }
    }
}
