
package com.example.finalgameapp;

public class ResultItem {
    private String date;
    int  score;
    private String question;
    private String answer;
    private String correctAnswer;

    public ResultItem(String date, int score, String question, String answer, String correctAnswer) {
        this.date = date;
        this.score = score;
        this.question = question;
        this.answer = answer;
        this.correctAnswer = correctAnswer;
    }

    public String getDate() {
        return date;
    }

    public int getScore() {
        return score;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
