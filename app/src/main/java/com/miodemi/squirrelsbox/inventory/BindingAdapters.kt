package com.miodemi.squirrelsbox.inventory

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.miodemi.squirrelsbox.inventory.data.BoxData
import com.miodemi.squirrelsbox.inventory.navigation.homebox.HomeBoxAdapter

// region RecyclerView
@BindingAdapter("setItems")
fun setItems(recyclerView: RecyclerView, list: List<BoxData>?) {
    (recyclerView.adapter as HomeBoxAdapter).setItems(list)
}
// endregion RecyclerView