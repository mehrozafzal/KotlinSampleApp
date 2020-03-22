package com.example.mobiquitytask.view.category

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.mobiquitytask.R
import com.example.mobiquitytask.data.model.api.RestaurantResponse

class CategorySpinnerAdapter internal constructor(
    context: Activity,
    resourceID: Int,
    private val restaurantList: List<RestaurantResponse>
) : ArrayAdapter<RestaurantResponse>(context, resourceID, restaurantList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return rowView(convertView, position)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View? {
        return rowDropDownView(convertView, position)
    }

    private fun rowView(convertView: View?, position: Int): View {
        val holder: CategoryViewHolder
        var rowView = convertView
        if (rowView == null) {
            holder = CategoryViewHolder()
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            rowView = inflater.inflate(R.layout.item_cateogy_spinner, null, false)
            holder.txtTitle =
                rowView.findViewById<View>(R.id.item_category_name) as TextView
            rowView.tag = holder
        } else holder =
            rowView.tag as CategoryViewHolder
        holder.bindViews(restaurantList[position])
        return rowView!!
    }

    @SuppressLint("InflateParams")
    private fun rowDropDownView(convertView: View?, position: Int): View? {
        val holder: CategoryViewHolderDropDown
        var rowView = convertView
        if (rowView == null) {
            holder = CategoryViewHolderDropDown()
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            rowView =
                inflater.inflate(R.layout.item_category_drop_down, null, false)
            holder.txtTitle =
                rowView.findViewById<View>(R.id.item_category_drop_down_name) as TextView
            rowView.tag = holder
        } else holder =
            rowView.tag as CategoryViewHolderDropDown
        holder.bindViews(restaurantList[position])
        return rowView
    }


    private inner class CategoryViewHolder {
        var txtTitle: TextView? = null
        fun bindViews(restaurantResponse: RestaurantResponse) {
            txtTitle?.text = restaurantResponse.name
        }
    }

    private inner class CategoryViewHolderDropDown {
        var txtTitle: TextView? = null
        fun bindViews(restaurantResponse: RestaurantResponse) {
            txtTitle?.text = restaurantResponse.name
        }
    }

}