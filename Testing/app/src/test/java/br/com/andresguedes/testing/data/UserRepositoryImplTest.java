package br.com.andresguedes.testing.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.andresguedes.testing.data.remote.GithubUserRestService;
import br.com.andresguedes.testing.data.remote.model.User;
import br.com.andresguedes.testing.data.remote.model.UsersList;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by andreguedes on 13/09/17.
 */
public class UserRepositoryImplTest {

    private static final String ANDRE_GUEDES = "andresguedes";
    private static final String ROBSON_MOREIRA = "robixnai";

    @Mock
    GithubUserRestService githubUserRestService;

    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userRepository = new UserRepositoryImpl(githubUserRestService);
    }

    @Test
    public void searchUsers_200OkResponse_InvokeCorrectApiCalls() {
        when(githubUserRestService.searchGithubUsers(anyString())).thenReturn(Observable.just(githubUserList()));
        when(githubUserRestService.getUser(anyString())).thenReturn(Observable.just(user1FullDetails()), Observable.just(user2FullDetails()));

        // When
        TestSubscriber<List<User>> subscriber = new TestSubscriber<>();
        userRepository.searchUsers(ANDRE_GUEDES).subscribe(subscriber);

        // Then
        subscriber.awaitTerminalEvent();
        subscriber.assertNoErrors();

        List<List<User>> onNextEvents = subscriber.getOnNextEvents();
        List<User> users = onNextEvents.get(0);

        Assert.assertEquals(ANDRE_GUEDES, users.get(0).getLogin());
        Assert.assertEquals(ROBSON_MOREIRA, users.get(1).getLogin());

        verify(githubUserRestService).searchGithubUsers(ANDRE_GUEDES);
        verify(githubUserRestService).getUser(ANDRE_GUEDES);
        verify(githubUserRestService).getUser(ROBSON_MOREIRA);
    }

    @Test
    public void searchUsers_IOExceptionThenSuccess_SearchUsersRetried() {
        when(githubUserRestService.searchGithubUsers(anyString())).thenReturn(getIOExceptionError(), Observable.just(githubUserList()));
        when(githubUserRestService.getUser(anyString())).thenReturn(Observable.just(user1FullDetails()), Observable.just(user2FullDetails()));

        // When
        TestSubscriber<List<User>> subscriber = new TestSubscriber<>();
        userRepository.searchUsers(ANDRE_GUEDES).subscribe(subscriber);

        // Then
        subscriber.awaitTerminalEvent();
        subscriber.assertNoErrors();

        verify(githubUserRestService, times(2)).searchGithubUsers(ANDRE_GUEDES);
        verify(githubUserRestService).getUser(ANDRE_GUEDES);
        verify(githubUserRestService).getUser(ROBSON_MOREIRA);
    }

    @Test
    public void searchUsers_GetUserIOExceptionThenSuccess_SearchUsersRetried() {
        //Given
        when(githubUserRestService.searchGithubUsers(anyString())).thenReturn(Observable.just(githubUserList()));
        when(githubUserRestService.getUser(anyString())).thenReturn(getIOExceptionError(), Observable.just(user1FullDetails()), Observable.just(user2FullDetails()));

        //When
        TestSubscriber<List<User>> subscriber = new TestSubscriber<>();
        userRepository.searchUsers(ANDRE_GUEDES).subscribe(subscriber);

        //Then
        subscriber.awaitTerminalEvent();
        subscriber.assertNoErrors();

        verify(githubUserRestService, times(2)).searchGithubUsers(ANDRE_GUEDES);
        verify(githubUserRestService, times(2)).getUser(ANDRE_GUEDES);
        verify(githubUserRestService).getUser(ROBSON_MOREIRA);
    }

    @Test
    public void searchUsers_OtherHttpError_SearchTerminatedWithError() {
        //Given
        when(githubUserRestService.searchGithubUsers(anyString())).thenReturn(get403ForbiddenError());

        //When
        TestSubscriber<List<User>> subscriber = new TestSubscriber<>();
        userRepository.searchUsers(ANDRE_GUEDES).subscribe(subscriber);

        //Then
        subscriber.awaitTerminalEvent();
        subscriber.assertError(HttpException.class);

        verify(githubUserRestService).searchGithubUsers(ANDRE_GUEDES);

        verify(githubUserRestService, never()).getUser(ANDRE_GUEDES);
        verify(githubUserRestService, never()).getUser(ROBSON_MOREIRA);
    }

    private UsersList githubUserList() {
        User user = new User();
        user.setLogin(ANDRE_GUEDES);

        User user2 = new User();
        user2.setLogin(ROBSON_MOREIRA);

        List<User> githubUsers = new ArrayList<>();
        githubUsers.add(user);
        githubUsers.add(user2);

        UsersList usersList = new UsersList();
        usersList.setItems(githubUsers);
        return usersList;
    }

    private User user1FullDetails() {
        User user = new User();
        user.setLogin(ANDRE_GUEDES);
        user.setName("Andre Guedes");
        user.setAvatarUrl("avatar_url");
        user.setBio("Bio1");
        return user;
    }

    private User user2FullDetails() {
        User user = new User();
        user.setLogin(ROBSON_MOREIRA);
        user.setName("Robson Moreira");
        user.setAvatarUrl("avatar_url");
        user.setBio("Bio2");
        return user;
    }

    private Observable getIOExceptionError() {
        return Observable.error(new IOException());
    }

    private Observable<UsersList> get403ForbiddenError() {
        return Observable.error(new HttpException(Response.error(403, ResponseBody.create(MediaType.parse("application/json"), "Forbidden"))));
    }

}