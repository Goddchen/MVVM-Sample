package de.goddchen.android.mvvmsample.model

data class ChapterResponse(
        val count: Int,
        val pages: Int,
        val page: Int,
        val perpage: Int,
        val items: List<Chapter>
)