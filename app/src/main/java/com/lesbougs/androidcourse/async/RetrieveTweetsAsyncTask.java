package com.lesbougs.androidcourse.async;

import android.os.AsyncTask;

import com.lesbougs.androidcourse.helpers.TwitterHelper;
import com.lesbougs.androidcourse.interfaces.TweetChangeListener;
import com.lesbougs.androidcourse.pojo.Tweet;

import java.util.List;

public class RetrieveTweetsAsyncTask extends AsyncTask<String, Void, List<Tweet>> {//entry = username, progress = not yet, result = tweet list

    private TweetChangeListener mListener;

    public RetrieveTweetsAsyncTask(TweetChangeListener mListener) {
        this.mListener = mListener;
    }

    @Override
    protected List<Tweet> doInBackground(String... usernames) {
        if (usernames != null && usernames.length > 0) {
            return TwitterHelper.getTweetsOfUser(usernames[0]);//magic happends hère
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Tweet> tweets) {
        if(mListener != null && tweets != null) {
            mListener.onTweetRetrieved(tweets);
        }
        super.onPostExecute(tweets);
    }
}
