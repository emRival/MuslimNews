package com.rival.muslimnews.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rival.muslimnews.ui.home.AboutAlQuranFragment
import com.rival.muslimnews.ui.home.AlJazeraFragment
import com.rival.muslimnews.ui.home.CommonFragment
import com.rival.muslimnews.ui.home.WarningFragment

class SectionPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CommonFragment()
            1 -> AboutAlQuranFragment()
            2 -> AlJazeraFragment()
            3 -> WarningFragment()
            else -> CommonFragment()
        }
    }


}