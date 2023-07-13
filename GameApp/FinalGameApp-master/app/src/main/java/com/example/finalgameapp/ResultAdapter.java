package com.example.finalgameapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.finalgameapp.R;
import com.example.finalgameapp.ResultItem;

import java.util.List;

public class ResultAdapter extends ArrayAdapter<ResultItem> {

    public ResultAdapter(Context context, List<ResultItem> resultsList) {
        super(context, 0, resultsList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.result_list_item, parent, false);
        }

        ResultItem currentItem = getItem(position);

        TextView dateTextView = convertView.findViewById(R.id.text_date);
        TextView scoreTextView = convertView.findViewById(R.id.text_score_value);
        TextView questionTextView = convertView.findViewById(R.id.text_questions);
        TextView answerTextView = convertView.findViewById(R.id.text_answers);
        TextView correctAnsTextView = convertView.findViewById(R.id.text_correct_ans);

        dateTextView.setText(currentItem.getDate());
        scoreTextView.setText(Integer.toString(currentItem.getScore()));
        questionTextView.setText(currentItem.getQuestion());
        answerTextView.setText(currentItem.getAnswer());
        correctAnsTextView.setText(currentItem.getCorrectAnswer());

        return convertView;
    }
}
