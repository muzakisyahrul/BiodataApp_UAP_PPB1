package mobile.muzaki.biodataapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BiodataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun tambahData(biodata: Biodata)

    @Update
    fun updateData(biodata: Biodata)

    @Query("SELECT * FROM Biodata")
    fun getBiodata() : LiveData<List<Biodata>>
}