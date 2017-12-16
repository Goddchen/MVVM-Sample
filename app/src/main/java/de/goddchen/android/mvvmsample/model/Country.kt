package de.goddchen.android.mvvmsample.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Country(
        @SerializedName("_id")
        var id: String? = null,
        var name: String? = null
) : Serializable