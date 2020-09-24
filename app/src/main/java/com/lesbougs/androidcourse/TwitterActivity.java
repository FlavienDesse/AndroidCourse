package com.lesbougs.androidcourse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.lesbougs.androidcourse.utils.Constants;
import com.lesbougs.androidcourse.utils.PreferenceUtils;

public class TwitterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent = getIntent();
        if(intent != null) {
            final Bundle extras = intent.getExtras();
            if (extras != null && extras.containsKey(Constants.Login.EXTRA_LOGIN)) {
                final String login = extras.getString(Constants.Login.EXTRA_LOGIN);
                getSupportActionBar().setSubtitle(login);//getActionBar avec extends Activity
            }
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
}