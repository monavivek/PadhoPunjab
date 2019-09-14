package c2d.com.codetodevelop.javaClass;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import c2d.com.codetodevelop.javaClass.QuizContract.QuestionTable;
import c2d.com.codetodevelop.level.Level;

import static android.content.Context.MODE_PRIVATE;
import static c2d.com.codetodevelop.StartingScreen.SHARED_PREFS;


public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 3;
    private SQLiteDatabase db;
    private static final String SHARED_PREF_NAME = "sharedPrefs";
    private static final String KEY_NAME = "keyLevel";
    Level level;
    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db=db;
        final String SQL_CREATE_QUESTIONS_TABLE="CREATE TABLE "+ QuestionTable.TABLE_NAME +"("+
                QuestionTable._ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                QuestionTable.COLUMN_QUESTION +" TEXT, " +
                QuestionTable.COLUMN_OPTION1 +" TEXT, "+
                QuestionTable.COLUMN_OPTION2 +" TEXT, "+
                QuestionTable.COLUMN_OPTION3 +" TEXT, "+
                QuestionTable.COLUMN_OPTION4 +" TEXT, "+
                QuestionTable.COLUMN_ANSWER_NR +" INTEGER, "+
                QuestionTable .COLUMN_HINT + " TEXT, " +
                QuestionTable .COLUMN_TOKEN + " TEXT " +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        db.execSQL(Level.CREATE_TABLE);
        fillQuestionTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ QuestionTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Level.TABLE_NAME);
        onCreate(db);



    }
    private void fillQuestionTable()
    {
        Question q1 = new Question("The squares of which of the following numbers would be an odd number?", "726", "158", "269", "1980", 3, "Ans 3 is correct", "1");
        addQuestion(q1);
        Question q2 = new Question("How many zeros will be there  in square of 90 ?", "2", "3", "4", "6", 1, "Ans 1 is correct ", "1");
        addQuestion(q2);
        Question q3 = new Question("What will be the unit digit of the square root of 9150625", "1", "0", "5", "3", 3, "Ans 3 is correct Ans", "1");
        addQuestion(q3);
        Question q4 = new Question(" Find the smallest whole number by which 2028 should be multiplied so as to get a perfect square number ?", "2", "3", "11", "13", 2, "Ans B is correct", "1");
        addQuestion(q4);
        Question q5 = new Question("Find the square root of 12.25 ", "35", "0.35", "3.5", "0.035", 3, "Ans C is Correct", "1");
        addQuestion(q5);
        Question q6 = new Question("There are 500 children in a school. For a P.T. drill they have to stand in such a manner that the number of rows is equal to the number of columns. How many children would be left out in this arrangement?", "16", "24", "32", "8", 1, "Ans A us correct", "1");
        addQuestion(q6);
        Question q7 = new Question(" Find the smallest whole number by which 704 must be divided to get a perfect cube.", "2", "3", "9", "11", 4, "Option D is corrext", "1");
        addQuestion(q7);
        Question q8 = new Question("What will be the unit digit of the cube of 149", "5", "7", "8", "9", 4, "Ans D is correct", "1");
        addQuestion(q8);
        Question q9 = new Question("Find the ratio of 5 paisa to 5 Rupees.", "1:1000", "1:100", "1.1", "1.10", 2, "Ans B is correct", "1");
        addQuestion(q9);
        Question q10 = new Question("Waheeda bought an air cooler for Rs. 3300 including VAT(Value Added Tax) of 10%. Find the price of air cooler before VAT was added.", "3320 Rs.", "3000 Rs", "3150 Rs", "3050 Rs", 2, "Ans B is correct", "1");
        addQuestion(q10);
        Question q11 = new Question("Find the smallest number by which 68600 must be multiplied to get a perfect cube?", "10", "5", "7", "2", 2, "Ans B is correct", "1");
        addQuestion(q11);
        Question q12 = new Question("In a right  triangle ABC , B =9 and AB=6 cm , BC= 8cm . Find the value of AC.", "10 cm", "12 cm", "7 cm", "15 cm", 1, "Ans A is correct", "1");
        addQuestion(q12);
        Question q13 = new Question("Find the greatest number of four digits which is a perfect square.", "9999", "9801", "9900", "9000", 1, "Ans A is correct ans ", "1");
        addQuestion(q13);
        Question q14 = new Question("Solve:  6= z + 2", "4", "-4", "8", "-8", 1, "Ans A is correct", "2");
        addQuestion(q14);
        Question q15 = new Question("Sum of two numbers is 95 . If one exceeds the other by 15 then find the smaller number out of these two numbers.", "55", "50", "40", "35", 3, "Ans 3 is correct ", "2");
        addQuestion(q15);
        Question q16 = new Question("Solve the equation:  8x-3/3x=2", "3/2", "-3/2", "3/14", "-3/14", 1, "Ans 1 is correct ", "2");
        addQuestion(q16);
        Question q17 = new Question("Find the area of a rectangle whose length and breadth is 3mn and 4np units  respectively.", "12mnp² units", "7mnp² units", "12mn²p units ", "12 m²np sq. units", 3, "Ans 3 is correct", "2");
        addQuestion(q17);
        Question q18 = new Question("Factorize: z-7+7xy-xyz. ", "(Z+7)(1-xy)", "(z+7)(1+xy)", "(z-7)(1-xy)", "(z+7)(1+xy)", 3, "Ans 3 is coorect", "2");
        addQuestion(q18);
        Question q19 = new Question("Simplify 10y(6y+21) ÷ 5(2y+7)", "6y", "3y(2y+7)", "3y", "6y(sq)2", 1, "Ans A is correct", "2");
        addQuestion(q19);
        Question q20 = new Question(" Subtract  x²– y² from  x² + y² ", "0", "2x²", "2y²", "x²-y²", 3, "Ans C is correct", "3");
        addQuestion(q20);
        Question q21 = new Question("Find the height of cylinder whose radius is 7cm and the total surface area is 968cm².", "15 cm", "6 cm", "14 cm", "8 cm", 1, "Ans A is correct", "3");

        addQuestion(q21);
        Question q23 = new Question("Diagonals of a rhombus are 7.5 cm and 12cm long. Find its area.", "35 cm²", "45 cm²", "36cm²", "90 cm²", 2, "Ans B is correct", "3");
        addQuestion(q23);
        Question q24 = new Question("1 Litre  = ____________ ", "1 m", "1 cm", "1 dm", "1 mm", 3, "Ans C is correct", "3");
        addQuestion(q24);
        Question q25 = new Question("If side of a cube is doubled then how many times its total surface area will increased? ", "4", "9", "2", "8", 4, "Ans D is correct", "3");
        addQuestion(q25);
        Question q26 = new Question("Area of a circle is 16 cm2 , Find its circumference. ", "8 2", " 8 π²", "36 π²", "8 π²", 4, "Ans D is correct  π (²)", "4");
        addQuestion(q26);
        Question q27 = new Question("If 21y5 is a multiple of 9, where y is a digit, what is  the value of y?", "2", "1", "0", "3", 2, "Ans B is correct", "4");
        addQuestion(q27);
        Question q28 = new Question("Which of the following is smallest? ", "12-9/3", "12+9\u0003", "12-9*3", "12×9/3", 1, "Ans A is correct", "4");
        addQuestion(q28);
        Question q29 = new Question("Which of the following is greatest?", "-108", "-600", "0", "-1", 3, "Ans C is correct", "4");
        addQuestion(q29);
        Question q30 = new Question("A train is moving at a uniform speed of 75 km/h. How far will it travel in 20 minutes?", "25 km", "15 km", "35 km", "45 km", 1, "Ans A is correct", "4");
        addQuestion(q30);
        Question q31 = new Question("Reciprocal of -1 is _____", "-1", "0", "1", "none", 1, " ans A is correct", "4");

        addQuestion(q31);



    }

    private void addQuestion(Question question)
    {
        ContentValues cv= new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1,question.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2,question.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3,question.getOption3());
        cv.put(QuestionTable.COLUMN_OPTION4,question.getOption4());
        cv.put(QuestionTable.COLUMN_ANSWER_NR,question.getAnswerNr());
        cv.put(QuestionTable.COLUMN_HINT,question.getHint());
        cv.put(QuestionTable.COLUMN_TOKEN,question.getToken());

        db.insert(QuestionTable.TABLE_NAME,null,cv);


    }

    //    public ArrayList<Question> getAllQuestions(String levelID)
         public ArrayList<Question> getAllQuestions(String levelID)
    {
        ArrayList<Question> questionList=new ArrayList<>();
        db = getReadableDatabase();

        String level_id = levelID;

//         levelID = Level.COLUMN_LEVEL_ID;
        System.out.println("mona= "+level_id);


        //SELECT * FROM " + QuestionTable.TABLE_NAME + " where token=" + levelID

        Cursor c = db.rawQuery("SELECT * FROM quiz_questions WHERE token = ? ", new String[]{level_id});
        if (c.moveToFirst())
        {
            do {
                Question question=new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                question.setHint(c.getString(c.getColumnIndex(QuestionTable.COLUMN_HINT)));
                questionList.add(question);

            }while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

    public void addLevel(Level level) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Level.COLUMN_LEVEL_NAME, level.getTitle()); // Level Name
        values.put(Level.COLUMN_LEVEL_ID, level.getLevelId());

        System.out.println("hlwId="+level.getLevelId());


        // Inserting Row
        db.insert(Level.TABLE_NAME, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }


    public List<Level> getAllLevel() {
        List<Level> notes = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + Level.TABLE_NAME ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Level level = new Level();
                level.setLevelId(cursor.getString(cursor.getColumnIndex(Level.COLUMN_LEVEL_ID)));
                level.setTitle(cursor.getString(cursor.getColumnIndex(Level.COLUMN_LEVEL_NAME)));
             //   level.setScore(cursor.getString(cursor.getColumnIndex(Level.COLUMN_LEVEL_FLAG)));
                notes.add(level);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public int getLevelCount() {
        String countQuery = "SELECT  * FROM " + Level.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public void deleteLevel() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Level.TABLE_NAME,null,null);
        db.close();
    }


    public int updateLevel(String level) {
        SQLiteDatabase db = this.getWritableDatabase();
        String levelId= level;

        ContentValues values = new ContentValues();
        values.put(Level.COLUMN_LEVEL_FLAG, "1");

        // updating row
        return db.update(Level.TABLE_NAME, values, Level.COLUMN_LEVEL_ID + " = ?",
                new String[]{String.valueOf(level)});
    }


}
