package com.aurelioklv.kat.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.aurelioklv.kat.R
import com.aurelioklv.kat.core.data.Resource
import com.aurelioklv.kat.core.domain.model.Breed
import com.aurelioklv.kat.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide
import org.koin.android.ext.android.inject

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val breedId = args.breedId
        viewModel.getBreedById(breedId)
        viewModel.breedDetail.observe(viewLifecycleOwner) {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        it.data?.let { breed ->
                            setupData(breed)
                            binding.viewError.root.visibility = View.GONE
                        } ?: {
                            binding.viewError.root.visibility = View.VISIBLE
                        }
                    }

                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun setupData(breed: Breed) {
        with(binding) {
            Glide.with(this@DetailFragment)
                .load(breed.imageUrl)
                .into(ivCat)

            tvName.text = breed.name
            tvOrigin.text = breed.origin
            tvLifespan.text = getString(R.string.lifespan_years, breed.lifeSpan)
            tvWeightMetric.text = getString(R.string.weight_kg, breed.weightMetric)
            tvDescription.text = breed.description

            tvIndoor.text = breed.indoor.toString()
            tvLap.text = breed.lap.toString()
            tvHairless.text = breed.hairless.toString()
            tvShortLegs.text = breed.shortLegs.toString()
            tvSuppressedTail.text = breed.suppressedTail.toString()
            tvNatural.text = breed.natural.toString()
            tvExperimental.text = breed.experimental.toString()
            tvRare.text = breed.rare.toString()
            tvRex.text = breed.rex.toString()

            tvTemperament.text = breed.temperament
            tvAdaptability.text = breed.adaptability.toString()
            tvAffectionLevel.text = breed.affectionLevel.toString()
            tvEnergyLevel.text = breed.energyLevel.toString()
            tvIntelligence.text = breed.intelligence.toString()
            tvSocialNeeds.text = breed.socialNeeds.toString()
            tvVocalisation.text = breed.vocalisation.toString()

            tvStrangerFriendly.text = breed.strangerFriendly.toString()
            tvChildFriendly.text = breed.childFriendly.toString()
            tvDogFriendly.text = breed.dogFriendly.toString()

            tvHealthIssues.text = breed.healthIssues.toString()
            tvSheddingLevel.text = breed.sheddingLevel.toString()
            tvGrooming.text = breed.grooming.toString()
            tvHypoallergenic.text = breed.hypoallergenic.toString()

            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    if (breed.isFavorite) R.drawable.baseline_favorite_24
                    else R.drawable.baseline_favorite_border_24
                )
            )
            binding.fabFavorite.setOnClickListener {
                viewModel.setFavoriteBreed(breed, !breed.isFavorite)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}