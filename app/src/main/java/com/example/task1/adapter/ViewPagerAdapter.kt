package com.example.task1.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.task1.fragment.CouponsFragment
import com.example.task1.fragment.DepartmentFragment
import com.example.task1.fragment.MyDealsFragment

class ViewPagerAdapter(fragmentManager: FragmentManager,lifecycle:Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
       return when(position){
            0->{
                MyDealsFragment()
            }
            1->{
                CouponsFragment()
            }
            2->{
                DepartmentFragment()
            }
            else->{
                Fragment()
            }
        }
    }
}