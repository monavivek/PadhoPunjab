package c2d.com.codetodevelop;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import c2d.com.codetodevelop.javaClass.QuizDbHelper;
import c2d.com.codetodevelop.level.Level;
import c2d.com.codetodevelop.level.LevelAdapter;
import c2d.com.codetodevelop.level.RecyclerTouchListener;

public class SelectLevel extends AppCompatActivity {
    private List<Level> levelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private LevelAdapter lAdapter;
    QuizDbHelper quizDbHelper ;
    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String SHARED_PREFS="sharedPrefs";
    public static final String KEY_HIGHSCORE="keyHighscore";
    private TextView textViewHighScore,textViewScore;
    private int highscore;
    String value;
    Button video;
    int i;
    //int score;
    int sco;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);
        getSupportActionBar().hide(); //hide the title bar


        Animation animation;
        animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.together);

        Intent intent = getIntent();
       int ac = intent.getIntExtra("SCORE",0);
       System.out.println("ac="+ac);

        quizDbHelper =new QuizDbHelper(this);
//        if(ac >=4){
//            //quizDbHelper.updateLevel(intent.getStringExtra("Level"));
//        }
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        video = findViewById(R.id.video);
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectLevel.this,VideoActivity.class);
                startActivity(intent);
            }
        });

       // textViewScore = findViewById(R.id.text_view_score);
        //loadScore();
        //textViewHighScore = findViewById(R.id.text_view_hightscore);

        //loadHighScore();
        lAdapter= new LevelAdapter(levelList);
        recyclerView.setHasFixedSize(true);

        // vertical RecyclerView
        // keep movie_list_row.xml width to `match_parent`
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        // horizontal RecyclerView
        // keep movie_list_row.xml width to `wrap_content`
        // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(mLayoutManager);

        // adding inbuilt divider line
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // adding custom divider line with padding 16dp
        // recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.HORIZONTAL, 16));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(lAdapter);

        // row click listener
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Level level = levelList.get(position);
                Intent intent =new Intent(SelectLevel.this,Quiz.class);
                 value = level.getLevelId();
               //  sc = level.setScore(score);

              //      System.out.println("hlw="+score);
                intent.putExtra("Level",value);
                startActivityForResult(intent,REQUEST_CODE_QUIZ);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        prepareLevelData();

    }


    private void prepareLevelData() {
        this.quizDbHelper.deleteLevel();
        for (int i = 1; i <= 4; i++) {
            Level level = new Level();
            StringBuilder sb = new StringBuilder();
            sb.append("level ");
            level.setTitle("level " + i);
            sb.append(i);
            level.setLevelId("" +i);
            level.setTitle(sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append("");
            sb2.append(i);
            this.quizDbHelper.addLevel(level);
        }
        this.levelList.clear();
        for (Level level2 : this.quizDbHelper.getAllLevel()) {
            this.levelList.add(level2);
        }
        this.lAdapter.notifyDataSetChanged();
    }


//    private void prepareLevelData() {
//
//        for (i = 1; i <= 4; i++)
//            {
//                //Level level = new Level();
//                //level.setTitle("level " + i);
//               // System.out.println("lv"+i);
//               // level.setLevelId("" +i);
//               // level.setScore("0");
//
//               // quizDbHelper.addLevel(level );
//
//            }
//        levelList.clear();
//        for(Level level2 : quizDbHelper.getAllLevel())
//        {
//
//            levelList.add(level2);
//
//
//        }
//        // notify adapter about data set changes
//        // so that it will render the list with new data
//       // levelList.clear();
//        lAdapter.notifyDataSetChanged();
//    }

//    private void startQuiz()
//    {
//
//
//        Intent intent=new Intent(SelectLevel.this,Quiz.class);
//        startActivityForResult(intent,REQUEST_CODE_QUIZ);
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
//
//        }


   // }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ){
            if(resultCode == RESULT_OK){
                 int score = data.getIntExtra(Quiz.EXTRA_SCORE,0);
               int scoree= Integer.parseInt(value)*score;

               System.out.println("hlw="+scoree);
             //   textViewScore.setText("score: " + scoree);


                if (scoree > highscore)
                {
                    updateHighScore(scoree);
                }

            }

        }
    }

    private void loadHighScore()
    {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        highscore=prefs.getInt(KEY_HIGHSCORE,0);
        textViewHighScore.setText("Highscore: " + highscore);
    }

//    private void loadScore(){
//
//        int score = data.getIntExtra(Quiz.EXTRA_SCORE,0);
//        textViewScore.setText("score: " + score);
//
//    }

    private void updateHighScore(int highscoreNew){
        highscore = highscore+highscoreNew;
        textViewHighScore.setText("Highscore: " + highscore);
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE,highscore);
        editor.apply();
    }






}
