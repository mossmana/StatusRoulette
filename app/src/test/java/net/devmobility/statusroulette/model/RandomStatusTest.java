package net.devmobility.statusroulette.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

/**
 * Created by amossman on 11/4/15.
 */
@RunWith(RobolectricTestRunner.class)
public class RandomStatusTest {

    @Test
    public void randomStatusCanBeBuiltFromJson() {

        RandomStatus status = getRandomStatus();
        assertThat(status.getMessage(), is(not(nullValue())));
        String message = status.getMessage().getText();
        assertThat(
                message.equals("Test 1")
                ||
                message.equals("Test 2")
                , is(true));
    }

    @Test
    public void randomStatusHasCorrectDisplayText() {

        RandomStatus status = getRandomStatus();
        String displayText = status.getMessage().getDisplayText();
        // TODO: implement getDisplayText and complete this unit test
    }

    private RandomStatus getRandomStatus() {
        String feed = "{\"data\":[{\"id\":\"2532470678170_2492500758947\",\"created_time\":\"2015-08-28T02:04:56+0000\",\"message\":\"Test 1\"},{\"id\":\"2532470678170_2482848677651\",\"created_time\":\"2015-08-16T07:23:24+0000\",\"story\":\"Test 2\"}],\"paging\":{\"previous\":\"https:\\/\\/graph.facebook.com\\/v2.5\\/2532470678170\\/feed?format=json&since=1443366722&access_token=CAAXgYBPb2GQBANo7nA5Brf286lBp4mTfb7ig1FYEHjZCZBNY4MsZAXF29LrGUeFKXzrChYEgEoWis5tfNzC5vayaYBw7AYeSXsviUBVTquBJ9qVgxuwknJIH6joJ1GpCdqICcucHZCIeX8GzQhZC5cphpENprFOGRvRIKAkDqJhyObZCscF0ZAGBxCZBtmOgMQcQOrZA767PhlgZDZD&limit=25&__paging_token=enc_AdC6KguD9PPeXZAbdu07XlsVfZBAcDWVD27hD7DrGl9GU4VIGhWwwWg3jrXALOgCqnW4d37XGcpdkZACOxc1rDG2Azs&__previous=1\",\"next\":\"https:\\/\\/graph.facebook.com\\/v2.5\\/2532470678170\\/feed?format=json&access_token=CAAXgYBPb2GQBANo7nA5Brf286lBp4mTfb7ig1FYEHjZCZBNY4MsZAXF29LrGUeFKXzrChYEgEoWis5tfNzC5vayaYBw7AYeSXsviUBVTquBJ9qVgxuwknJIH6joJ1GpCdqICcucHZCIeX8GzQhZC5cphpENprFOGRvRIKAkDqJhyObZCscF0ZAGBxCZBtmOgMQcQOrZA767PhlgZDZD&limit=25&until=1423090092&__paging_token=enc_AdBsNNyLVsRJ20PqZBEVM3BUakwXYcMP2Kw5lTil0KBrkBs005MqYj5DJez6OBiwrSRrge6jhTGOOIpbjXdy3I12U\"}}";
        RandomStatus status = new RandomStatus();
        status.buildFromJson(feed);
        return status;
    }

}
