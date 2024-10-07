package com.android.encora.weather.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/*
Track is the database object equivalent of Result
*/
@Entity(tableName = "Weather")
data class Weather(
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "country") val country: String?,
    @ColumnInfo(name = "temperature") val temperature: String?,
    @ColumnInfo(name = "timestamp") val timestamp: String?,
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}