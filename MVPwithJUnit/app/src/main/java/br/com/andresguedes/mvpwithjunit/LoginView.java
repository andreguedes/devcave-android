package br.com.andresguedes.mvpwithjunit;

/**
 * Created by andreguedes on 17/07/17.
 */

interface LoginView {

    String getUsername();
    String getPassword();
    void showUsernameError(int resId);
    void showPasswordError(int resId);
    void startMainActivity();
    void showLoginError(int resId);

}