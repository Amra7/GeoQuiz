package com.example.amrapoprzanovic.geoquiz;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class QuizUpActivity extends ActionBarActivity {

    private final String TAG = "QuizActivity";
    public static final String INTENT_KEY_MESSAGE= "message_key";

    public static final String INTENT_KEY_CORRECT_ANSWER= "bitcamp.geoQuizUp.correct_answer";
    public static final String INTENT_KEY_TOTAL_QUESTION= "bitcamp.geoQuizUp.total_question";
    public static final String INTENT_KEY_CURRENT_QUESTION= "bitcamp.geoQuizUp.current_key";
    public static final String INTENT_KEY_USERNAME= "bitcamp.geoQuizUp.username";


    private static final String BUNDLE_KEY_CORRECT_INDEX ="bitcamp.geoQuizUp.current_key";
    private static final String BUNDLE_KEY_CORRECT_ANSWER ="bitcamp.geoQuizUp.current_answer";
    private static final int INPUT_REQUEST_CODE=200;

    public static  int correct= 0;
    public static int correctAnswer= 0;
    public static int incorrect = 0;

    public  int mCorrectAnswer= 0;

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mStatsButton;
    private Button mInputButton;
    private TextView mQuestionText;

    private String mUsername=null;


    private int mCurrentIndex=0;

    private Question[] mQuestions = new Question[]{
            new Question(R.string.question_1, true),
            new Question(R.string.question_2, false),
            new Question(R.string.question_3, true),
            new Question(R.string.question_4, true),
            new Question(R.string.question_5, false),
            new Question(R.string.question_6, true)

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_up);

        Log.d(TAG, "onCreate Strated");

        if(savedInstanceState!= null){
            mCurrentIndex = savedInstanceState.getInt(BUNDLE_KEY_CORRECT_INDEX);
            mCorrectAnswer = savedInstanceState.getInt(BUNDLE_KEY_CORRECT_ANSWER);
        }

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

        mStatsButton = (Button)findViewById(R.id.stats_button);
        mStatsButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               Intent statsIntent = new Intent(QuizUpActivity.this, StatsActivity.class);
                statsIntent.putExtra(INTENT_KEY_MESSAGE, "Broj tacnih odgovora je: " + (correct*100)/(correct+incorrect) + "%");
                startActivity(statsIntent);
            }
        });

        mInputButton = (Button)findViewById(R.id.set_name_button);
        mInputButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent inputIntent = new Intent(QuizUpActivity.this, InputActivity.class);
                intputIntent.putExtra(INTENT_KEY_USERNAME, mUsername);
                startActivity(inputIntent, INPUT_REQUEST_CODE);
            }
        });

        mQuestionText = (TextView) findViewById(R.id.question_text);
//        mQuestionText.setText(mQuestions[mCurrentIndex].getQuestionId());

        updateQuestionText();


    }

    private void updateQuestionText(){
//       mCurrentIndex= (mCurrentIndex +1) % mQuestionText.length();
        mQuestionText.setText(mQuestions[mCurrentIndex].getQuestionId());
    }

    private void checkAnswer( boolean userChoice){
        int toastText;

        if(userChoice == mQuestions[mCurrentIndex].isAnswer()){
            toastText = R.string.correct;
            mCurrentIndex = (mCurrentIndex +1) % mQuestions.length;
            updateQuestionText();
            correct++;
        } else {
            toastText = R.string.incorrect;
            incorrect++;

        }

        //this predstavlja kontekst
        Toast.makeText(QuizUpActivity.this, toastText, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outputBundle){
        super.onSaveInstanceState(outputBundle);
        outputBundle.putInt(BUNDLE_KEY_CORRECT_ANSWER, mCorrectAnswer);
        outputBundle.putInt(BUNDLE_KEY_CORRECT_INDEX, mCurrentIndex);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Log.d(TAG, "Activity result " + requestCode + " " + resultCode);
        if(requestCode == INPUT_REQUEST_CODE){
            if(resultCode==RESULT_OK){
                mUsername = data.getStringExtra(INTENT_KEY_USERNAME);
                updateQuestionText();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause Started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume Strated");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy Started");

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
