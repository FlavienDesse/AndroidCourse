<<<<<<< HEAD
package com.lesbougs.androidcourse.ui.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lesbougs.androidcourse.R;
import com.lesbougs.androidcourse.TwitterActivity;
import com.lesbougs.androidcourse.async.RetrieveTweetsAsyncTask;
import com.lesbougs.androidcourse.interfaces.TweetChangeListener;
import com.lesbougs.androidcourse.interfaces.TweetListener;
import com.lesbougs.androidcourse.pojo.Tweet;
import com.lesbougs.androidcourse.utils.PreferenceUtils;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TweetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TweetFragment extends Fragment implements TweetChangeListener, AdapterView.OnItemClickListener {

    private RetrieveTweetsAsyncTask mTweetAsyncTask;
    private ListView mListView;
    private TweetListener mTweetListener;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TweetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TweetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TweetFragment newInstance(String param1, String param2) {
        TweetFragment fragment = new TweetFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_twitter, container, false);
        mListView = (ListView) rootView.findViewById(R.id.tweetsListView);//interception de la listView du fragment
        mListView.setOnItemClickListener(this);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        final String login = PreferenceUtils.getLogin();
        if (!TextUtils.isEmpty(login)) {
            mTweetAsyncTask = new RetrieveTweetsAsyncTask(this);//this class implements
            mTweetAsyncTask.execute(login);
        }
    }

    @Override
    public void onTweetRetrieved(List<Tweet> tweets) {
        if (tweets != null) {
            final ArrayAdapter<Tweet> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, tweets);
            mListView.setAdapter(adapter);
            for (Tweet tweet : tweets) {
                //System.out.println(TwitterActivity.class.getName() + tweet.text);//interdit en déploiement
                Log.d(TwitterActivity.class.getName(), tweet.text);//plus correct. d pour débug

                //appliquer ici le layout à chaque tweet?
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TweetListener) {
            mTweetListener = (TweetListener) context;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Tweet tweet = (Tweet) mListView.getItemAtPosition(position);
        if (mTweetListener != null) mTweetListener.onViewTweet(tweet);
    }
=======
package com.lesbougs.androidcourse.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lesbougs.androidcourse.R;
import com.lesbougs.androidcourse.TwitterActivity;
import com.lesbougs.androidcourse.TwitterApplication;
import com.lesbougs.androidcourse.utils.Constants;
import com.squareup.picasso.Picasso;

import static android.widget.Toast.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TweetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TweetFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_NAME = "paramName";
    private static final String ARG_PARAM_ALIAS = "paramAlias";
    private static final String ARG_PARAM_TEXT = "paramText";
    private static final String ARG_PARAM_URL = "paramUrl";

    // TODO: Rename and change types of parameters
    private String mName;
    private String mAlias;
    private String mText;
    private String mUrl;

    private Button mReturnButton;

    public TweetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param Name Parameter 1.
     * @param Alias Parameter 2.
     * @param Text Parameter 3.
     * @param Url Parameter 4.
     * @return A new instance of fragment TweetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TweetFragment newInstance(String Name, String Alias, String Text, String Url) {
        TweetFragment fragment = new TweetFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_NAME, Name);
        args.putString(ARG_PARAM_ALIAS, Alias);
        args.putString(ARG_PARAM_TEXT, Text);
        args.putString(ARG_PARAM_URL, Url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mName = getArguments().getString(ARG_PARAM_NAME);
            mAlias = getArguments().getString(ARG_PARAM_ALIAS);
            mText = getArguments().getString(ARG_PARAM_TEXT);
            mUrl = getArguments().getString(ARG_PARAM_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_tweet, container, false);

        ((TextView) view.findViewById(R.id.tweetNameTextView)).setText(mName);
        ((TextView) view.findViewById(R.id.tweetAliasTextView)).setText(mAlias);
        ((TextView) view.findViewById(R.id.tweetTextTextView)).setText(mText);

        if (!mUrl.isEmpty()) {
            Picasso.get().load(mUrl).fit().into((ImageView) view.findViewById(R.id.tweetImageView));
        }

        mReturnButton = (Button) view.findViewById(R.id.tweetExitButton);
        mReturnButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "retour à la liste", LENGTH_SHORT).show();//tmp
            //stuff
        });

        ((Button) view.findViewById(R.id.tweetReplyButton)).setOnClickListener(v -> {
            Toast.makeText(getContext(), R.string.tweet_reply, LENGTH_SHORT).show();
        });
        ((Button) view.findViewById(R.id.tweetRetweetButton)).setOnClickListener(v -> {
            Toast.makeText(getContext(), R.string.tweet_retweet, LENGTH_SHORT).show();
        });
        ((Button) view.findViewById(R.id.tweetStarButton)).setOnClickListener(v -> {
            Toast.makeText(getContext(), R.string.tweet_star, LENGTH_SHORT).show();
        });

        return view;
    }
>>>>>>> origin/TP3/Master
}