package com.aurelioklv.kat.core.utils.mapper

import com.aurelioklv.kat.core.data.local.entity.BreedEntity
import com.aurelioklv.kat.core.data.remote.response.NetworkBreed
import com.aurelioklv.kat.core.domain.model.Breed

object BreedNetworkToEntityMapper : Mapper<NetworkBreed, BreedEntity> {
    override fun map(from: NetworkBreed): BreedEntity {
        return BreedEntity(
            id = from.id.toString(),
            name = from.name,
            origin = from.origin,
            description = from.description,
            altNames = from.altNames,

            countryCode = from.countryCode,
            countryCodes = from.countryCodes,

            weightMetric = from.weight?.metric,
            weightImperial = from.weight?.imperial,
            lifeSpan = from.lifeSpan,

            indoor = from.indoor,
            lap = from.lap,
            hairless = from.hairless,
            shortLegs = from.shortLegs,
            suppressedTail = from.suppressedTail,
            natural = from.natural,
            experimental = from.experimental,
            rare = from.rare,
            rex = from.rex,

            healthIssues = from.healthIssues ?: -1,
            sheddingLevel = from.sheddingLevel,
            grooming = from.grooming,
            hypoallergenic = from.hypoallergenic,

            temperament = from.temperament,
            adaptability = from.adaptability,
            affectionLevel = from.affectionLevel,
            energyLevel = from.energyLevel,
            intelligence = from.intelligence,
            socialNeeds = from.socialNeeds,
            vocalisation = from.vocalisation,

            strangerFriendly = from.strangerFriendly,
            childFriendly = from.childFriendly,
            dogFriendly = from.dogFriendly,

            wikipediaUrl = from.wikipediaUrl,
            vetstreetUrl = from.vetstreetUrl,
            vcahospitalsUrl = from.vcahospitalsUrl,
            cfaUrl = from.cfaUrl,
            imageUrl = from.referenceImageId?.let { "https://cdn2.thecatapi.com/images/$it.jpg" }
        )
    }
}

object BreedEntityToModelMapper : Mapper<BreedEntity, Breed> {
    override fun map(from: BreedEntity): Breed {
        return Breed(
            id = from.id,
            isFavorite = from.isFavorite,
            name = from.name,
            origin = from.origin,
            description = from.description,
            altNames = from.altNames,

            countryCode = from.countryCode,
            countryCodes = from.countryCodes,

            weightMetric = from.weightMetric,
            weightImperial = from.weightImperial,
            lifeSpan = from.lifeSpan,

            indoor = from.indoor?.let { it == 1 },
            lap = from.lap?.let { it == 1 },
            hairless = from.hairless?.let { it == 1 },
            shortLegs = from.shortLegs?.let { it == 1 },
            suppressedTail = from.suppressedTail?.let { it == 1 },
            natural = from.natural?.let { it == 1 },
            experimental = from.experimental?.let { it == 1 },
            rare = from.rare?.let { it == 1 },
            rex = from.rex?.let { it == 1 },

            healthIssues = from.healthIssues ?: -1,
            sheddingLevel = from.sheddingLevel,
            grooming = from.grooming,
            hypoallergenic = from.hypoallergenic,

            temperament = from.temperament,
            adaptability = from.adaptability,
            affectionLevel = from.affectionLevel,
            energyLevel = from.energyLevel,
            intelligence = from.intelligence,
            socialNeeds = from.socialNeeds,
            vocalisation = from.vocalisation,

            strangerFriendly = from.strangerFriendly,
            childFriendly = from.childFriendly,
            dogFriendly = from.dogFriendly,

            wikipediaUrl = from.wikipediaUrl,
            vetstreetUrl = from.vetstreetUrl,
            vcahospitalsUrl = from.vcahospitalsUrl,
            cfaUrl = from.cfaUrl,
            imageUrl = from.imageUrl,
        )
    }
}

object BreedModelToEntityMapper : Mapper<Breed, BreedEntity> {
    override fun map(from: Breed): BreedEntity {
        return BreedEntity(
            id = from.id,
            name = from.name,
            origin = from.origin,
            description = from.description,
            altNames = from.altNames,

            countryCode = from.countryCode,
            countryCodes = from.countryCodes,

            weightMetric = from.weightMetric,
            weightImperial = from.weightImperial,
            lifeSpan = from.lifeSpan,

            indoor = from.indoor?.let { if (it) 1 else 0 },
            lap = from.lap?.let { if (it) 1 else 0 },
            hairless = from.hairless?.let { if (it) 1 else 0 },
            shortLegs = from.shortLegs?.let { if (it) 1 else 0 },
            suppressedTail = from.suppressedTail?.let { if (it) 1 else 0 },
            natural = from.natural?.let { if (it) 1 else 0 },
            experimental = from.experimental?.let { if (it) 1 else 0 },
            rare = from.rare?.let { if (it) 1 else 0 },
            rex = from.rex?.let { if (it) 1 else 0 },

            healthIssues = from.healthIssues ?: -1,
            sheddingLevel = from.sheddingLevel,
            grooming = from.grooming,
            hypoallergenic = from.hypoallergenic,

            temperament = from.temperament,
            adaptability = from.adaptability,
            affectionLevel = from.affectionLevel,
            energyLevel = from.energyLevel,
            intelligence = from.intelligence,
            socialNeeds = from.socialNeeds,
            vocalisation = from.vocalisation,

            strangerFriendly = from.strangerFriendly,
            childFriendly = from.childFriendly,
            dogFriendly = from.dogFriendly,

            wikipediaUrl = from.wikipediaUrl,
            vetstreetUrl = from.vetstreetUrl,
            vcahospitalsUrl = from.vcahospitalsUrl,
            cfaUrl = from.cfaUrl,
            imageUrl = from.imageUrl,
        )
    }
}