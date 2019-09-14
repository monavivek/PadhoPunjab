package c2d.com.codetodevelop.javaClass;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.BaseColumns;
import android.widget.Toast;

import c2d.com.codetodevelop.level.Level;

import static android.content.Context.MODE_PRIVATE;
import static c2d.com.codetodevelop.StartingScreen.SHARED_PREFS;

public final class QuizContract {



    private QuizContract(){}

    public static class QuestionTable implements BaseColumns {

        public static final String TABLE_NAME="quiz_questions";
        public static final String COLUMN_QUESTION="question";
        public static final String COLUMN_OPTION1="option1";
        public static final String COLUMN_OPTION2="option2";
        public static final String COLUMN_OPTION3="option3";
        public static final String COLUMN_OPTION4="option4";

        public static final String COLUMN_ANSWER_NR="answer_nr";
        public static final String COLUMN_HINT="hint";
        public static final String COLUMN_TOKEN="token";

    }
}
