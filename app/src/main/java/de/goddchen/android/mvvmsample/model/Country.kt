package de.goddchen.android.mvvmsample.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Country(
        @SerializedName("_id") val id: String? = null,
        val name: String? = null
) : Serializable