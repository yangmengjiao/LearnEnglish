package edu.mum.learnenglish;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import edu.mum.learnenglish.helper.SettingManager;
import edu.mum.learnenglish.helper.ShakeManager;
import edu.mum.learnenglish.model.Word;
import edu.mum.learnenglish.swipe.WordCard;
import edu.mum.learnenglish.swipe.WordUtils;


public class RandomWordFragment extends Fragment {

    private SwipePlaceHolderView mSwipeView;
    private Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_random, container, false);

        mSwipeView = (SwipePlaceHolderView)view.findViewById(R.id.swipeView);
        mContext = getContext();

        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.swipe_out_msg_view));


        for(Word word : WordUtils.loadWords(this.getContext(), "random_words.json")){
            mSwipeView.addView(new WordCard(mContext, word, mSwipeView));
        }
        ShakeManager shakeManager = new ShakeManager(getContext(),mSwipeView);

        return  view;
    }
}
