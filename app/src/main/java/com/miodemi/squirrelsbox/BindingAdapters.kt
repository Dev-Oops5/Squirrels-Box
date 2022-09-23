package com.miodemi.squirrelsbox

import android.webkit.WebView
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.miodemi.squirrelsbox.data.models.BoxData
import com.miodemi.squirrelsbox.detail.homebox.HomeBoxAdapter

// region RecyclerView
@BindingAdapter("setItems")
fun setItems(recyclerView: RecyclerView, list: List<BoxData>?) {
    (recyclerView.adapter as HomeBoxAdapter).setItems(list)
}
// endregion RecyclerView