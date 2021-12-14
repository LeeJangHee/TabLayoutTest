package com.devlee.testtablayout.ui.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.devlee.testtablayout.databinding.FragmentTabviewBinding
import com.devlee.testtablayout.utils.Constants.TAB_BUNDLE_KEY
import com.devlee.testtablayout.viewmodel.TabFragmentData
import com.devlee.testtablayout.viewmodel.TabViewModel

class TabFragment : Fragment() {
    private var tabType: Int = 0

    private var _binding: FragmentTabviewBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: TabViewModel

    companion object {
        @JvmStatic
        fun newInstance(type: Int) = TabFragment().apply {
            arguments = Bundle().apply {
                putInt(TAB_BUNDLE_KEY, type)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            viewModel = ViewModelProvider(it).get(TabViewModel::class.java)
        }
        arguments?.let {
            tabType = it.getInt(TAB_BUNDLE_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTabviewBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.tabDataLiveData.observe(viewLifecycleOwner) {
            when {
                tabType == 0 -> {
                    when (it) {
                        is TabFragmentData.Nil -> binding.data = null
                        is TabFragmentData.FullSwingData -> binding.data = it.tabData
                    }
                }
                tabType == 1 && it is TabFragmentData.ApproachData -> binding.data = it.tabData
                tabType == 2 && it is TabFragmentData.PuttData -> binding.data = it.tabData
            }
        }
    }
}