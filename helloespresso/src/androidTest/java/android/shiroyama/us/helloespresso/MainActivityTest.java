package android.shiroyama.us.helloespresso;


import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void TextViewに表示されている文字列を検証するテストコードを書こう() {
        onView(withId(R.id.text)).check(ViewAssertions.matches(withText("Hello World!")));
    }

    @Test
    public void CLICKとかかれたボタンを押してTextViewの文字列が変更するテストコードを書こう() {
        onView(allOf(withId(R.id.button), withText("CLICK"))).perform(click());
        onView(withId(R.id.text)).check(ViewAssertions.matches(withText("CLICKED!")));
    }

}
