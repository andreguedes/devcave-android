package br.com.andresguedes.mvpwithjunit;

/**
 * Created by andreguedes on 17/07/17.
 */

public class LoginService {

    public boolean login(String username, String password) {
        return username.equalsIgnoreCase("user") && password.equalsIgnoreCase("pass");
    }

}