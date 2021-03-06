package org.academiadecodigo.bootcamp8.client.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by codecadet on 13/07/17.
 */
public class ServiceRegistry {

    private static ServiceRegistry instance = null;
    private Map<String, Service> services;

    private ServiceRegistry() {
        services = new HashMap<>();

    }

    public static ServiceRegistry getInstance() {

        if (instance == null) {
            synchronized (ServiceRegistry.class) {
                if (instance == null) {
                    instance = new ServiceRegistry();
                }
            }
        }

        return instance;
    }

    public void addService(String name, Service service) {
        services.put(name, service);

    }

    public void removeService(String name) {
        services.remove(name);

    }

    public <T extends Service> T getService(Class<T> classCast) {
        return (T) services.get(classCast.getSimpleName());

    }

}
