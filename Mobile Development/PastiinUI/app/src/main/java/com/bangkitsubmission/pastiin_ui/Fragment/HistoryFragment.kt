package com.bangkitsubmission.pastiin_ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.bangkitsubmission.pastiin_ui.Adapter.ViewPagerAdapter
import com.bangkitsubmission.pastiin_ui.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class HistoryFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private lateinit var root: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_history, container, false)
        ShowViewPager(root)
        return root
    }
    private fun ShowViewPager(root: View) {
        var tabs: TabLayout = root.findViewById(R.id.hisorytabLayout)
        var viewPager: ViewPager2   = root.findViewById(R.id.historyviewPager)
        var adapterthis = ViewPagerAdapter(this)
        viewPager.adapter = adapterthis
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()


    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.history_tab_text_1,
            R.string.history_tab_text_2
        )
    }
}