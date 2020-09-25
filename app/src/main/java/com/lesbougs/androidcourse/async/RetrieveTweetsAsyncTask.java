package com.lesbougs.androidcourse.async;

import android.os.AsyncTask;
import android.util.Log;

import com.lesbougs.androidcourse.TwitterActivity;
import com.lesbougs.androidcourse.helpers.TwitterHelper;
import com.lesbougs.androidcourse.interfaces.TweetChangeListener;
import com.lesbougs.androidcourse.pojo.Tweet;

import java.util.List;


public class RetrieveTweetsAsyncTask extends AsyncTask<String, Void, List<Tweet>> {

    private TweetChangeListener mListener;

    public RetrieveTweetsAsyncTask(TweetChangeListener mListener) {
        this.mListener = mListener;
    }

    @Override
    protected List<Tweet> doInBackground(String... strings) {
        if((null != strings) && (strings.length > 0)) {
            return TwitterHelper.getTweetsOfUser(strings[0]);
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
