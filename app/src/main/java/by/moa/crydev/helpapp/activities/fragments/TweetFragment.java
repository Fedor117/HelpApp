package by.moa.crydev.helpapp.activities.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import by.moa.crydev.helpapp.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class TweetFragment extends Fragment {

    public static final String LOG_TAG = "TweetFragment";

    private String[] data = {
        "The first tweet", "The second tweet"
    };

    ArrayAdapter<String> mTweetAdapter;

    public TweetFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        List<String> tweets = new ArrayList<>(Arrays.asList(data));

        mTweetAdapter =
                new ArrayAdapter<>(
                        getActivity(),
                        R.layout.list_item_tweets,
                        R.id.list_item_tweet_textview,
                        tweets);

        View rootview = inflater.inflate(R.layout.fragment_tweet, container, false);

        ListView listView = (ListView) rootview.findViewById(R.id.listview_tweets);
        listView.setAdapter(mTweetAdapter);

        return rootview;
    }
}
