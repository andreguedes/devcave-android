package br.com.andresguedes.testing.presentation.search;

import java.util.List;

import br.com.andresguedes.testing.data.remote.model.User;
import br.com.andresguedes.testing.presentation.base.MvpPresenter;
import br.com.andresguedes.testing.presentation.base.MvpView;

/**
 * Created by andreguedes on 13/09/17.
 */

public interface UserSearchContract {

    interface View extends MvpView {
        void showSearchResults(List<User> githubUserList);

        void showError(String message);

        void showLoading();

        void hideLoading();
    }

    interface Presenter extends MvpPresenter<View> {
        void search(String term);
    }

}