package com.devlee.testtablayout.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TabData(
    val type: Int,
    val value1: Int,
    val value2: Int,
    val value3: Int
): Parcelable
