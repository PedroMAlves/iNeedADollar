package org.academiadecodigo.bootcamp8.shared.message;

import java.io.Serializable;

/**
 * Created by Prashanta on 14/07/17.
 */
public class DualContainer implements Serializable {

    private static final long serialVersionUID = 10L;
    private String name;
    private String request;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
