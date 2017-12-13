package de.goddchen.android.mvvmsample.model

import com.google.gson.annotations.SerializedName

data class Country(
        @SerializedName("_id") val id: String? = null,
        val name: String? = null
)