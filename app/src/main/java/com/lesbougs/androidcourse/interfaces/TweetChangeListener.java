package com.lesbougs.androidcourse.interfaces;

import com.lesbougs.androidcourse.pojo.Tweet;

import java.util.List;

public interface TweetChangeListener {

    void onTweetRetrieved(List<Tweet> tweets);
}
