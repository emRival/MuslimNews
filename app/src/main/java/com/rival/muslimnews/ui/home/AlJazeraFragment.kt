package com.rival.muslimnews.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rival.muslimnews.R
import com.rival.muslimnews.adapter.NewsAdapter
import com.rival.muslimnews.data.repository.NewsRepository
import com.rival.muslimnews.databinding.FragmentAlJazeraBinding
import com.rival.muslimnews.ui.NewsViewModel
import com.rival.muslimnews.utils.NewsViewModelFactory


class AlJazeraFragment : Fragment() {

    private lateinit var binding: FragmentAlJazeraBinding
    private val alJazeraViewModel : NewsViewModel by viewModels {
        NewsViewModelFactory(NewsRepository())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentAlJazeraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mAdapter = NewsAdapter()
        if (alJazeraViewModel.alJazeeraNews.value == null){
            alJazeraViewModel.getAlJazeraNews()
        }

        alJazeraViewModel.alJazeeraNews.observe(viewLifecycleOwner){
            mAdapter.setData(it.articles)
            binding.rvAljazeraNews.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

        alJazeraViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }


    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.loadingView.root.visibility = View.VISIBLE
        } else {
            binding.loadingView.root.visibility = View.GONE
        }
    }


}