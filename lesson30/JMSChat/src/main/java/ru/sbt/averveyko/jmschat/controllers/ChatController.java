package ru.sbt.averveyko.jmschat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {
    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String chatPage(@RequestParam(value="userName") String userName, Model model) {

        model.addAttribute("userName", userName);
        return "chatPage";
    }
}
