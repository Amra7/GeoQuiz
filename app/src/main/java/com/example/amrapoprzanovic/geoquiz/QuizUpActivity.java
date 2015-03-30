package com.example.amrapoprzanovic.geoquiz;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class QuizUpActivity extends ActionBarActivity {


    private Button mTrueButton;
    private Button mFalseButton;
    private TextView mQuestionText;


    private int mCurrentIndex=0;
    private Question[] mQuestions = new Question[]{
            new Question(R.string.question_1, true),
            new Question(R.string.question_2, false),
            new Question(R.string.question_3, true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_up);

        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer(true);
                //Toast.makeText(QuizUpActivity.this,R.string.correct, Toast.LENGTH_SHORT).show();
            }
        });

        mFalseButton =(Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                checkAnswer(false);
               // Toast.makeText(QuizUpActivity.this, R.string.incorrect, Toast.LENGTH_SHORT).show();
            }
        });

        mQuestionText = (TextView) findViewById(R.id.question_text);
//        mQuestionText.setText(mQuestions[mCurrentIndex].getQuestionId());
        updateQuestionText();


    }

    private void updateQuestionText(){
        mQuestionText.setText(mQuestions[mCurrentIndex].getQuestionId());
    }

    private void checkAnswer( boolean userChoice){
        int toastText;

        if(userChoice == mQuestions[mCurrentIndex].isAnswer()){
            toastText = R.string.correct;
            mCurrentIndex = (mCurrentIndex +1) % mQuestions.length;
            updateQuestionText();
        } else {
            toastText = R.string.incorrect;
        }

        Toast.makeText(QuizUpActivity.this, toastText, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
