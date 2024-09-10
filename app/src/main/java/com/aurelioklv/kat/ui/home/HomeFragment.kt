package com.aurelioklv.kat.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aurelioklv.kat.R
import com.aurelioklv.kat.core.data.Resource
import com.aurelioklv.kat.core.ui.BreedAdapter
import com.aurelioklv.kat.core.ui.DefaultItemDecoration
import com.aurelioklv.kat.databinding.FragmentHomeBinding
import com.aurelioklv.kat.ui.detail.DetailFragment.Companion.KEY_BREED_ID
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
    }

    private fun getData() {
        val adapter = BreedAdapter {
            val bundle = Bundle().apply {
                putString(KEY_BREED_ID, it.id)
            }
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
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

        viewModel.breeds.observe(viewLifecycleOwner) { breeds ->
            if (breeds != null) {
                when (breeds) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        adapter.setData(breeds.data)
                    }

                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text =
                            breeds.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}