package de.goddchen.android.mvvmsample.mvvm.model

import java.io.Serializable

data class Geo(
        var lat: Double? = null,
        var lng: Double? = null
) : Serializable