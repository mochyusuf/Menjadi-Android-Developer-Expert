package mocha.yusuf.film5.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import mocha.yusuf.film5.Model.TVModel;

@Dao
public interface TVDao {
    @Query("SELECT * from TVs ORDER BY name ASC")
    List<TVModel> getTVs();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertTV(TVModel tvModel);

    @Query("SELECT * from TVs WHERE id = :id")
    TVModel checkTV(int id);

    @Delete
    int deleteTV(TVModel tvModel);

    @Query("DELETE FROM TVs")
    void deleteAllTV();
}
