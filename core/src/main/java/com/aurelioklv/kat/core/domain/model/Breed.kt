package com.aurelioklv.kat.core.domain.model

data class Breed(
    val id: String,
    val isFavorite: Boolean,
    val name: String?,
    val origin: String?,
    val description: String?,
    val altNames: String?,

    val countryCode: String?,
    val countryCodes: String?,

    val weightMetric: String?,
    val weightImperial: String?,
    val lifeSpan: String?,

    val indoor: Boolean?,
    val lap: Boolean?,
    val hairless: Boolean?,
    val shortLegs: Boolean?,
    val suppressedTail: Boolean?,
    val natural: Boolean?,
    val experimental: Boolean?,
    val rare: Boolean?,
    val rex: Boolean?,

    val healthIssues: Int?,
    val sheddingLevel: Int?,
    val grooming: Int?,
    val hypoallergenic: Int?,

    val temperament: String?,
    val adaptability: Int?,
    val affectionLevel: Int?,
    val energyLevel: Int?,
    val intelligence: Int?,
    val socialNeeds: Int?,
    val vocalisation: Int?,

    val strangerFriendly: Int?,
    val childFriendly: Int?,
    val dogFriendly: Int?,

    val wikipediaUrl: String?,
    val vetstreetUrl: String?,
    val vcahospitalsUrl: String?,
    val cfaUrl: String?,
    val imageUrl: String?,
)