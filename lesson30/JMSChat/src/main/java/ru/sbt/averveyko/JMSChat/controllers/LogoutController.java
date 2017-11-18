package ru.sbt.averveyko.JMSChat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sbt.averveyko.JMSChat.service.UserService;

@Controller
public class LogoutController {
    private final UserService userService;

    @Autowired
    public LogoutController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/logout")
    public String logout(@RequestParam(value = "userName") String userName) {
        userService.remove(userName);
        return "redirect:/login";
    }
}
