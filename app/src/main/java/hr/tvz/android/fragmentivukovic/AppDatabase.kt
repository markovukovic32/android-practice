package hr.tvz.android.fragmentivukovic

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CarObject::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun carDao(): CarDao
}