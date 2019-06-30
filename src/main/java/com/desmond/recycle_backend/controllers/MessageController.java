package com.desmond.recycle_backend.controllers;

import com.desmond.recycle_backend.models.Message;
import com.desmond.recycle_backend.models.Response;
import com.desmond.recycle_backend.models.User;
import com.desmond.recycle_backend.repository.MessageRepository;
import com.desmond.recycle_backend.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.desmond.recycle_backend.helper.GlobalFunction.getBase64Img;

public class MessageController {
    private MessageRepository messageRepository;
    private UserRepository userRepository;

    MessageController(UserRepository userRepository, MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    Map getAllMsg(Map<String, String[]> request){
        Map<String, Object> data = new HashMap<>();
        List<Message> messages = this.messageRepository.findAll();
        List<Map<String, Object>> ans = new ArrayList<>();
        messages.forEach((message -> {
            Map<String, Object> messageMap = message.toMap();
            User sender = userRepository.findById(message.getSender()).get();
            User receiver = userRepository.findById(message.getReceiver()).get();
            long user = userRepository.findByToken(request.get("token")[0]).getId();
            if (user != sender.getId() && user != receiver.getId()) return;
            messageMap.put("senderAvatar", getBase64Img(sender.getAvatar()));
            messageMap.put("senderName", sender.getName());
            messageMap.put("receiverAvatar", getBase64Img(receiver.getAvatar()));
            messageMap.put("receiverName", receiver.getName());
            messageMap.put("direction", user == sender.getId()); //true: 我是发送者，false: 我是接受者
            ans.add(messageMap);
        }));
        data.put("info", ans);
        return new Response(data, "", 200).toMap();
    }

    Map addMsg(Map<String, String[]> request) {
        String receiverName = request.get("receiver")[0];
        User receiver = userRepository.findByName(receiverName);
        User sender = userRepository.findByToken(request.get("token")[0]);
        String content = request.get("content")[0];
        Message message = new Message(content, sender.getId(), receiver.getId());
        messageRepository.save(message);
        return new Response(null, "", 200).toMap();
    }
}
