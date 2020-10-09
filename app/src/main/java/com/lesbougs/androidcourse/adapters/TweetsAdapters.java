<<<<<<< HEAD
package com.lesbougs.androidcourse.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lesbougs.androidcourse.pojo.Tweet;

import java.util.List;

public class TweetsAdapters extends BaseAdapter {

    private List<Tweet> mTweets;

    public TweetsAdapters(List<Tweet> mTweets) {
        this.mTweets = mTweets;
    }

    @Override
    public int getCount() {
        return mTweets != null ? mTweets.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mTweets != null ? mTweets.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        return null;
    }
}
=======
package com.lesbougs.androidcourse.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lesbougs.androidcourse.pojo.Tweet;

import java.util.List;

public class TweetsAdapters extends BaseAdapter {

    private List<Tweet> mTweets;

    public TweetsAdapters(List<Tweet> mTweets) {
        this.mTweets = mTweets;
    }

    @Override
    public int getCount() {
        return mTweets != null ? mTweets.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mTweets != null ? mTweets.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        return null;
    }
}
>>>>>>> parent of 441a296... Merge branch 'TP1/Thib' into TP3/Master
