package edu.mum.learnenglish.swipe;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;

import edu.mum.learnenglish.R;
import edu.mum.learnenglish.helper.ContextUtil;
import edu.mum.learnenglish.model.Word;

@Layout(R.layout.word_card_view)
public class WordCard {

    @View(R.id.wordImageView)
    private ImageView wordImageView;

    @View(R.id.englishTxt)
    private TextView englishTxt;

    @View(R.id.chineseTxt)
    private TextView chineseTxt;

    private Word word;
    private Context mContext;
    private SwipePlaceHolderView mSwipeView;

    public WordCard(Context context, Word word, SwipePlaceHolderView swipeView) {
        mContext = context;
        this.word = word;
        mSwipeView = swipeView;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Resolve
    private void onResolved(){
        int id = R.mipmap.apple222;
        wordImageView.setImageResource( WordUtils.getResource(word.getImageName()));

        englishTxt.setText(word.getEnglish());
        chineseTxt.setText(word.getChinese());
    }

    @SwipeOut
    private void onSwipedOut(){
        Log.d("EVENT", "onSwipedOut");
        mSwipeView.addView(this);
    }

    @SwipeCancelState
    private void onSwipeCancelState(){
        Log.d("EVENT", "onSwipeCancelState");
    }

    @SwipeIn
    private void onSwipeIn(){
        Log.d("EVENT", "onSwipedIn");
    }

    @SwipeInState
    private void onSwipeInState(){
        Log.d("EVENT", "onSwipeInState");
    }

    @SwipeOutState
    private void onSwipeOutState(){
        Log.d("EVENT", "onSwipeOutState");
    }



}