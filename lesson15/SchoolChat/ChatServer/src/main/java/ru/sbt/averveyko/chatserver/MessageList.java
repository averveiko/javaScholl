package ru.sbt.averveyko.chatserver;

import ru.sbt.averveyko.api.ChatPacket;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MessageList {
    private List<ChatPacket> msgList = new CopyOnWriteArrayList<>();

    public void addMessage(ChatPacket chatPacket) {
        msgList.add(chatPacket);
    }

    public List<ChatPacket> getMessages(String login) {
        ArrayList<ChatPacket> packets = new ArrayList<>();
        for (ChatPacket chatPacket : msgList) {
            if (chatPacket.getReceiver().equals(login))
                packets.add(chatPacket);
        }
        return packets;
    }
}
