package com.example.task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.task1.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerExample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager_example)

        val tabLayout=findViewById<TabLayout>(R.id.tab_layout);
        val viewpager=findViewById<ViewPager2>(R.id.view_pager);

        val adapter= ViewPagerAdapter(supportFragmentManager,lifecycle)
        viewpager.adapter=adapter

        TabLayoutMediator(tabLayout,viewpager){tab,position ->
            when(position){
               0->{
                   tab.text="My Deals"
               }
                1->{
                    tab.text="Coupons"
                }
                2->{
                    tab.text="Department"
                }
            }
        }.attach()

    }
}