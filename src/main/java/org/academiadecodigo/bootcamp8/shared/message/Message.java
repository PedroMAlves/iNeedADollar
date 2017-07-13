package org.academiadecodigo.bootcamp8.shared.message;


public class Message<T> implements Sendable<T> {

    private static final long serialVersionUID = 10L;
    private final T content;
    private MessageType type;


    public Message(MessageType type, T content) {

        this.content = content;
        this.type = type;
    }

    @Override
    public MessageType getType() {
        return type;
    }

    @Override
    public T getContent() {
        return content;
    }

}