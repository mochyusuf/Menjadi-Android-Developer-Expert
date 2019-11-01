package mocha.yusuf.favorit.Database;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class MovieContract {
    public static final class MovieColumns implements BaseColumns {

        public static final String TABLE_MOVIE = "Movies";
        public static String MOVIE_id = "id";
        public static String MOVIE_title = "title";
        public static String MOVIE_popularity = "popularity";
        public static String MOVIE_vote_count = "vote_count";
        public static String MOVIE_poster_path = "poster_path";
        public static String MOVIE_backdrop_path = "backdrop_path";
        public static String MOVIE_original_language = "original_language";
        public static String MOVIE_original_title = "original_title";
        public static String MOVIE_vote_average = "vote_average";
        public static String MOVIE_overview = "overview";
        public static String MOVIE_release_date = "release_date";
    }

    public static final String AUTHORITY = "mocha.yusuf.film5";

    private static final String TABLE_MOVIE = "Movies";
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_MOVIE)
            .build();

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static double getColumnDouble(Cursor cursor, String columnName) {
        return cursor.getDouble(cursor.getColumnIndex(columnName));
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }
}
