package mocha.yusuf.film5.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import mocha.yusuf.film5.Model.MovieModel;

@Dao
public interface MovieDao {
    @Query("SELECT * from Movies ORDER BY title ASC")
    List<MovieModel> getMovies();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertMovie(MovieModel movieModel);

    @Query("SELECT * from Movies WHERE id = :id")
    MovieModel checkMovies(int id);

    @Delete
    int deleteMovie(MovieModel movieModel);

    @Query("DELETE FROM Movies")
    void deleteAllMovie();
}
