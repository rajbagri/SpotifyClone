package com.rajbagri.spotifyclone.searchSetup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.rajbagri.spotifyclone.R
import com.rajbagri.spotifyclone.databinding.FragmentSearchBinding
import com.squareup.picasso.Cache
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso


class Search : Fragment() {
    private lateinit var searchBinding: FragmentSearchBinding
    private lateinit var searchAdapter: BrowseCardAdapter
    private lateinit var browseList: ArrayList<BrowseCards>
    private lateinit var picasso: Picasso


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        searchBinding = FragmentSearchBinding.inflate(inflater, container,false)
        return searchBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apply {

            browseList = ArrayList()

            picasso = Picasso.Builder(requireContext())
                .indicatorsEnabled(true) // Enable debugging indicators
                .loggingEnabled(true)    // Enable logging
                .memoryCache(Cache.NONE)
                .downloader(OkHttp3Downloader(requireContext())) // Use OkHttp3Downloader for cachin
                .build()

            setUpList()
            searchBinding.cardViewSearch.setOnClickListener {
                val newFragment = SearchBar()
                replaceFragment(newFragment)
            }


        }
    }

    private fun setUpList() {

        val red = 0xFFFF0000.toInt()
        browseList.add(BrowseCards("Music", red, "https://w0.peakpx.com/wallpaper/243/779/HD-wallpaper-taylor-swift-1989-taylor-swift-ts.jpg"))
        browseList.add(BrowseCards("Music", red, "https://w0.peakpx.com/wallpaper/243/779/HD-wallpaper-taylor-swift-1989-taylor-swift-ts.jpg"))
        browseList.add(BrowseCards("Music", red, "https://w0.peakpx.com/wallpaper/243/779/HD-wallpaper-taylor-swift-1989-taylor-swift-ts.jpg"))
        browseList.add(BrowseCards("Music", red, "https://w0.peakpx.com/wallpaper/243/779/HD-wallpaper-taylor-swift-1989-taylor-swift-ts.jpg"))
        browseList.add(BrowseCards("Music", red, "https://w0.peakpx.com/wallpaper/243/779/HD-wallpaper-taylor-swift-1989-taylor-swift-ts.jpg"))
        browseList.add(BrowseCards("Music", red, "https://w0.peakpx.com/wallpaper/243/779/HD-wallpaper-taylor-swift-1989-taylor-swift-ts.jpg"))
        browseList.add(BrowseCards("Music", red, "https://w0.peakpx.com/wallpaper/243/779/HD-wallpaper-taylor-swift-1989-taylor-swift-ts.jpg"))
        browseList.add(BrowseCards("Music", red, "https://w0.peakpx.com/wallpaper/243/779/HD-wallpaper-taylor-swift-1989-taylor-swift-ts.jpg"))
        browseList.add(BrowseCards("Music", red, "https://w0.peakpx.com/wallpaper/243/779/HD-wallpaper-taylor-swift-1989-taylor-swift-ts.jpg"))
        browseList.add(BrowseCards("Music", red, "https://w0.peakpx.com/wallpaper/243/779/HD-wallpaper-taylor-swift-1989-taylor-swift-ts.jpg"))
        browseList.add(BrowseCards("Music", red, "https://w0.peakpx.com/wallpaper/243/779/HD-wallpaper-taylor-swift-1989-taylor-swift-ts.jpg"))
        browseList.add(BrowseCards("Music", red, "https://w0.peakpx.com/wallpaper/243/779/HD-wallpaper-taylor-swift-1989-taylor-swift-ts.jpg"))
        browseList.add(BrowseCards("Music", red, "https://w0.peakpx.com/wallpaper/243/779/HD-wallpaper-taylor-swift-1989-taylor-swift-ts.jpg"))
        browseList.add(BrowseCards("Music", red, "https://w0.peakpx.com/wallpaper/243/779/HD-wallpaper-taylor-swift-1989-taylor-swift-ts.jpg"))
        browseList.add(BrowseCards("Music", red, "https://w0.peakpx.com/wallpaper/243/779/HD-wallpaper-taylor-swift-1989-taylor-swift-ts.jpg"))
        browseList.add(BrowseCards("Music", red, "https://w0.peakpx.com/wallpaper/243/779/HD-wallpaper-taylor-swift-1989-taylor-swift-ts.jpg"))

        searchAdapter = BrowseCardAdapter(requireContext(), browseList, picasso)
        searchBinding.searchRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        searchBinding.searchRecyclerView.adapter = searchAdapter
    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = requireActivity().supportFragmentManager

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_content, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}