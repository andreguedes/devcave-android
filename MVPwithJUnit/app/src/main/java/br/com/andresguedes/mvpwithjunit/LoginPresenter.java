package br.com.andresguedes.mvpwithjunit;

/**
 * Created by andreguedes on 17/07/17.
 */

public class LoginPresenter {

    private LoginView view;
    private LoginService service;

    public LoginPresenter(LoginView view, LoginService service) {
        this.view = view;
        this.service = service;
    }

    public void onLoginClicked() {
        String username = view.getUsername();
        if (username.isEmpty()) {
            view.showUsernameError(R.string.username_error);
            return;
        }
        String password = view.getPassword();
        if (password.isEmpty()) {
            view.showPasswordError(R.string.password_error);
            return;
        }
        boolean login = service.login(username, password);
        if (login) {
            view.startMainActivity();
            return;
        }
        view.showLoginError(R.string.login_error);
    }

}