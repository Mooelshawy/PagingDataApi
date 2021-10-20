package com.omran.pagingdataapi.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

class Utils {

    companion object{
        fun View.visible(isVisible: Boolean) {
            visibility = if (isVisible) View.VISIBLE else View.GONE
        }

        fun ImageView.loadImage(url: String) {
            Glide.with(this)
                .load(url)
                .into(this)
        }
    }

}