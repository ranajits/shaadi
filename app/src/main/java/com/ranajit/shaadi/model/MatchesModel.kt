package com.ranajit.shaadi.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ranajit.shaadi.utility.DateConverter

@Entity(tableName = "matches")
@TypeConverters(DateConverter::class)
data class MatchesModel(
    @PrimaryKey
    val email: String,
    var gender: String = "",
    var title: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var streetNumber: String = "",
    var streetName: String = "",
    var city: String = "",
    var state: String = "",
    var country: String = "",
    var postCode: String = "",
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var timeZone: String = "",
    var timeZoneDesc: String = "",
    var userName: String = "",
    var dobDate: String? = null,
    var dobAge: Int = 0,
    var regDate: String? = null,
    var regAge: Int = 0,
    var phone: String = "",
    var cell: String = "",
    var idName: String = "",
    var idValue: String = "",
    var pictureLarge: String = "",
    var pictureMedium: String = "",
    var picturethumbnail: String = "",
    var nat: String = "",
    var isAccepted: Int = -1,
    var dateAdded: String? = "",
    var dateModified: String? = ""
)