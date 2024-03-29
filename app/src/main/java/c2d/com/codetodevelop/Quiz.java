package c2d.com.codetodevelop;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import c2d.com.codetodevelop.javaClass.Question;
import c2d.com.codetodevelop.javaClass.QuizDbHelper;
import c2d.com.codetodevelop.level.Level;

public class Quiz extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extraScore";
    private static final long COUNTDOWN_IN_MILLIS = 30000;

    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_MILLIS_LEFT = "keyMillisLeft";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";

    String tv;

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;
    private TextView textViewHint;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttomConfirmNext;
    private ImageView buttonHint;
    private List<Level> levelList = new ArrayList<>();

    private ArrayList<Question> questionList;

    private ColorStateList textColorDefaultRb;
    private ColorStateList getTextColorDefaultcd;

    private CountDownTimer countDownTimer;
    private long timeLeftMillis;

    private int questionCounter;
    private int questionCountTotal = 5;
    private Question currentQuestion;

    private int score;
    private boolean answered;
    private long backPressedTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getSupportActionBar().hide(); //hide the title bar

        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewCountDown = findViewById(R.id.text_view_countdown);
        textViewHint = findViewById(R.id.text_view_hint);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        rb4 = findViewById(R.id.radio_button4);
        buttomConfirmNext = findViewById(R.id.button_confirm_next);
        buttonHint = findViewById(R.id.btn_hint);

        buttonHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHint();
            }
        });

        textColorDefaultRb = rb1.getTextColors();
        getTextColorDefaultcd = textViewCountDown.getTextColors();

        if (savedInstanceState == null) {

//            QuizDbHelper dbHelper = new QuizDbHelper(this);
//            questionList = dbHelper.getAllQuestions();

//
            QuizDbHelper dbHelper = new QuizDbHelper(this);

            Intent intent = getIntent();
            tv= intent.getStringExtra("Level");
            System.out.println("mo="+tv);
           questionList = dbHelper.getAllQuestions(tv);


       //     questionCountTotal = questionList.size();
            Collections.shuffle(questionList);

            showNextQuestion();
        }else{
            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            if (questionList == null)
            {
                finish();
            }
         //   questionCountTotal = questionList.size();
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = questionList.get(questionCounter - 1);
            score = savedInstanceState.getInt(KEY_SCORE);
            timeLeftMillis = savedInstanceState.getLong(KEY_MILLIS_LEFT);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);

            if (!answered)
            {
                startCountDown();
            } else {
                updateCountDownText();
                showSolution();
            }

        }

        buttomConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answered){
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked())
                    {
                        checkAnswer();

                    }else{
                        Toast.makeText(Quiz.this,"Plese select an Option",Toast.LENGTH_LONG).show();
                    }
                }else{
                    showNextQuestion();

                }

            }
        });

    }

    private void checkAnswer()
    {
        answered = true;

        countDownTimer.cancel();
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());

        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        if(answerNr == currentQuestion.getAnswerNr()){
            score++;
            textViewScore.setText("Score: " + score);

            System.out.println("Score"+score);
        }

        showSolution();
    }

    private void showSolution()
    {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNr())
        {
            case 1:
                rb1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Option 1 is Correct");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Option 2 is Correct");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Option 3 is Correct");
                break;

            case 4:
                rb4.setTextColor(Color.GREEN);
                textViewQuestion.setText("Option 4 is Correct");
                break;
        }
        if (questionCounter < questionCountTotal)
        {
            buttomConfirmNext.setText("Next");
            textViewHint.setText("");
        } else
        {
            buttomConfirmNext.setText("Finish");
            textViewHint.setText("");


        }if (questionCounter==questionCountTotal){


            Intent intent = new Intent(Quiz.this,StartingScreen.class);

            intent.putExtra("SCORE",score);
            intent.putExtra("Level",tv);
            System.out.println("mScore = "+score);
            startActivity(intent);

                     }

    }

    private void showHint()
    {
        textViewHint.setText(currentQuestion.getHint());
        if (buttonHint.isClickable() == true)
        {
            score--;
            textViewScore.setText("Score: " + score);
        }
    }

    private void showNextQuestion()
    {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rb4.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        if(questionCounter < questionCountTotal)
        {
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());

            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());
            questionCounter++;
            textViewQuestionCount.setText("Question : " + questionCounter + "/" +questionCountTotal);
            answered = false;
            buttomConfirmNext.setText("confirm");

            timeLeftMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
            }
            else{

            finishQuiz();
        }
    }

    private void startCountDown(){
        countDownTimer = new CountDownTimer(timeLeftMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timeLeftMillis = millisUntilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {

                timeLeftMillis = 0;
                updateCountDownText();
                checkAnswer();

            }
        }.start();
    }

    private void updateCountDownText(){
        int mintues = (int) (timeLeftMillis / 1000) / 60;
        int seconds = (int) (timeLeftMillis / 1000) % 60;

        String timeformatted = String.format(Locale.getDefault(),"%02d:%02d", mintues, seconds);

        textViewCountDown.setText(timeformatted);

        if(timeLeftMillis < 10000){
            textViewCountDown.setTextColor(Color.RED);
        }else{
            textViewCountDown.setTextColor(getTextColorDefaultcd);
        }
    }

    private void finishQuiz()
    {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE,score);
        setResult(RESULT_OK,resultIntent);
        finish();
    }

    @Override
    public void onBackPressed() {

        if(backPressedTime + 2000 > System.currentTimeMillis()){
            finishQuiz();


        }else {
            Toast.makeText(Quiz.this,"Press back again to finish",Toast.LENGTH_LONG).show();

        }

        backPressedTime = System.currentTimeMillis();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer != null)
        {
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE,score);
        outState.putInt(KEY_QUESTION_COUNT,questionCounter);
        outState.putLong(KEY_MILLIS_LEFT,timeLeftMillis);
        outState.putBoolean(KEY_ANSWERED,answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST,questionList);
    }
}
