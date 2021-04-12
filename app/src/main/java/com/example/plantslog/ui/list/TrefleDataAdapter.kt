package com.example.plantslog.ui.list



import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.plantslog.R
import com.example.plantslog.data.PlantData
import com.example.plantslog.databinding.ItemListBinding


class TrefleDataAdapter: PagingDataAdapter<PlantData.Data, TrefleDataAdapter.ViewHolder>(PLANT_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)

        if(currentItem != null) {
            holder.bind(currentItem)
        }

    }

    class ViewHolder(private var binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

            @SuppressLint("SetTextI18n")
            fun bind(plant: PlantData.Data) {
                val options: RequestOptions = RequestOptions()
                    .centerCrop()
                        .fallback(R.drawable.ic_no_image)
                        .error(R.drawable.ic_error)


                binding.apply {
                    Glide.with(itemView)
                            .load(plant.image_url)
                            .apply(options)
                            .transition(DrawableTransitionOptions.withCrossFade())

//                        .error(Glide.with(itemView).load(R.drawable.ic_error))
                            .into(ivPlant)
                    tvCommonName.text = "Name: ${plant.common_name}"
                    tvFamily.text = "Family: ${plant.family}"
                    tvGenus.text = "Genus: ${plant.genus}"



                }
            }
        }

    companion object {
        private val PLANT_COMPARATOR = object: DiffUtil.ItemCallback<PlantData.Data>() {
            override fun areItemsTheSame(oldItem: PlantData.Data, newItem: PlantData.Data) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PlantData.Data, newItem: PlantData.Data) =
                oldItem == newItem
        }
    }
}
