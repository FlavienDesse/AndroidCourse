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
            getActivity().getSupportFragmentManager().popBackStack();
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
}