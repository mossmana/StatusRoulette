package net.devmobility.statusroulette;

import android.widget.Button;

import com.facebook.FacebookSdk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
/**
 * Created by amossman on 11/5/15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class StatusRouletteFragmentTest {

    @Test
    public void spinButtonHasCorrectLabel() {

        FacebookSdk.sdkInitialize(RuntimeEnvironment.application);
        StatusRouletteFragment fragment = new StatusRouletteFragment();
        SupportFragmentTestUtil.startVisibleFragment(fragment);
        Button spinButton = (Button) fragment.getView().findViewById(R.id.spin_button);
        assertThat(spinButton, is(not(nullValue())));
        assertThat(spinButton.getText().toString(), is("Spin"));
    }
}
