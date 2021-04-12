package com.example.plantslog.ui.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plantslog.R
import com.example.plantslog.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint


@SuppressLint("ResourceType")
@AndroidEntryPoint
class ListFragment: Fragment(R.layout.fragment_list) {
    private val viewmodel by viewModels<ListViewModel>()

    private var _binding: FragmentListBinding? = null
    private val binding
        get() = _binding!!



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mLayoutManager = LinearLayoutManager(context)

        _binding = FragmentListBinding.bind(view)

        val adapter = TrefleDataAdapter()

        binding.apply {
            recyclerview.setHasFixedSize(true)
            recyclerview.adapter = adapter.withLoadStateHeaderAndFooter(
                    header = TrefleLoadStateAdapter { adapter.retry() },
                    footer = TrefleLoadStateAdapter { adapter.retry() }
            )
            recyclerview.layoutManager = mLayoutManager
        }


        viewmodel.data.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_list, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                if(query != null) {
                    binding.recyclerview.scrollToPosition(0)
                    viewmodel.searchPlants(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText == null || newText.isEmpty()){
                    viewmodel.searchPlants("")

                }
                return true
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}