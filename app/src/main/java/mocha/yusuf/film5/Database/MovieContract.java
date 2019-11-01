package mocha.yusuf.film5.Database;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    public static final class MovieColumns implements BaseColumns {

        public static final String TABLE_MOVIE = "Movies";
        public static String MOVIE_id = "id";
        public static String MOVIE_title = "title";
        public static String MOVIE_image = "image";
        public static String MOVIE_popularity = "popularity";
        public static String MOVIE_vote_count = "vote_count";
        public static String MOVIE_poster_path = "poster_path";
        public static String MOVIE_adult = "adult";
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

    private static final String TABLE_TV = "TVs";

    public static final class TVColumns implements BaseColumns {

        public static final String TABLE_TV = "TVs";
        public static String TV_id = "id";
        public static String TV_original_name = "original_name";
        public static String TV_name = "name";
        public static String TV_popularity = "popularity";
        public static String TV_vote_count = "vote_count";
        public static String TV_first_air_date = "first_air_date";
        public static String MOVIE_adult = "adult";
        public static String MOVIE_backdrop_path = "backdrop_path";
        public static String MOVIE_original_language = "original_language";
        public static String MOVIE_original_title = "original_title";
        public static String MOVIE_vote_average = "vote_average";
        public static String MOVIE_overview = "overview";
        public static String MOVIE_release_date = "release_date";
    }

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }
}
