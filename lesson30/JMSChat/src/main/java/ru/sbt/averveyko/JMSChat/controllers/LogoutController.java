package ru.sbt.averveyko.JMSChat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sbt.averveyko.JMSChat.service.ChatService;

@Controller
public class LogoutController {
    private final ChatService chatService;

    @Autowired
    public LogoutController(ChatService chatService) {
        this.chatService = chatService;
    }

    @RequestMapping("/logout")
    public String logout(@RequestParam(value = "userName") String userName) {
        chatService.remove(userName);
        return "redirect:/login";
    }
}
