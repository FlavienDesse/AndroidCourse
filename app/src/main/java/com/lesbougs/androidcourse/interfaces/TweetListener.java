package com.lesbougs.androidcourse.interfaces;

import com.lesbougs.androidcourse.pojo.Tweet;

public interface TweetListener {

    public void onRetweet(Tweet tweet);
    public void onViewTweet(Tweet tweet);
}
