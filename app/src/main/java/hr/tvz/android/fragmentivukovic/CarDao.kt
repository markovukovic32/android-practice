package hr.tvz.android.fragmentivukovic

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CarDao {
    @Query("SELECT * FROM carObject")
    fun getAll(): List<CarObject>
    @Insert
    fun insertAll(vararg cars: CarObject)
    @Delete
    fun delete(car: CarObject)
}