package com.aurelioklv.kat.favorite.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aurelioklv.kat.R
import com.aurelioklv.kat.core.ui.BreedAdapter
import com.aurelioklv.kat.core.ui.DefaultItemDecoration
import com.aurelioklv.kat.databinding.ViewEmptyBinding
import com.aurelioklv.kat.favorite.databinding.FragmentFavoriteBinding
import com.aurelioklv.kat.favorite.di.favoriteModule
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private var _emptyViewBinding: ViewEmptyBinding? = null
    private val emptyViewBinding get() = _emptyViewBinding!!

    private val viewModel: FavoriteViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        _emptyViewBinding =
            ViewEmptyBinding.bind(binding.root.findViewById(com.aurelioklv.kat.favorite.R.id.empty_data))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteModule)
        getData()
    }

    private fun getData() {
        val adapter = BreedAdapter {
            val navDirections =
                FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(it.id)
            findNavController().navigate(navDirections)
        }
        binding.rvBreeds.adapter = adapter
        binding.rvBreeds.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvBreeds.addItemDecoration(
            DefaultItemDecoration(
                resources.getDimensionPixelSize(
                    R.dimen.list_item_spacing
                )
            )
        )

        viewModel.favoriteBreeds.observe(viewLifecycleOwner) { breeds ->
            adapter.setData(breeds)
            binding.progressBar.visibility = View.GONE
            emptyViewBinding.root.visibility = if (breeds.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _emptyViewBinding = null
    }
}