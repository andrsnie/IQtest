/*
 * Created by andrSnie on 1.03.21 3:15
 * Copyright (c) 2021. All rights reserved.
 */

package angel.andrsnie.iqtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity {

    private final Question[] mQuestions = new Question[] {
            new Question(R.string.question_1, R.string.answer_1),
            new Question(R.string.question_2, R.string.answer_2),
            new Question(R.string.question_3, R.string.answer_3),
            new Question(R.string.question_4, R.string.answer_4),
            new Question(R.string.question_5, R.string.answer_5),
            new Question(R.string.question_6, R.string.answer_6),
            new Question(R.string.question_7, R.string.answer_7),
            new Question(R.string.question_8, R.string.answer_8),
            new Question(R.string.question_9, R.string.answer_9),
            new Question(R.string.question_10, R.string.answer_10),
            new Question(R.string.question_11, R.string.answer_11),
            new Question(R.string.question_12, R.string.answer_12),
            new Question(R.string.question_13, R.string.answer_13),
            new Question(R.string.question_14, R.string.answer_14),
            new Question(R.string.question_15, R.string.answer_15),
            new Question(R.string.question_16, R.string.answer_16),
            new Question(R.string.question_17, R.string.answer_17),
            new Question(R.string.question_18, R.string.answer_18),
            new Question(R.string.question_19, R.string.answer_19),
            new Question(R.string.question_20, R.string.answer_20)
    };

    private TextView mQuestionTextView;
    private EditText mEditText;

    private int mCurrentIndex = 0;
    private boolean mIsShown;
    private int mScore;
    private boolean mIsCheckButton;

    private static final String KEY_FOR_INDEX = "currentIndex";
    private static final String KEY_FOR_SHOWN = "IsShown";
    private static final String KEY_FOR_SCORE = "score";
    private static final String KEY_FOR_IS_CHECK = "IsCheck";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_FOR_INDEX, 0);
            mIsShown = savedInstanceState.getBoolean(KEY_FOR_SHOWN, false);
            mScore = savedInstanceState.getInt(KEY_FOR_SCORE, 0);
            mIsCheckButton = savedInstanceState.getBoolean(KEY_FOR_IS_CHECK, false);
            setSubtl();
        }

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        mEditText = (EditText) findViewById(R.id.edit_answer);

        Button mCheckButton = (Button) findViewById(R.id.check_button);
        mCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
                mIsCheckButton = true;
            }
        });

        Button mHintButton = (Button) findViewById(R.id.hint_button);
        mHintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TestActivity.this, HintActivity.class);
                int answer = mQuestions[mCurrentIndex].getAnswer();
                i.putExtra(HintActivity.EXTRA_ANSWER, answer);
                startActivityForResult(i, 0);
            }
        });

        Button mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
                if (mQuestions[mCurrentIndex].getAnswer() == R.string.answer_20)
                {
//                    String congrat = getString(R.string.congratulations) + mScore;
//                    mQuestionTextView.setText(congrat);
                    Intent i = new Intent(TestActivity.this, CongratActivity.class);
                    i.putExtra(CongratActivity.EXTRA_SCORE, mScore);
                    startActivityForResult(i, 1);
                }
                else {
                    mCurrentIndex = (mCurrentIndex + 1) % mQuestions.length;
                    updateQuestion();
                    mEditText.setText("");
                    mIsCheckButton = false;
                }
            }
        });

//        mIsShown = false;
        updateQuestion();
    }

    private void updateQuestion() {
        int question = mQuestions[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer() {
        int answer = mQuestions[mCurrentIndex].getAnswer();
        int messageResId;
        String userAnswerString = mEditText.getText().toString().trim().toLowerCase();

        if (mIsShown) {
            messageResId = R.string.judgment_toast;
        } else {
            if (userAnswerString.equals(getString(answer))) {
                if (!mIsCheckButton) {
                    mScore += Question.IQ_SCORE;
                    setSubtl();
                }
                    messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
        mIsShown = false;
    }

    private void setSubtl()
    {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            String subtitleScore = getString(R.string.your_score_is) + mScore;
            actionBar.setSubtitle(subtitleScore);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_FOR_INDEX, mCurrentIndex);
        savedInstanceState.putBoolean(KEY_FOR_SHOWN, mIsShown);
        savedInstanceState.putInt(KEY_FOR_SCORE, mScore);
        savedInstanceState.putBoolean(KEY_FOR_IS_CHECK, mIsCheckButton);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        mIsShown = data.getBooleanExtra(HintActivity.EXTRA_ANSWER_SHOWN, false);
        if (requestCode == 1) {
            mCurrentIndex = data.getIntExtra(CongratActivity.EXTRA_ANSWER_INDEX, 0);
            updateQuestion();
            mScore = 0;
            setSubtl();
        }
    }
}