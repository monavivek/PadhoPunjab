package c2d.com.codetodevelop.level;

/**
 * Created by Mona on 17/07/19.
 */
public class Level {

    private String score;
    private String title, levelId;
    public static final String TABLE_NAME = "level";

    public static final String COLUMN_LEVEL_ID = "levelId";
    public static final String COLUMN_LEVEL_NAME = "levelName";
    public static final String COLUMN_LEVEL_FLAG = "score";

    public static final String COLUMN_ID = "id";


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_LEVEL_ID + " TEXT, "
                    + COLUMN_LEVEL_NAME + " TEXT"
                    + COLUMN_LEVEL_FLAG + " TEXT"
                    + ")";

    public Level() {
    }

//    public Level( String title, String levelId) {
//        this.title = title;
//        this.levelId = levelId;
//
//    }


    public Level(String score, String title, String levelId) {
        this.score = score;
        this.title = title;
        this.levelId = levelId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }
}



