package de.goddchen.android.mvvmsample.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

import org.threeten.bp.LocalDateTime
import java.io.Serializable

@Entity
data class Chapter(
        @PrimaryKey
        @SerializedName("_id")
        var id: String,

        var created_at: LocalDateTime? = null,

        var updated_at: LocalDateTime? = null,

        var site: String? = null,

        var status: String? = null,

        var group_type: String? = null,

        @Embedded(prefix = "country_")
        var country: Country? = null,

        var state: String? = null,

        var city: String? = null,

        var name: String? = null,

        @SerializedName("__v")
        var v: String? = null,

        @SerializedName("organizers")
        var organizerIds: List<String>? = null,

        @Embedded(prefix = "geo_")
        var geo: Geo? = null
) : Serializable