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
}