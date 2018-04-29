package edu.mum.learnenglish.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.mum.learnenglish.WordsFragment;
import edu.mum.learnenglish.R;
import edu.mum.learnenglish.model.Word;
import edu.mum.learnenglish.swipe.WordUtils;

public class WordAdapter extends BaseAdapter {
    private  List<Word> words ;
    public WordAdapter(List<Word> words){
         this.words = words;
    }

    @Override
    public int getCount() {
        return words.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(WordsFragment.fragment.getContext());
        View view = inflater.inflate(R.layout.activity_custom_list_view,null);
        ImageView imageView = (ImageView)view.findViewById(R.id.picture);
        TextView englishTV = (TextView)view.findViewById(R.id.englishTV);
        TextView chineseTV = (TextView)view.findViewById(R.id.chineseTV);
        englishTV.setText(words.get(position).getEnglish());
        chineseTV.setText(words.get(position).getChinese());
        imageView.setImageResource(WordUtils.getResource(words.get(position).getImageName()));
        return view;
    }
}
