package ru.sbt.averveyko.chatserver;

import ru.sbt.averveyko.api.ChatCommand;
import ru.sbt.averveyko.api.ChatPacket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class MessageList {
    private Map<String, List<ChatPacket>> messages = new ConcurrentHashMap<>();

    public void addMessage(final ChatPacket chatPacket) {
        if (messages.containsKey(chatPacket.getReceiver())) {
            messages.get(chatPacket.getReceiver()).add(chatPacket);
            return;
        }
        List<ChatPacket> msgList = new CopyOnWriteArrayList<>();
        msgList.add(chatPacket);
        messages.put(chatPacket.getReceiver(), msgList);
    }

    public List<ChatPacket> getMessages(final String login) {
        List<ChatPacket> msgList = new ArrayList<>();
        if (messages.containsKey(login) && messages.get(login).size() > 0) {
            msgList.addAll(messages.get(login));
            messages.get(login).clear();
            return msgList;
        }
        ChatPacket noMessages = new ChatPacket(
                ChatCommand.MSG,
                "Server",
                login,
                "No message for you");
        msgList.add(noMessages);
        return msgList;
    }
}
