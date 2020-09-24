package com.lesbougs.androidcourse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.lesbougs.androidcourse.utils.Constants;
import com.lesbougs.androidcourse.utils.PreferenceUtils;

public class TwitterLoginActivity extends Activity implements View.OnClickListener {
    private EditText mLoginEditText; //m (convention) : donnée membre
    private EditText mPasswordEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        mLoginEditText = (EditText) findViewById(R.id.loginEditText);
        mPasswordEditText = (EditText) findViewById(R.id.passwordEditText);

        final String login = PreferenceUtils.getLogin();
        if (!TextUtils.isEmpty(login)) startActivity(getHomeIntent(login));
    }

    @Override
    public void onClick(View view) {
        {
            Boolean loginData = TextUtils.isEmpty(mLoginEditText.getText());
            Boolean passwordData = TextUtils.isEmpty(mPasswordEditText.getText());

            if (loginData && passwordData) {//+complet
                final String errMsg = getResources().getString(R.string.error_no_login) + "\n" + getResources().getString(R.string.error_no_password);
                Toast.makeText(this, errMsg, Toast.LENGTH_LONG).show();
                return;
            }
            else if (passwordData) {//+probable
                Toast.makeText(this, R.string.error_no_password, Toast.LENGTH_LONG).show();
                return;
            }
            else if (loginData) {
                Toast.makeText(this, R.string.error_no_login, Toast.LENGTH_LONG).show();
                return;
            }
        }

        String login = mLoginEditText.getText().toString();
        PreferenceUtils.setLogin(login);

        startActivity(getHomeIntent(login));
    }

    private Intent getHomeIntent(String userName) {
        Intent intent = new Intent(this, TwitterActivity.class);

        final Bundle extras = new Bundle();
        extras.putString(Constants.Login.EXTRA_LOGIN, userName);//ensemble de clés valeurs
        intent.putExtras(extras);

        return intent;
    }
}
