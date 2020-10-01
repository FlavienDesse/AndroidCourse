package com.lesbougs.androidcourse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.lesbougs.androidcourse.interfaces.TweetListener;
import com.lesbougs.androidcourse.pojo.Tweet;
import com.lesbougs.androidcourse.ui.fragments.TweetFragment;
import com.lesbougs.androidcourse.utils.Constants;
import com.lesbougs.androidcourse.utils.PreferenceUtils;

public class TwitterActivity extends AppCompatActivity implements TweetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);

        final Intent intent = getIntent();
        if(intent != null) {
            final Bundle extras = intent.getExtras();
            if (extras != null && extras.containsKey(Constants.Login.EXTRA_LOGIN)) {
                final String login = extras.getString(Constants.Login.EXTRA_LOGIN);
                getSupportActionBar().setSubtitle(login);//getActionBar avec extends Activity
            }
        }
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new TweetFragment()).commit();

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.wltwitter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.actionLogout) {
            PreferenceUtils.setLogin(null);
            finish();//arrêter action en cours
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRetweet(Tweet tweet) {
        Toast.makeText(this, "Retweet : " + tweet.text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onViewTweet(Tweet tweet) {
        Toast.makeText(this, "View : " + tweet.text, Toast.LENGTH_LONG).show();
    }
}