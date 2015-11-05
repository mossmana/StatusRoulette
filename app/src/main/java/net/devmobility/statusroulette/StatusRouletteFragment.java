package net.devmobility.statusroulette;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import net.devmobility.statusroulette.model.RandomStatus;

/**
 * The main fragment
 */
public class StatusRouletteFragment extends Fragment {

    private static final String TAG = StatusRouletteFragment.class.getSimpleName();

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private TextView statusTextView;
    private RandomStatus status;

    public StatusRouletteFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_status_roulette, container, false);
        loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_posts");
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "onCreateView: login success!");
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        });
        Button spinButton = (Button) view.findViewById(R.id.spin_button);
        spinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRandomStatus();
            }
        });
        statusTextView = (TextView) view.findViewById(R.id.status_textview);
        return view;
    }

    private void showRandomStatus() {
        if (status == null) {
            new GraphRequest(AccessToken.getCurrentAccessToken(),
                    "/me/feed",
                    null,
                    HttpMethod.GET,
                    new GraphRequest.Callback() {
                        public void onCompleted(GraphResponse response) {
                            Log.d(TAG, "showRandomStatus: response = " + response);
                            status = new RandomStatus();
                            status.buildFromJson(response.getRawResponse());
                            updateStatus();
                        }
                    }).executeAsync();
        } else {
            updateStatus();
        }
    }

    private void updateStatus() {
        RandomStatus.Message message = status.getMessage();
        if (message != null) {
            // TODO: this should call message.getDisplayText()
            statusTextView.setText(message.getText() + " - " + message.getCreatedTime());
        } else {
            status = null;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
