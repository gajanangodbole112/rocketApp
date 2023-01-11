package com.gajanan.rocketapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.gajanan.rocketapp.R
import com.gajanan.rocketapp.databinding.ItemlistOfRocketsBinding
import com.gajanan.rocketapp.modalClass.RocketDetailResponse

class RocketAdapter(
    private val context : Context,
   private val onItemClick: (String) -> Unit
) : ListAdapter<RocketDetailResponse, RocketAdapter.RocketVH>(RocketDiffUtil()) {

    inner class RocketVH(private val binding: ItemlistOfRocketsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RocketDetailResponse) {
            binding.apply {

                tvName.text = item.name
                tvEngineCount.text = context.getString(R.string.engine_cound,item.engines?.number)
                tvCountry.text = item.country
                iv.load(item.flickr_images?.get(0)){
                    transformations(RoundedCornersTransformation(10f))
                }

                root.setOnClickListener { onItemClick(item.id) }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketVH =
        RocketVH(
            ItemlistOfRocketsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RocketAdapter.RocketVH, position: Int) {
        holder.bind(getItem(position))
    }


    class RocketDiffUtil : DiffUtil.ItemCallback<RocketDetailResponse>() {
        override fun areItemsTheSame(
            oldItem: RocketDetailResponse,
            newItem: RocketDetailResponse
        ): Boolean = oldItem.id == newItem.id


        override fun areContentsTheSame(
            oldItem: RocketDetailResponse,
            newItem: RocketDetailResponse
        ): Boolean = oldItem == newItem

    }
}