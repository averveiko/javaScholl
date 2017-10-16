package ru.sbt.averveyko.api;

import java.io.Serializable;

public class ChatPacket implements Serializable {
    ChatCommand cmd;    // Команда
    String sender;      // Отправитель сообщения
    String receiver;    // Получатель сообщения
    String msg;         // Текст сообщения

    public ChatPacket(ChatCommand cmd, String sender, String receiver, String msg) {
        this.cmd = cmd;
        this.sender = sender;
        this.receiver = receiver;
        this.msg = msg;
    }

    public ChatCommand getCmd() {
        return cmd;
    }

    public void setCmd(ChatCommand cmd) {
        this.cmd = cmd;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ChatPacket{" +
                "cmd=" + cmd +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
