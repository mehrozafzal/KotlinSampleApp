package com.example.mobiquitytask.view.category

import com.example.mobiquitytask.data.model.api.ProductsItem
import com.example.mobiquitytask.view.base.BaseHelper

interface MainHelper : BaseHelper{
    //fun showToast(message: String?)
    fun onCategoryItemSelected(productsItem: ProductsItem)
}