package c2d.com.codetodevelop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import c2d.com.codetodevelop.javaClass.QuizDbHelper;
import pl.droidsonroids.gif.GifImageView;

public class StartingScreen extends AppCompatActivity {
    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String EXTRA_SCORE = "extraScore";
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";

    GifImageView sad, happy;
    ImageView certificate;

    private int highscore;
    private int score;
    String levelId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);
        getSupportActionBar().hide(); //hide the title bar

        sad = findViewById(R.id.sad);
        happy = findViewById(R.id.happy);
        certificate = findViewById(R.id.certficate);


        Intent intent = getIntent();
        levelId = intent.getStringExtra("Level");
        final int sco = intent.getIntExtra("SCORE", 0);
        score = sco * sco;
        System.out.println("hgsc" + score);
        if (sco <= 3) {

            //  Toast.makeText(this,"sad =" +sco,Toast.LENGTH_LONG).show();
            //  showAlertDialog(R.layout.sad_face_layout);
            sad.setVisibility(View.VISIBLE);

        }

        if (sco >= 4) {

            //  Toast.makeText(this,"Happy =" +sco,Toast.LENGTH_LONG).show();

            happy.setVisibility(View.VISIBLE);

            AlertDialog.Builder builder = new AlertDialog.Builder(StartingScreen.this);

            // Set a title for alert dialog
           // builder.setTitle("Select your answer.");

            // Ask the final question
            builder.setMessage("Do you Want to generate Certificate?");

            // Set the alert dialog yes button click listener
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Do something when user clicked the Yes button
                    // Set the TextView visibility GONE
                     certificate.setVisibility(View.VISIBLE);
                }
            });

            // Set the alert dialog no button click listener
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Do something when No button clicked
                    Toast.makeText(getApplicationContext(),
                            "No Button Clicked", Toast.LENGTH_SHORT).show();
                }
            });

            AlertDialog dialog = builder.create();
            // Display the alert dialog on interface
            dialog.show();
        }



        Button buttonStartQuiz=findViewById(R.id.button_start_quiz);

        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(StartingScreen.this,SelectLevel.class);
                intent1.putExtra("SCORE",sco);
                intent1.putExtra("Level",levelId);
                startActivity(intent1);

               // startQuiz();

            }
        });
    }

    private void startQuiz()
    {


        Intent intent=new Intent(StartingScreen.this,SelectLevel.class);
        startActivity(intent);
//        String tv1;
//        Intent in = getIntent();
//        tv1 = in.getExtras().getString("Level1");
//        if(tv1!=null && !tv1.isEmpty()){
//
//            QuizDbHelper dbHelper = new QuizDbHelper(getApplicationContext());
//            dbHelper.getAllQuestions(tv1);
//
//            System.out.println("leve="+tv1);
//            Toast.makeText(this,"level="+tv1,Toast.LENGTH_SHORT).show();

        }




//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == REQUEST_CODE_QUIZ){
//            if(resultCode == RESULT_OK){
//                 score = data.getIntExtra(Quiz.EXTRA_SCORE,0);
//                if (score > highscore)
//                {
//                    updateHighScore(score);
//                }
//
//
//
//            }
//
//        }
//    }

    private void loadHighScore()
    {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        highscore=prefs.getInt(KEY_HIGHSCORE,0);
        //textViewHighScore.setText("Hightscore: " + highscore);
    }




//    private void updateHighScore(int highscoreNew){
//        highscore = highscoreNew;
//      //  textViewHighScore.setText("Hightscore: " + highscore);
//        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putInt(KEY_HIGHSCORE,highscore);
//        editor.apply();
//    }


//    private void showAlertDialog(int layout){
//        dialogBuilder = new AlertDialog.Builder(StartingScreen.this);
//        View layoutView = getLayoutInflater().inflate(layout, null);
//        Button dialogButton = layoutView.findViewById(R.id.btnDialog);
//        dialogBuilder.setView(layoutView);
//        alertDialog = dialogBuilder.create();
////        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
////        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        alertDialog.show();
//        dialogButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                alertDialog.dismiss();
//            }
//        });
//    }

    }


