package br.com.andresguedes.presentation.search;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.view.KeyEvent;

import org.junit.Rule;
import org.junit.Test;

import br.com.andresguedes.testing.R;
import br.com.andresguedes.testing.data.remote.MockGithubUserRestServiceImpl;
import br.com.andresguedes.testing.presentation.search.UserSearchActivity;
import rx.Observable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

/**
 * Created by andreguedes on 14/09/17.
 */

public class UserSearchActivityTest {

    @Rule
    public ActivityTestRule<UserSearchActivity> testRule = new ActivityTestRule<>(UserSearchActivity.class);

    @Test
    public void searchActivity_onLaunch_HintTextDisplayed() {
        onView(withText("Start typing to search")).check(matches(isDisplayed()));
    }

    @Test
    public void searchText_ReturnsCorrectlyFromWebService_DisplaysResult() {
        // When
        onView(allOf(withId(R.id.menu_search), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))).perform(click());
        onView(withId(R.id.search_src_text)).perform(typeText("teste"), pressKey(KeyEvent.KEYCODE_ENTER));

        // Then
        onView(withId(R.id.text_view_error_msg)).check(matches(not(isDisplayed())));
        onView(withText("andresguedes - Andre Guedes")).check(matches(isDisplayed()));
        onView(withText("robxinai - Robson Moreira")).check(matches(isDisplayed()));
    }

    @Test
    public void searchText_ServiceCallFails_DisplayError() {
        String errorMsg = "Server error";
        MockGithubUserRestServiceImpl.setDummyGithubSearchResult(Observable.error(new Exception(errorMsg)));

        onView(allOf(withId(R.id.menu_search), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))).perform(click());
        onView(withId(R.id.search_src_text)).perform(typeText("teste"), pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withText(errorMsg)).check(matches(isDisplayed()));
    }

}