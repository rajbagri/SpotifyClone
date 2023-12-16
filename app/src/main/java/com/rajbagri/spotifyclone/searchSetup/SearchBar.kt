package com.rajbagri.spotifyclone.searchSetup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.rajbagri.spotifyclone.apiData.ApiInterface
import com.rajbagri.spotifyclone.apiData.Data
import com.rajbagri.spotifyclone.apiData.DataAdapter
import com.rajbagri.spotifyclone.apiData.MyData
import com.rajbagri.spotifyclone.databinding.FragmentSearchBarBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SearchBar : Fragment() {
    private lateinit var searchBarBinding: FragmentSearchBarBinding

    private lateinit var adapter: DataAdapter

    private lateinit var userInput: String

    companion object{
        var musicList: List<Data>? = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchBarBinding = FragmentSearchBarBinding.inflate(inflater, container, false)
        return searchBarBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)




        searchBarBinding.editTextSearchSong.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                // Not needed, but required to implement
                userInput = ""
                userInput = charSequence.toString()
                if(!userInput.isEmpty()){
                    val retrofit = retrofitBuilder.getData(userInput)

                    retrofit.enqueue(object : Callback<MyData?> {
                        override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                            val dataList = response.body()?.data!!
                            musicList = dataList

                            adapter = DataAdapter(requireContext(), dataList)
                            searchBarBinding.recyclerViewSearchSong.adapter = adapter
                            searchBarBinding.recyclerViewSearchSong.layoutManager = LinearLayoutManager(requireContext())

                            Log.d("Tag: onResponse", "onResponse: " + response.body())
                        }

                        override fun onFailure(call: Call<MyData?>, t: Throwable) {

                        }
                    })
                }
            }



            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                // Filter your data based on the entered text
                userInput = ""
                userInput = charSequence.toString()
                if(!userInput.isEmpty()){
                    val retrofit = retrofitBuilder.getData(userInput)

                    retrofit.enqueue(object : Callback<MyData?> {
                        override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                            val dataList = response.body()?.data!!
                            musicList = dataList
                            adapter = DataAdapter(requireContext(), dataList)
                            searchBarBinding.recyclerViewSearchSong.adapter = adapter
                            searchBarBinding.recyclerViewSearchSong.layoutManager = LinearLayoutManager(requireContext())
                            Log.d("Tag: onResponse", "onResponse: " + response.body())
                        }

                        override fun onFailure(call: Call<MyData?>, t: Throwable) {

                        }
                    })
                }
                else{

                }

            }

            override fun afterTextChanged(editable: Editable) {
                // Not needed, but required to implement
            }
        })




        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){


            requireActivity().supportFragmentManager.popBackStack()
        }
    }




}


