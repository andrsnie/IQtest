/*
 * Created by andrSnie on 1.03.21 3:15
 * Copyright (c) 2021. All rights reserved.
 */

package angel.andrsnie.iqtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HintActivity extends AppCompatActivity {

    public static final String EXTRA_ANSWER = "angel.andrsnie.iqtest.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "angel.andrsnie.iqtest.answer_shown";
    private String mAnswer;
    private boolean mIsAnswerShown;
    private TextView mAnswerTextView;

    private static final String KEY_FOR_ANSWERSHOWN = "answerShown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        if (savedInstanceState != null) {
            mIsAnswerShown = savedInstanceState.getBoolean(KEY_FOR_ANSWERSHOWN, false);
        }

        setAnswerShownResult();

        mAnswer = getString(getIntent().getIntExtra(EXTRA_ANSWER, 0));
        mAnswerTextView = (TextView)findViewById(R.id.answerTextView);


        Button mShowAnswer = (Button) findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mAnswerTextView.setText(mAnswer);

                    mIsAnswerShown = true;
                    setAnswerShownResult();
            }
        });
    }

    private void setAnswerShownResult() {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, mIsAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(KEY_FOR_ANSWERSHOWN, mIsAnswerShown);
    }
}