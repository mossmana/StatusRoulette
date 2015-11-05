package net.devmobility.statusroulette.model;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by amossman on 11/4/15.
 */
public class RandomStatus {

    private static final String TAG = RandomStatus.class.getSimpleName();

    private List<Message> messages;

    private Random random = new Random(System.currentTimeMillis());

    public RandomStatus() {
    }

    public void buildFromJson(String feed) {

        try {
            JSONObject feedJSON = new JSONObject(feed);
            JSONArray dataArray = feedJSON.getJSONArray("data");
            messages = new ArrayList<>(dataArray.length());
            Message message;
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject post = dataArray.getJSONObject(i);
                String text = post.has("message") ? post.getString("message") : "";
                if (TextUtils.isEmpty(text)) {
                    text = post.has("story") ? post.getString("story") : "";
                }
                message = new Message(post.getString("created_time"), text);
                messages.add(message);
            }
        } catch (Exception e) {
            Log.e(TAG, "buildFromJson: " + e.getMessage() + " - Unable to parse feed = " + feed);
        }
    }

    public Message getMessage() {

        if (messages == null || messages.size() <= 0) return null;
        return messages.get(random.nextInt(messages.size()));
    }

    public static class Message {
        private String createdTime;
        private String text;

        private Message(String createdTime, String text) {
            this.createdTime = createdTime;
            this.text = text;
        }

        public String getCreatedTime() {
            return createdTime;
        }

        public String getText() {

            return text;
        }

        public String getDisplayText() {
            return null;
        }
    }
}
