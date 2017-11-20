package ru.sbt.averveyko.JMSChat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sbt.averveyko.JMSChat.service.ChatService;

@Controller
public class LoginController {
    private final ChatService chatService;

    @Autowired
    public LoginController(ChatService chatService) {
        this.chatService = chatService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "userName") String userName, Model model) {

        if (! chatService.containsUser(userName)) {
            chatService.addUser(userName);

            return "redirect:/chat?userName=" + userName;
        }

        model.addAttribute("errorMessage", userName + " already in use, select another.");
        return  "loginPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "loginPage";
    }

    @RequestMapping("/logout")
    public String logout(@RequestParam(value = "userName") String userName) {
        chatService.removeUser(userName);
        return "redirect:/login";
    }
}
