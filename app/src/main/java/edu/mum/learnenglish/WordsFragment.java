package edu.mum.learnenglish;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;

import edu.mum.learnenglish.adapter.WordAdapter;
import edu.mum.learnenglish.helper.SettingManager;
import edu.mum.learnenglish.swipe.WordUtils;


public class WordsFragment extends Fragment implements View.OnClickListener {
    public static Fragment fragment;
    ListView listView;
    MediaPlayer mPlayer;
    SeekBar sBar;
    android.os.Handler handler=new android.os.Handler();
    public String jsonFileName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_words, container, false);
        fragment = this;
        listView=(ListView)fragmentView.findViewById(R.id.listVidew);
        String type = getArguments().getString("type");


        sBar=(SeekBar) fragmentView.findViewById(R.id.sBar);

        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mPlayer.seekTo(progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        if (type.equals("food")){
            jsonFileName = "foods.json";

            if(SettingManager.getEnglishOnlyFlag(getContext()) == 1){
                mPlayer=MediaPlayer.create(getContext(),R.raw.food_en);
            }else{
                mPlayer=MediaPlayer.create(getContext(),R.raw.food_en_ch);
            }

        }else {
            jsonFileName = "animals.json";

            if(SettingManager.getEnglishOnlyFlag(getContext()) == 1){
                mPlayer=MediaPlayer.create(getContext(),R.raw.animal_en);
            }else{
                mPlayer=MediaPlayer.create(getContext(),R.raw.animals_en_ch);
            }
        }


        listView.setAdapter(new WordAdapter(WordUtils.loadWords(getContext(), jsonFileName)));
        sBar.setMax(mPlayer.getDuration());
        updateSeekBar();

        ImageView play = (ImageView) fragmentView.findViewById(R.id.play);
        play.setOnClickListener(this);
        ImageView forward = (ImageView) fragmentView.findViewById(R.id.forward);
        forward.setOnClickListener(this);
        ImageView backward = (ImageView) fragmentView.findViewById(R.id.backward);
        backward.setOnClickListener(this);

        return fragmentView;
    }

    public void updateSeekBar(){
        sBar.setProgress(mPlayer.getCurrentPosition());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateSeekBar();
            }
        },5000);
    }


    @Override
    public void onStop() {
        super.onStop();
        mPlayer.stop();;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.play:
                ImageView imageView = (ImageView)v;
                if(mPlayer.isPlaying()){
                    mPlayer.pause();
                    imageView.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.play));
                }else {
                    mPlayer.start();
                    imageView.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.pause));
                }
                break;
            case R.id.backward:
                mPlayer.seekTo(mPlayer.getCurrentPosition()-mPlayer.getDuration()/10);
                break;

            case R.id.forward:
                mPlayer.seekTo(mPlayer.getCurrentPosition()+mPlayer.getDuration()/10);
                break;
        }
    }
}
