package br.com.andresguedes.testing.presentation.search;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.andresguedes.testing.data.UserRepository;
import br.com.andresguedes.testing.data.remote.model.User;
import br.com.andresguedes.testing.data.remote.model.UsersList;
import br.com.andresguedes.testing.presentation.base.BasePresenter;
import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by andreguedes on 14/09/17.
 */
public class UserSearchPresenterTest {

    private static final String ANDRE_GUEDES = "andresguedes";
    private static final String ROBSON_MOREIRA = "robixnai";

    @Mock
    UserRepository userRepository;
    @Mock
    UserSearchContract.View view;

    UserSearchPresenter userSearchPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userSearchPresenter = new UserSearchPresenter(Schedulers.immediate(), Schedulers.immediate(), userRepository);
        userSearchPresenter.attachView(view);
    }

    @Test
    public void search_ValidSearchTerm_ReturnsResults() {
        UsersList usersList = getDummyUserList();
        when(userRepository.searchUsers(anyString())).thenReturn(Observable.just(usersList.getItems()));

        userSearchPresenter.search("andresguedes");

        verify(view).showLoading();
        verify(view).hideLoading();
        verify(view).showSearchResults(usersList.getItems());
        verify(view, never()).showError(anyString());
    }

    @Test
    public void search_UserRepositoryError_ErrorMsg() {
        String errorMsg = "No internet";

        when(userRepository.searchUsers(anyString())).thenReturn(Observable.error(new IOException(errorMsg)));

        userSearchPresenter.search("bookdash");

        verify(view).showLoading();
        verify(view).hideLoading();
        verify(view, never()).showSearchResults(anyList());
        verify(view).showError(errorMsg);
    }

    @Test(expected = BasePresenter.MvpViewNotAttachedException.class)
    public void search_NotAttached_ThrowsMvpException() {
        userSearchPresenter.detachView();
        userSearchPresenter.search("test");

        verify(view, never()).showLoading();
        verify(view, never()).showSearchResults(anyList());
    }

    private UsersList getDummyUserList() {
        List<User> githubUsers = new ArrayList<>();
        githubUsers.add(user1FullDetails());
        githubUsers.add(user2FullDetails());
        return new UsersList(githubUsers);
    }

    private User user1FullDetails() {
        return new User(ANDRE_GUEDES, "Andre Guedes", "avatar_url", "Bio1");
    }

    private User user2FullDetails() {
        return new User(ROBSON_MOREIRA, "Robson Moreira", "avatar_url2", "Bio2");
    }

}