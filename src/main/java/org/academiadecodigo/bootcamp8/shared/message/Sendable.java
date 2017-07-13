package org.academiadecodigo.bootcamp8.shared.message;

import java.io.Serializable;



public interface Sendable<T> extends Serializable {

    MessageType getType();

    T getContent();

}
