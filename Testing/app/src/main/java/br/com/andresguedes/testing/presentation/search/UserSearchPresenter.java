package br.com.andresguedes.testing.presentation.search;

import java.util.List;

import br.com.andresguedes.testing.data.UserRepository;
import br.com.andresguedes.testing.data.remote.model.User;
import br.com.andresguedes.testing.presentation.base.BasePresenter;
import rx.Scheduler;
import rx.Subscriber;

/**
 * Created by andreguedes on 13/09/17.
 */

public class UserSearchPresenter extends BasePresenter<UserSearchContract.View> implements UserSearchContract.Presenter {

    private final Scheduler mainScheduler, ioScheduler;
    private UserRepository userRepository;

    public UserSearchPresenter(Scheduler mainScheduler, Scheduler ioScheduler, UserRepository userRepository) {
        this.mainScheduler = mainScheduler;
        this.ioScheduler = ioScheduler;
        this.userRepository = userRepository;
    }

    @Override
    public void search(String term) {
        checkViewAttached();
        getView().showLoading();
        addSubscription(userRepository.searchUsers(term).subscribeOn(ioScheduler).observeOn(mainScheduler)
                .subscribe(new Subscriber<List<User>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideLoading();
                        getView().showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<User> users) {
                        getView().hideLoading();
                        getView().showSearchResults(users);
                    }
                }));
    }

}