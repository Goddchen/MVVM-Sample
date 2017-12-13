package de.goddchen.android.mvvmsample.model

import java.io.Serializable

data class Geo(
        val lat: Double? = null,
        val lng: Double? = null
) : Serializable