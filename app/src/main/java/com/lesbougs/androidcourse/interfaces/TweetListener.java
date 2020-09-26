package com.lesbougs.androidcourse.interfaces;

import com.lesbougs.androidcourse.pojo.Tweet;

public interface TweetListener {

    void onRetweet(Tweet tweet);
    void onViewTweet(Tweet tweet);
}
