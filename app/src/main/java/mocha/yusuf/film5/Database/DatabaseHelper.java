package mocha.yusuf.film5.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "favorit";

    public static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," + //MOVIE_ID
                    " %s TEXT NOT NULL," + //MOVIE_TITLE
                    " %s REAL NOT NULL," + //MOVIE_popularity
                    " %s INTEGER NOT NULL," + //MOVIE_vote_count
                    " %s TEXT NOT NULL," + //MOVIE_poster_path
                    " %s TEXT NOT NULL," + //MOVIE_backdrop_path
                    " %s TEXT NOT NULL," + //MOVIE_original_language
                    " %s TEXT NOT NULL," + //MOVIE_original_title
                    " %s REAL NOT NULL," + //MOVIE_vote_average
                    " %s TEXT NOT NULL," + //MOVIE_overview
                    " %s TEXT NOT NULL)", //MOVIE_release_date
            MovieContract.MovieColumns.TABLE_MOVIE,
            MovieContract.MovieColumns._ID,
            MovieContract.MovieColumns.MOVIE_id,
            MovieContract.MovieColumns.MOVIE_title,
            MovieContract.MovieColumns.MOVIE_popularity,
            MovieContract.MovieColumns.MOVIE_vote_count,
            MovieContract.MovieColumns.MOVIE_poster_path,
            MovieContract.MovieColumns.MOVIE_backdrop_path,
            MovieContract.MovieColumns.MOVIE_original_language,
            MovieContract.MovieColumns.MOVIE_original_title,
            MovieContract.MovieColumns.MOVIE_vote_average,
            MovieContract.MovieColumns.MOVIE_overview,
            MovieContract.MovieColumns.MOVIE_release_date
    );

    private static final String SQL_CREATE_TABLE_TV = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," + //TV_id
                    " %s TEXT NOT NULL," + //TV_original_name
                    " %s TEXT NOT NULL," + //TV_name
                    " %s REAL NOT NULL," + //TV_popularity
                    " %s INTEGER NOT NULL," + //TV_vote_count
                    " %s TEXT NOT NULL," + //TV_first_air_date
                    " %s TEXT NOT NULL," + //TV_backdrop_path
                    " %s TEXT NOT NULL," + //TV_original_language
                    " %s REAL NOT NULL," + //TV_vote_average
                    " %s TEXT NOT NULL," + //TV_overview
                    " %s TEXT NOT NULL)" , //TV_release_date
            TVContract.TVColumns.TABLE_TV,
            TVContract.TVColumns._ID,
            TVContract.TVColumns.TV_id,
            TVContract.TVColumns.TV_original_name,
            TVContract.TVColumns.TV_name,
            TVContract.TVColumns.TV_popularity,
            TVContract.TVColumns.TV_vote_count,
            TVContract.TVColumns.TV_first_air_date,
            TVContract.TVColumns.TV_backdrop_path,
            TVContract.TVColumns.TV_original_language,
            TVContract.TVColumns.TV_vote_average,
            TVContract.TVColumns.TV_overview,
            TVContract.TVColumns.TV_poster_path
    );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
        db.execSQL(SQL_CREATE_TABLE_TV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieColumns.TABLE_MOVIE);
        db.execSQL("DROP TABLE IF EXISTS " + TVContract.TVColumns.TABLE_TV);
        onCreate(db);
    }
}
