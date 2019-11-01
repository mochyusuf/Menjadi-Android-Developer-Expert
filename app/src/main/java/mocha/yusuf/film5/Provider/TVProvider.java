package mocha.yusuf.film5.Provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Objects;

import mocha.yusuf.film5.Database.TVContract;
import mocha.yusuf.film5.Database.TVHelper;

import static mocha.yusuf.film5.Database.TVContract.AUTHORITY;
import static mocha.yusuf.film5.Database.TVContract.CONTENT_URI;

public class TVProvider extends ContentProvider {
    private static final int TV = 300;
    private static final int TV_ID = 301;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY,
                TVContract.TVColumns.TABLE_TV, TV);
        sUriMatcher.addURI(AUTHORITY,
                TVContract.TVColumns.TABLE_TV+ "/#",
                TV_ID);
    }

    private TVHelper tvHelper;

    @Override
    public boolean onCreate() {
        tvHelper = new TVHelper(getContext());
        tvHelper.open();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;
        int match = sUriMatcher.match(uri);
        switch(match){
            case TV:
                cursor = tvHelper.queryProvider();
                break;
            case TV_ID:
                cursor = tvHelper.queryByIdProvider(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }

        if (cursor != null){
            cursor.setNotificationUri(getContext().getContentResolver(),uri);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long added ;

        switch (sUriMatcher.match(uri)){
            case TV:
                added = tvHelper.insertProvider(values);
                break;
            default:
                added = 0;
                break;
        }

        if (added > 0) {
            Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        }
        return Uri.parse(CONTENT_URI + "/" + added);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int movieDeleted;

        int match = sUriMatcher.match(uri);
        switch (match) {
            case TV_ID:
                movieDeleted =  tvHelper.deleteProvider(uri.getLastPathSegment());
                break;
            default:
                movieDeleted = 0;
                break;
        }

        if (movieDeleted > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return movieDeleted;

    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        int movieUpdated ;
        switch (sUriMatcher.match(uri)) {
            case TV_ID:
                movieUpdated =  tvHelper.updateProvider(uri.getLastPathSegment(),values);
                break;
            default:
                movieUpdated = 0;
                break;
        }

        if (movieUpdated > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return movieUpdated;
    }
}
