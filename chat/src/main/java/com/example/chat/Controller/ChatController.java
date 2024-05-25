package com.example.chat.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @GetMapping("/chat")
    public String chat(Model model) {
        model.addAttribute("name", "User");
        return "chat"; // returns the name of the template file (chat.html)
    }
}
