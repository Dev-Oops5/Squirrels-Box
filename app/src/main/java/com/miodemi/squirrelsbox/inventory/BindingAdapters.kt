package com.miodemi.squirrelsbox.inventory

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.miodemi.squirrelsbox.inventory.domain.BoxData
import com.miodemi.squirrelsbox.inventory.domain.ItemData
import com.miodemi.squirrelsbox.inventory.domain.SectionData
import com.miodemi.squirrelsbox.inventory.presentation.box.HomeBoxAdapter
import com.miodemi.squirrelsbox.inventory.presentation.item.HomeItemAdapter
import com.miodemi.squirrelsbox.inventory.presentation.section.HomeSectionAdapter

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

@BindingAdapter("setSearchBoxItems")
fun setSearchBoxItems(recyclerView: RecyclerView, list: List<BoxData>?) {
    (recyclerView.adapter as HomeBoxAdapter).setHomeBoxItems(list)
}
