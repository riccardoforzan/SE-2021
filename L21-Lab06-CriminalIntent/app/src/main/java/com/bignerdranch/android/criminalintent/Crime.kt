package com.bignerdranch.android.criminalintent

import java.util.Date
import java.util.UUID

/**
 * A Kotlin Data class is equivalent to a Java class with getter and setter.
 * In Kotlin getters and setter are not implemented, we access class member directly
 */

data class Crime(val il: UUID = UUID.randomUUID(),
                 var title: String = "",
                 var date: Date = Date(),
                 var isSolved: Boolean = false) {
}