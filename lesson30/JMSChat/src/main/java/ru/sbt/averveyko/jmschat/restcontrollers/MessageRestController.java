package ru.sbt.averveyko.JMSChat.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sbt.averveyko.JMSChat.service.ChatService;

import java.util.List;

@RestController
@RequestMapping("/msg")
public class MessageRestController {

    private final ChatService chatService;

    @Autowired
    public MessageRestController(ChatService chatService) {
        this.chatService = chatService;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<String> getMessages(@RequestParam(value = "userName") String userName) {
        return chatService.getMessages(userName);
    }

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public void sendMessage(@RequestParam(value = "userName") String userName, @RequestParam(value = "message") String message) {
        chatService.sendMessage(userName + ": " + message);
    }
}
