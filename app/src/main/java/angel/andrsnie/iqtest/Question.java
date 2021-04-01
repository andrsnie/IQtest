/*
 * Created by andrSnie on 1.03.21 3:15
 * Copyright (c) 2021. All rights reserved.
 */

package angel.andrsnie.iqtest;

class Question {

    private final int mQuestion;
    private final int mAnswer;

    static final int IQ_SCORE = 10;

    Question(int question, int answer) {
        mQuestion = question;
        mAnswer = answer;
    }

    int getQuestion() {
        return mQuestion;
    }

    int getAnswer() {
        return mAnswer;
    }
}