package com.devlee.testtablayout.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devlee.testtablayout.data.TabData

class TabViewModel: ViewModel() {

    var tabDataLiveData: MutableLiveData<TabData?> = MutableLiveData(null)

}