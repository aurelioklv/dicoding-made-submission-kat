package com.aurelioklv.kat.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breed")
data class BreedEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: String,
    @ColumnInfo("is_favorite")
    var isFavorite: Boolean = false,
    @ColumnInfo("name")
    val name: String? = null,
    @ColumnInfo("origin")
    val origin: String? = null,
    @ColumnInfo("description")
    val description: String? = null,
    @ColumnInfo("alt_names")
    val altNames: String? = null,

    @ColumnInfo("country_code")
    val countryCode: String? = null,
    @ColumnInfo("country_codes")
    val countryCodes: String? = null,

    @ColumnInfo("weight_metric")
    val weightMetric: String? = null,
    @ColumnInfo("weight_imperial")
    val weightImperial: String? = null,
    @ColumnInfo("life_span")
    val lifeSpan: String? = null,

    @ColumnInfo("indoor")
    val indoor: Int? = null,
    @ColumnInfo("lap")
    val lap: Int? = null,
    @ColumnInfo("hairless")
    val hairless: Int? = null,
    @ColumnInfo("short_legs")
    val shortLegs: Int? = null,
    @ColumnInfo("suppressed_tail")
    val suppressedTail: Int? = null,
    @ColumnInfo("natural")
    val natural: Int? = null,
    @ColumnInfo("experimental")
    val experimental: Int? = null,
    @ColumnInfo("rare")
    val rare: Int? = null,
    @ColumnInfo("rex")
    val rex: Int? = null,

    @ColumnInfo("health_issues")
    val healthIssues: Int? = null,
    @ColumnInfo("shedding_level")
    val sheddingLevel: Int? = null,
    @ColumnInfo("grooming")
    val grooming: Int? = null,
    @ColumnInfo("hypoallergenic")
    val hypoallergenic: Int? = null,

    @ColumnInfo("temperament")
    val temperament: String? = null,
    @ColumnInfo("adaptability")
    val adaptability: Int? = null,
    @ColumnInfo("affection_level")
    val affectionLevel: Int? = null,
    @ColumnInfo("energy_level")
    val energyLevel: Int? = null,
    @ColumnInfo("intelligence")
    val intelligence: Int? = null,
    @ColumnInfo("social_needs")
    val socialNeeds: Int? = null,
    @ColumnInfo("vocalisation")
    val vocalisation: Int? = null,

    @ColumnInfo("stranger_friendly")
    val strangerFriendly: Int? = null,
    @ColumnInfo("child_friendly")
    val childFriendly: Int? = null,
    @ColumnInfo("dog_friendly")
    val dogFriendly: Int? = null,

    @ColumnInfo("wikipedia_url")
    val wikipediaUrl: String? = null,
    @ColumnInfo("vetstreet_url")
    val vetstreetUrl: String? = null,
    @ColumnInfo("vcahospitals_url")
    val vcahospitalsUrl: String? = null,
    @ColumnInfo("cfa_url")
    val cfaUrl: String? = null,
    @ColumnInfo("image_url")
    val imageUrl: String? = null,
)