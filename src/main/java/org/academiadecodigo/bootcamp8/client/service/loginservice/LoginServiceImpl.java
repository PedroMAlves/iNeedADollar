package org.academiadecodigo.bootcamp8.client.service.loginservice;

import org.academiadecodigo.bootcamp8.client.service.loginservice.LoginService;

/**
 * Created by Prashanta on 13/07/17.
 */
public class LoginServiceImpl implements LoginService {

    @Override
    public String getName() {
        return LoginService.class.getSimpleName();
    }

}
