package android.shiroyama.us.testhandson02;


import android.support.design.widget.TextInputLayout;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.text.TextUtils;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void Eメールパスワードともに空欄でボタンを押しエラーになるテストを書こう() {
        ViewInteraction button = onView(
                allOf(withId(R.id.email_sign_in_button), withText("Sign in or register"),
                        withParent(allOf(withId(R.id.email_login_form),
                                withParent(withId(R.id.login_form))))));
        button.perform(scrollTo(), click());

        onView(withId(R.id.email_input_layout)).check(matches(hasTextInputLayoutHintText("This field is required")));
        onView(withId(R.id.password_input_layout)).check(matches(hasTextInputLayoutHintText("This password is too short")));
    }

    @Test
    public void アットマークがない不正なEメールアドレスを入力してエラーになるテストを書こう() {
        ViewInteraction email = onView(
                withId(R.id.email));
        email.perform(scrollTo(), replaceText("foo"), closeSoftKeyboard());

        ViewInteraction password = onView(
                withId(R.id.password));
        password.perform(scrollTo(), replaceText("testtest"), closeSoftKeyboard());

        ViewInteraction button = onView(
                allOf(withId(R.id.email_sign_in_button), withText("Sign in or register"),
                        withParent(allOf(withId(R.id.email_login_form),
                                withParent(withId(R.id.login_form))))));
        button.perform(scrollTo(), click());

        onView(withId(R.id.email_input_layout)).check(matches(hasTextInputLayoutHintText("This email address is invalid")));
        onView(withId(R.id.password_input_layout)).check(matches(hasTextInputLayoutHintText("")));
    }

    @Test
    public void 四文字以下の短いパスワードを入力してエラーになるテストを書こう() {
        ViewInteraction email = onView(
                withId(R.id.email));
        email.perform(scrollTo(), replaceText("foo@example.com"), closeSoftKeyboard());

        ViewInteraction password = onView(
                withId(R.id.password));
        password.perform(scrollTo(), replaceText("test"), closeSoftKeyboard());

        ViewInteraction button = onView(
                allOf(withId(R.id.email_sign_in_button), withText("Sign in or register"),
                        withParent(allOf(withId(R.id.email_login_form),
                                withParent(withId(R.id.login_form))))));
        button.perform(scrollTo(), click());

        onView(withId(R.id.email_input_layout)).check(matches(hasTextInputLayoutHintText("")));
        onView(withId(R.id.password_input_layout)).check(matches(hasTextInputLayoutHintText("This password is too short")));
    }

    @Test
    public void Eメールパスワードともに形式は正しいが情報が間違っているためエラーになるテストを書こう() {
        ViewInteraction email = onView(
                withId(R.id.email));
        email.perform(scrollTo(), replaceText("foo@example.com"), closeSoftKeyboard());

        ViewInteraction password = onView(
                withId(R.id.password));
        password.perform(scrollTo(), replaceText("testtest"), closeSoftKeyboard());

        ViewInteraction button = onView(
                allOf(withId(R.id.email_sign_in_button), withText("Sign in or register"),
                        withParent(allOf(withId(R.id.email_login_form),
                                withParent(withId(R.id.login_form))))));
        button.perform(scrollTo(), click());

        onView(withId(R.id.email_input_layout)).check(matches(hasTextInputLayoutHintText("")));
        onView(withId(R.id.password_input_layout)).check(matches(hasTextInputLayoutHintText("This password is incorrect")));
    }

    @Test
    public void Eメールパスワードともに形式が正しく情報も合っているテストを書こう() {
        ViewInteraction email = onView(
                withId(R.id.email));
        email.perform(scrollTo(), replaceText("foo@example.com"), closeSoftKeyboard());

        ViewInteraction password = onView(
                withId(R.id.password));
        password.perform(scrollTo(), replaceText("hello"), closeSoftKeyboard());

        ViewInteraction button = onView(
                allOf(withId(R.id.email_sign_in_button), withText("Sign in or register"),
                        withParent(allOf(withId(R.id.email_login_form),
                                withParent(withId(R.id.login_form))))));
        button.perform(scrollTo(), click());

        onView(withId(R.id.email_input_layout)).check(matches(hasTextInputLayoutHintText("")));
        onView(withId(R.id.password_input_layout)).check(matches(hasTextInputLayoutHintText("")));
    }

    /**
     * {@link TextInputLayout}のエラーを検証する{@link Matcher}
     *
     * @param expectedErrorText
     * @return
     */
    public static Matcher<View> hasTextInputLayoutHintText(final String expectedErrorText) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextInputLayout)) {
                    return false;
                }

                TextInputLayout textInputLayout = (TextInputLayout) view;
                CharSequence error = textInputLayout.getError();

                if (error == null) {
                    if (TextUtils.isEmpty(expectedErrorText)) {
                        return true;
                    }
                    return false;
                }

                String hint = error.toString();

                return expectedErrorText.equals(hint);
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }
}

