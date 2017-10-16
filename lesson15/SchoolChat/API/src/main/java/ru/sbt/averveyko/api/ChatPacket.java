package ru.sbt.averveyko.api;

import java.io.Serializable;

public class ChatPacket implements Serializable {
    private ChatCommand cmd;    // Команда
    private String sender;      // Отправитель сообщения
    private String receiver;    // Получатель сообщения
    private String msg;         // Текст сообщения

    public ChatPacket(final ChatCommand cmd, final String sender, final String receiver, final String msg) {
        this.cmd = cmd;
        this.sender = sender;
        this.receiver = receiver;
        this.msg = msg;
    }

    public ChatCommand getCmd() {
        return cmd;
    }

    public void setCmd(final ChatCommand cmd) {
        this.cmd = cmd;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(final String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(final String sender) {
        this.sender = sender;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(final String msg) {
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
