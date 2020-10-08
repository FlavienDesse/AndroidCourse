package com.lesbougs.androidcourse.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lesbougs.androidcourse.R;
import com.lesbougs.androidcourse.TwitterApplication;
import com.lesbougs.androidcourse.interfaces.TweetListener;
import com.lesbougs.androidcourse.pojo.Tweet;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TweetsAdapter extends BaseAdapter implements View.OnClickListener { //regarder page 24 pour améliorer

    private List<Tweet> mTweets;
    private final LayoutInflater mInflater;
    private TweetListener mListener;

    public TweetListener getListener() {
        return mListener;
    }

    public void setListener(TweetListener listener) {
        this.mListener = listener;
    }

    public TweetsAdapter(List<Tweet> tweets) {
        this.mTweets = tweets;
        this.mInflater = LayoutInflater.from(TwitterApplication.getContext());
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.tweet_listitem, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Tweet tweet = (Tweet) getItem(position);

        holder.name.setText(tweet.user.name);
        holder.alias.setText(tweet.user.screenName);
        holder.text.setText(tweet.text);
        holder.button.setTag(position);
        holder.button.setOnClickListener(this);

        if (!tweet.user.profileImageUrl.isEmpty()) {
            Picasso.get().load(tweet.user.profileImageUrl).fit().into(holder.image);
        }

        return convertView;
    }

    @Override
    public void onClick(View view) {
        int position = (Integer) view.getTag();
        if (mListener != null) {
            final Tweet tweet = (Tweet) getItem(position);
            mListener.onRetweet(tweet);
        }
    }

    private class ViewHolder {
        public ImageView image;
        public TextView name;
        public TextView alias;
        public TextView text;
        public Button button;

        public ViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.tweetListItemImageView);
            name = (TextView) view.findViewById(R.id.tweetListItemNameTextView);
            alias = (TextView) view.findViewById(R.id.tweetListItemAliasTextView);
            text = (TextView) view.findViewById(R.id.tweetListItemTextTextView);
            button = (Button) view.findViewById(R.id.tweetListItemButton);
        }
    }
}
