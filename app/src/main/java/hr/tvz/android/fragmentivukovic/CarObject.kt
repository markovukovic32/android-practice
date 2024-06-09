package hr.tvz.android.fragmentivukovic

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class CarObject(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "model") val model: String?,
    @ColumnInfo(name = "year") val year: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "image") val image: Int?
) : Parcelable{}