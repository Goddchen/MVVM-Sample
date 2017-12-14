package de.goddchen.android.mvvmsample.view.chapter

import android.content.Context
import de.goddchen.android.mvvmsample.R

class ChapterFormatProviderAndroid(
        context: Context,
        override val formatOrganizerCount: String = context.getString(R.string.format_organizer_count),
        override val formatAddress: String = context.getString(R.string.format_address)
) : ChapterFormatProvider