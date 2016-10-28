package android.shiroyama.us.testhandson02;


import android.support.design.widget.TextInputLayout;
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

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void Eメールパスワードともに空欄でボタンを押しエラーになるテストを書こう() {

    }

    @Test
    public void アットマークがない不正なEメールアドレスを入力してエラーになるテストを書こう() {

    }

    @Test
    public void 四文字以下の短いパスワードを入力してエラーになるテストを書こう() {

    }

    @Test
    public void Eメールパスワードともに形式は正しいが情報が間違っているためエラーになるテストを書こう() {

    }

    @Test
    public void Eメールパスワードともに形式が正しく情報も合っているテストを書こう() {

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

