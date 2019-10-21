package mocha.yusuf.film5.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import mocha.yusuf.film5.Model.MovieModel;
import mocha.yusuf.film5.Model.TVModel;

@Database(entities = {
        MovieModel.class,
        TVModel.class},
        version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

    public abstract TVDao tvDao();
}
