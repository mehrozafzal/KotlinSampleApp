package com.example.mobiquitytask.view.category

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiquitytask.R
import com.example.mobiquitytask.data.model.api.ProductsItem
import com.example.mobiquitytask.data.remote.ApiEndPoints
import com.example.mobiquitytask.databinding.ItemProductBinding
import com.example.mobiquitytask.utils.BindingUtils

class ProductAdapter(
    private val context: Context,
    private val productList: List<ProductsItem>,
    private val mainHelper: MainHelper
) : RecyclerView.Adapter<ProductAdapter.CellViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        return CellViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }


    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        holder.bind(position, productList[position])
    }


    inner class CellViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(position: Int, item: ProductsItem) {
            BindingUtils.setImageUrl(
                binding.itemRecyclerViewImage,
                ApiEndPoints.BASE_IMAGE_URL + item.url,
                R.drawable.placeholder_image
            )
            binding.itemRecyclerViewName.text = item.name
            binding.itemRecyclerViewDescription.text = item.description
            binding.itemRecyclerViewPrice.text =
                """${item.salePrice?.amount} ${item.salePrice?.currency}"""
            binding.root.setOnClickListener {
                mainHelper.onCategoryItemSelected(item)
            }
        }
    }

}