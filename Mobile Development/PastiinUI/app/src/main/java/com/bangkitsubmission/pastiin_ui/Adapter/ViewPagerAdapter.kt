package com.bangkitsubmission.pastiin_ui.Adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bangkitsubmission.pastiin_ui.Fragment.HistoryFragment_History
import com.bangkitsubmission.pastiin_ui.Fragment.HistoryFragment_Saved

class ViewPagerAdapter(fm: Fragment): FragmentStateAdapter(fm) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null

        when (position) {
            0 -> fragment = HistoryFragment_History()
            1 -> fragment = HistoryFragment_Saved()
        }
        return fragment as Fragment
    }

}