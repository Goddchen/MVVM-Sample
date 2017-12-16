package de.goddchen.android.mvvmsample.persistence

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId

class TypeConverters {

    @TypeConverter
    fun fromLocalDateTime(value: LocalDateTime?): Long? =
            value?.toEpochSecond(ZoneId.systemDefault().rules.getOffset(value)) ?: 0

    @TypeConverter
    fun toLocalDateTime(value: Long?): LocalDateTime? =
            LocalDateTime.ofEpochSecond(value ?: 0, 0,
                    ZoneId.systemDefault().rules.getOffset(Instant.EPOCH))

    @TypeConverter
    fun toJson(value: List<String>): String? =
            Gson().toJson(value)

    @TypeConverter
    fun fromJson(value: String?): List<String> =
            Gson().fromJson(value, object : TypeToken<List<String>>() {}.type)
}