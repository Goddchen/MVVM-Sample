package de.goddchen.android.mvvmsample.model

import com.google.gson.annotations.SerializedName

import org.threeten.bp.LocalDateTime

data class Chapter(
        @SerializedName("_id")
        var id: String? = null,

        var created_at: LocalDateTime? = null,

        var updated_at: LocalDateTime? = null,

        var site: String? = null,

        var status: String? = null,

        var group_type: String? = null,

        var country: Country? = null,

        var state: String? = null,

        var city: String? = null,

        var name: String? = null,

        @SerializedName("__v")
        var v: String? = null,

        @SerializedName("organizers")
        var organizerIds: List<String>? = null,

        var geo: Geo? = null
)