package org.ecuadorjug.api.impl;

import org.ecuadorjug.api.HelloService;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Kleber Ayala
 */
@ApplicationScoped
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
