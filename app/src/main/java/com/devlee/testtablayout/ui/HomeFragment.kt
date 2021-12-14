package com.devlee.testtablayout.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.devlee.testtablayout.data.TabData
import com.devlee.testtablayout.databinding.FragmentHomeBinding
import com.devlee.testtablayout.ui.tab.TabFragment
import com.devlee.testtablayout.ui.tab.adapter.PageAdapter
import com.devlee.testtablayout.viewmodel.TabViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.random.Random

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewPagerAdapter: PageAdapter

    private val fragments: ArrayList<Fragment> = arrayListOf()
    private var tabData: TabData? = null

    private val tabViewModel: TabViewModel by activityViewModels()

    var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        fragments.add(TabFragment.newInstance(0))
        fragments.add(TabFragment.newInstance(1))
        fragments.add(TabFragment.newInstance(2))
        viewPagerAdapter = PageAdapter(requireActivity(), fragments)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager2.apply {
            adapter = viewPagerAdapter
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, pos ->
            tab.text = "Title $pos"
        }.attach()

        binding.fab.setOnClickListener { view ->
            if (toast != null)  {
                toast?.cancel()
                toast = null
            }
            val type = Random.nextInt(3)
            val value1 = Random.nextInt(1000)
            val value2 = Random.nextInt(4000)
            val value3 = Random.nextInt(10000)

            tabViewModel.tabPositionLiveData.postValue(type)

            tabData = TabData(type, value1, value2, value3)

            Log.i("janghee", "Type: $type, value1: $value1, value2: $value2, value3: $value3")

            if (toast == null) {
                toast = Toast.makeText(view.context, "Type: $type", Toast.LENGTH_SHORT)
                toast?.show()
            }
        }

        tabViewModel.tabPositionLiveData.observe(viewLifecycleOwner) { pos ->
            switchTab(pos)
            tabViewModel.getTabData(tabData)
        }

    }

    private fun switchTab(index: Int) {
        binding.tabLayout.getTabAt(index)?.select()
        binding.viewPager2.currentItem = index
    }
}