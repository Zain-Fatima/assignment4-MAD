package com.example.finalgameapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Game#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Game extends Fragment {

    private TextView letterTextView, answerTextView;
    private char[] skyLetters = {'b', 'd', 'f', 'h', 'k', 'l', 't'};
    private char[] grassLetters = {'g', 'j', 'p', 'q', 'y'};
    private char[] rootLetters = {'a', 'c', 'e', 'i', 'm', 'n', 'o', 'r', 's', 'u', 'v', 'w', 'x', 'z'};
    private String answerString = "";
    Button skyButton;
    Button grassButton;
    Button rootButton;
    Button resetButton;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String userAns;
    String corrAns;
    int score = 0;
    int currTurn = 1;
    final int turns = 5;
    String[] Ans;
    String[] Corr;
    String[] Ques;

    public Game() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Game.
     */
    // TODO: Rename and change types and number of parameters
    public static Game newInstance(String param1, String param2) {
        Game fragment = new Game();
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

    public void resetFragment() {

        // Reset necessary variables and views
        letterTextView.setText(getRandomLetter());
        answerTextView.setText("");
        score = 0;
        currTurn = 1;
        // Enable necessary buttons if they were disabled
        skyButton.setEnabled(true);
        grassButton.setEnabled(true);
        rootButton.setEnabled(true);

        // Initialize the arrays for questions, answers, and correct answers
        Ques = new String[turns];
        Ans = new String[turns];
        Corr = new String[turns];
    }

    public Boolean endGame() {
        if (currTurn > turns) {
            letterTextView.setText("Game Over!");
            answerTextView.setText("Score: " + score);
            Db DbHandler = new Db(getContext());
            DbHandler.insertData(Ques, Ans, Corr, score);
            return true;
        }

        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        letterTextView = view.findViewById(R.id.letter_text_view);
        letterTextView.setText(getRandomLetter());

        answerTextView = view.findViewById(R.id.answer_text_view);

        skyButton = view.findViewById(R.id.sky_button);
        grassButton = view.findViewById(R.id.grass_button);
        rootButton = view.findViewById(R.id.root_button);
        resetButton = view.findViewById(R.id.resetBtn);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFragment();
            }
        });

        resetFragment();

        skyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                rootButton.setEnabled(false);
                grassButton.setEnabled(false);
                skyButton.setEnabled(false);

                if (answerString.equals("Sky Letter")) {
                    answerTextView.setText("Awesome, your answer is correct!");
                    score++;
                } else {
                    answerTextView.setText("Incorrect! The answer is " + answerString);
                }

                if (endGame()) {
                    return;
                }

                // Store the question, answer, and correct answer in the respective arrays
                Ques[currTurn - 1] = letterTextView.getText().toString();
                Ans[currTurn - 1] = answerString;
                Corr[currTurn - 1] = "Sky Letter";

                // Wait for 5 seconds and create a new question
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        letterTextView.setText(getRandomLetter());
                        answerTextView.setText("");
                        rootButton.setEnabled(true);
                        grassButton.setEnabled(true);
                        skyButton.setEnabled(true);
                    }
                }, 3000);

                currTurn++; // Increment the current turn
            }
        });

        grassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rootButton.setEnabled(false);
                grassButton.setEnabled(false);
                skyButton.setEnabled(false);

                if (answerString.equals("Grass Letter")) {
                    answerTextView.setText("Awesome, your answer is correct!");
                    score++;
                } else {
                    answerTextView.setText("Incorrect! The answer is " + answerString);
                }

                if (endGame()) {
                    return;
                }

                // Store the question, answer, and correct answer in the respective arrays
                Ques[currTurn - 1] = letterTextView.getText().toString();
                Ans[currTurn - 1] = answerString;
                Corr[currTurn - 1] = "Grass Letter";

                // Wait for 5 seconds and create a new question
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        letterTextView.setText(getRandomLetter());
                        answerTextView.setText("");
                        rootButton.setEnabled(true);
                        grassButton.setEnabled(true);
                        skyButton.setEnabled(true);
                    }
                }, 3000);

                currTurn++; // Increment the current turn
            }
        });

        rootButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rootButton.setEnabled(false);
                grassButton.setEnabled(false);
                skyButton.setEnabled(false);

                if (answerString.equals("Root Letter")) {
                    answerTextView.setText("Awesome, your answer is correct!");
                    score++;
                } else {
                    answerTextView.setText("Incorrect! The answer is " + answerString);
                }

                if (endGame()) {
                    return;
                }

                // Store the question, answer, and correct answer in the respective arrays
                Ques[currTurn - 1] = letterTextView.getText().toString();
                Ans[currTurn - 1] = answerString;
                Corr[currTurn - 1] = "Root Letter";

                // Wait for 5 seconds and create a new question
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        letterTextView.setText(getRandomLetter());
                        answerTextView.setText("");

                        rootButton.setEnabled(true);
                        grassButton.setEnabled(true);
                        skyButton.setEnabled(true);
                    }
                }, 3000);

                currTurn++; // Increment the current turn
            }
        });

        return view;
    }

    public void gameRun() {
        letterTextView.setText(getRandomLetter());
        answerTextView.setText("");
    }

    private String getRandomLetter() {
        Random random = new Random();
        int category = random.nextInt(3);
        char letter;
        switch (category) {
            case 0:
                letter = skyLetters[random.nextInt(skyLetters.length)];
                answerString = "Sky Letter";
                break;
            case 1:
                letter = grassLetters[random.nextInt(grassLetters.length)];
                answerString = "Grass Letter";
                break;
            default:
                letter = rootLetters[random.nextInt(rootLetters.length)];
                answerString = "Root Letter";
                break;
        }
        return String.valueOf(letter);
    }
}
