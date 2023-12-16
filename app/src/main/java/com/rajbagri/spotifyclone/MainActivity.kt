package com.rajbagri.spotifyclone

import com.rajbagri.spotifyclone.Home.Home
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.rajbagri.spotifyclone.databinding.ActivityMainBinding
import com.rajbagri.spotifyclone.searchSetup.Search

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: FragmentStateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        window.statusBarColor = resources.getColor(android.R.color.black)


        replaceFragment(Home())



        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(Home())
                R.id.search -> replaceFragment(Search())
                R.id.library -> replaceFragment(Library())
                R.id.premium -> replaceFragment(Premium())
                else -> {

                }
            }

            true
        }

    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_content, fragment)
        fragmentTransaction.commit()
    }
}