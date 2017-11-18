package ru.sbt.averveyko.JMSChat.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sbt.averveyko.JMSChat.service.UserService;

import java.util.List;

@RestController()
@RequestMapping("/msg")
public class MessageRestController {

    private final UserService userService;

    @Autowired
    public MessageRestController(UserService userService) {
        this.userService = userService;
    }

    // TODO post
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<String> getMessages(@RequestParam(value = "userName") String userName) {
        System.out.println("Call rest");
        return userService.getMessages(userName);
    }
}
