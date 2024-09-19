package com.aurelioklv.kat.core.data

import com.aurelioklv.kat.core.domain.model.Breed
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class BreedRepositoryTest {

    private lateinit var repository: FakeBreedRepository
    private lateinit var breed: Breed

    private val breedSize = 4

    @Before
    fun setUp() {
        // Add some initial data
        breed = Breed(
            id = "abys",
            name = "Abyssinian",
            origin = "Egypt",
            description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
            altNames = "",
            countryCode = "EG",
            countryCodes = "EG",
            weightImperial = "7  -  10",
            weightMetric = "3 - 5",
            lifeSpan = "14 - 15",
            indoor = false,
            lap = true,
            hairless = false,
            shortLegs = false,
            suppressedTail = false,
            natural = true,
            experimental = false,
            rare = false,
            rex = false,
            healthIssues = 2,
            sheddingLevel = 2,
            grooming = 1,
            hypoallergenic = 0,
            temperament = "Active, Energetic, Independent, Intelligent, Gentle",
            adaptability = 5,
            affectionLevel = 5,
            energyLevel = 5,
            intelligence = 5,
            socialNeeds = 5,
            vocalisation = 1,
            strangerFriendly = 5,
            childFriendly = 3,
            dogFriendly = 4,
            wikipediaUrl = "https://en.wikipedia.org/wiki/Abyssinian_(cat)",
            vetstreetUrl = "http://www.vetstreet.com/cats/abyssinian",
            vcahospitalsUrl = "https://vcahospitals.com/know-your-pet/cat-breeds/abyssinian",
            cfaUrl = "http://cfa.org/Breeds/BreedsAB/Abyssinian.aspx",
            isFavorite = false,
            imageUrl = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg"
        )
        val breeds = List(breedSize) {
            breed.copy(id = breed.id + it)
        }
        repository = FakeBreedRepository(breeds)

        repository.setFavoriteBreed(breed, true)
    }

    @Test
    fun `test getAllBreed returns all breeds`() = runTest {
        val result = repository.getAllBreed().first()
        assert(result is Resource.Success)
        assertEquals(breedSize, (result as Resource.Success).data?.size ?: false)
    }

    @Test
    fun `test getBreedById returns breed`() = runTest {
        val result = repository.getBreedById("abys1").first()
        assert(result is Resource.Success)
        assertEquals("abys1", (result as Resource.Success).data?.id ?: false)
    }

    @Test
    fun `test getFavoriteBreed returns favorites`() = runTest {
        val result = repository.getFavoriteBreed().first()
        assertEquals(1, result.size)
    }

    @Test
    fun `test setFavoriteBreed adds and removes from favorites`() = runTest {
        repository.setFavoriteBreed(breed, true)
        val favorites = repository.getFavoriteBreed().first()
        assertEquals(2, favorites.size)

        repository.setFavoriteBreed(breed, false)
        val favoritesAfterRemoval = repository.getFavoriteBreed().first()
        assertEquals(1, favoritesAfterRemoval.size)
    }
}