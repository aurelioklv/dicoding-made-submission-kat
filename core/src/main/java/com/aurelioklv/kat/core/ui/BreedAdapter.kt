package com.aurelioklv.kat.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aurelioklv.kat.core.R
import com.aurelioklv.kat.core.databinding.ItemBreedBinding
import com.aurelioklv.kat.core.domain.model.Breed
import com.bumptech.glide.Glide

class BreedAdapter(private val onClick: ((Breed) -> Unit)? = null) :
    RecyclerView.Adapter<BreedAdapter.ViewHolder>() {
    private var listData = ArrayList<Breed>()

    fun setData(newListData: List<Breed>?) {
        if (newListData == null) return
        val diffCallback = BreedDiffCallback(listData, newListData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        listData.clear()
        listData.addAll(newListData)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(private val binding: ItemBreedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(breed: Breed) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(breed.imageUrl)
                    .placeholder(R.drawable.progress)
                    .error(R.drawable.img_box_light)
                    .into(ivBreedPhoto)
                tvBreedName.text = breed.name
                tvOrigin.text = breed.origin
                binding.root.setOnClickListener {
                    onClick?.invoke(breed)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemBreedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listData[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}

class BreedDiffCallback(
    private val oldList: List<Breed>,
    private val newList: List<Breed>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}