package ru.sbt.averveyko.JMSChat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sbt.averveyko.JMSChat.service.UserService;

import java.util.List;

@Controller
public class ChatController {
    private final UserService userService;

    @Autowired
    public ChatController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String chatPage(@RequestParam(value="userName") String userName, Model model) {
        System.out.println("[chat] userName=" + userName);

        model.addAttribute("userName", userName);

        List<String> messages = userService.getMessages(userName);
        System.out.println("messages:");
        messages.forEach(System.out::println);
        model.addAttribute("messages", messages);
        return "chatPage";
    }
}
