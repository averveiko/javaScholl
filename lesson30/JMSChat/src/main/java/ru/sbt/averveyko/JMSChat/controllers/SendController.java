package ru.sbt.averveyko.JMSChat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sbt.averveyko.JMSChat.service.MessageProducerService;

@Controller
public class SendController {
    private final MessageProducerService messageProducerService;

    @Autowired
    public SendController(MessageProducerService messageProducerService) {
        this.messageProducerService = messageProducerService;
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String send(@RequestParam(value = "userName") String userName, @RequestParam(value = "message") String message) {
        System.out.println("[send] userName=" + userName + ", message=" + message);

        messageProducerService.send(userName + ": " + message);

        return "redirect:/chat?userName=" + userName;
    }
}
