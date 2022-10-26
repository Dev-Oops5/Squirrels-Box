package com.miodemi.squirrelsbox.inventory

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.miodemi.squirrelsbox.inventory.data.BoxData
import com.miodemi.squirrelsbox.inventory.data.ItemData
import com.miodemi.squirrelsbox.inventory.data.SectionData
import com.miodemi.squirrelsbox.inventory.navigation.home.HomeBoxAdapter
import com.miodemi.squirrelsbox.inventory.navigation.home.HomeItemAdapter
import com.miodemi.squirrelsbox.inventory.navigation.home.HomeSectionAdapter

// region RecyclerView
@BindingAdapter("setHomeBoxItems")
fun setHomeBoxItems(recyclerView: RecyclerView, list: List<BoxData>?) {
    (recyclerView.adapter as HomeBoxAdapter).setHomeBoxItems(list)
}
// endregion RecyclerView
@BindingAdapter("setHomeSectionItems")
fun setHomeSectionItems(recyclerView: RecyclerView, list: List<SectionData>?) {
    (recyclerView.adapter as HomeSectionAdapter).setHomeSectionItems(list)
}

@BindingAdapter("setHomeItemItems")
fun setHomeItemItems(recyclerView: RecyclerView, list: List<ItemData>?) {
    (recyclerView.adapter as HomeItemAdapter).setHomeItemItems(list)
}