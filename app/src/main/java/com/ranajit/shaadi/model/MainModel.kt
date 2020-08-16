package com.ranajit.shaadi.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class MatchesData(
    @JsonProperty("results")
    var results: ArrayList<Matches>? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Matches(
    @JsonProperty("gender")
    val gender: String? = "",
    @JsonProperty("name")
    val name: Name? = null,
    @JsonProperty("location")
    val location: Location? = null,
    @JsonProperty("email")
    val email: String? = "",
    @JsonProperty("login")
    val login: Login? = null,
    @JsonProperty("dob")
    val dob: DOB? = null,
    @JsonProperty("registered")
    val registered: Registered? = null,
    @JsonProperty("phone")
    val phone: String? = "",
    @JsonProperty("cell")
    val cell: String? = "",
    @JsonProperty("id")
    val id: Id? = null,
    @JsonProperty("picture")
    val picture: Picture? = null,
    @JsonProperty("nat")
    val nat: String? = ""
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Name(
    @JsonProperty("title") val title: String? = "",
    @JsonProperty("first") val first: String? = "",
    @JsonProperty("last") val last: String? = ""
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Location(
    @JsonProperty("street")
    val street: Street? = null,
    @JsonProperty("city")
    val city: String? = "",
    @JsonProperty("state")
    val state: String? = "",
    @JsonProperty("country")
    val country: String? = "",
    @JsonProperty("postcode")
    val postcode: String? = "",
    @JsonProperty("coordinates")
    val coordinates: Coordinates? = null,
    @JsonProperty("timezone")
    val timezone: Timezone? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Street(
    @JsonProperty("number") val number: Int? = 0,
    @JsonProperty("name") val name: String? = ""
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Coordinates(
    @JsonProperty("latitude") val latitude: Double? = 0.0,
    @JsonProperty("longitude") val longitude: Double? = 0.0
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Timezone(
    @JsonProperty("offset") val offset: String? = "",
    @JsonProperty("description") val description: String? = ""
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Login(
    @JsonProperty("uuid")
    val uuid: String? = "",
    @JsonProperty("username")
    val userName: String? = "",
    @JsonProperty("password")
    val password: String? = "",
    @JsonProperty("salt")
    val salt: String? = "",
    @JsonProperty("md5")
    val md5: String? = "",
    @JsonProperty("sha256")
    val sha256: String? = ""
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class DOB(
    @JsonProperty("date") val date: String? = "",
    @JsonProperty("age") val age: Int? = 0
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Registered(
    @JsonProperty("date") val date: String? = "",
    @JsonProperty("age") val age: Int? = 0
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Id(
    @JsonProperty("name") val name: String? = "",
    @JsonProperty("value") val value: String? = ""
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Picture(
    @JsonProperty("large") val large: String? = "",
    @JsonProperty("medium") val medium: String? = "",
    @JsonProperty("thumbnail") val thumbnail: String? = ""
)