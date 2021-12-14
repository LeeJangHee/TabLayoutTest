package com.devlee.testtablayout.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devlee.testtablayout.data.TabData
import kotlinx.coroutines.launch

class TabViewModel : ViewModel() {

    var tabDataLiveData: MutableLiveData<TabFragmentData> = MutableLiveData(TabFragmentData.Nil)

    var tabPositionLiveData: MutableLiveData<Int> = MutableLiveData(0)

    fun getTabData(tabData: TabData?) {
        viewModelScope.launch {
            when {
                tabData == null -> tabDataLiveData.postValue(TabFragmentData.Nil)
                tabData.type == 0 -> tabDataLiveData.postValue(TabFragmentData.FullSwingData(tabData))
                tabData.type == 1 -> tabDataLiveData.postValue(TabFragmentData.ApproachData(tabData))
                tabData.type == 2 -> tabDataLiveData.postValue(TabFragmentData.PuttData(tabData))
            }
        }
    }


}

sealed class TabFragmentData {
    object Nil : TabFragmentData()
    data class FullSwingData(val tabData: TabData?) : TabFragmentData()
    data class ApproachData(val tabData: TabData?) : TabFragmentData()
    data class PuttData(val tabData: TabData?) : TabFragmentData()
}