package com.example.chat.Controller;

import com.example.chat.Entity.Message;
import com.example.chat.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/save")
    public Message saveMessage(@RequestBody Message message) {
        return messageService.saveMessage(message);
    }
}