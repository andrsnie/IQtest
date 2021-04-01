/*
 * Created by andrSnie on 1.03.21 3:15
 * Copyright (c) 2021. All rights reserved.
 */

package angel.andrsnie.iqtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CongratActivity extends AppCompatActivity {

    public static final String EXTRA_ANSWER_INDEX = "angel.andrsnie.iqtest.answer_index";
    public static final String EXTRA_SCORE = "angel.andrsnie.iqtest.iq_score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrat);

        setAnswerShownResult();

        int score = getIntent().getIntExtra(EXTRA_SCORE, 0);

        TextView congratView = (TextView) findViewById(R.id.congrat);
        String congrat = getString(R.string.congratulations) + score;
        congratView.setText(congrat);

        TextView iqView = (TextView) findViewById(R.id.iq);
        if (score < 85)
            iqView.setText(R.string.iq_85);
        else if (score >= 140)
            iqView.setText(R.string.iq_140);
        else if (115 < score && score < 140)
            iqView.setText(R.string.iq_115);
        else
            iqView.setText(R.string.iq_85_115);
    }

    private void setAnswerShownResult() {
        Intent data = new Intent();
        int mIndexQuestion = 0;
        data.putExtra(EXTRA_ANSWER_INDEX, mIndexQuestion);
        setResult(RESULT_OK, data);
    }
}