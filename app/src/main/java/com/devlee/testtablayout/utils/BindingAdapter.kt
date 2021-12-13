package com.devlee.testtablayout.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("numberText")
fun bindNumberText(view: TextView, value: Int?) {
    view.text = value?.toString() ?: "null"
}