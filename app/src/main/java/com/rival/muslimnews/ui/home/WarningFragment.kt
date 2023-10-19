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
import com.rival.muslimnews.databinding.FragmentWarningBinding
import com.rival.muslimnews.ui.NewsViewModel
import com.rival.muslimnews.utils.NewsViewModelFactory


class WarningFragment : Fragment() {

    private lateinit var binding: FragmentWarningBinding
    private val warningViewModel : NewsViewModel by viewModels {
        NewsViewModelFactory(NewsRepository())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentWarningBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mAdapter = NewsAdapter()

        if (warningViewModel.warningForMuslimNews.value == null){
            warningViewModel.getWarningNews()
        }

        warningViewModel.warningForMuslimNews.observe(viewLifecycleOwner){
            mAdapter.setData(it.articles)

            binding.rvWarningNews.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

        warningViewModel.isLoading.observe(viewLifecycleOwner){
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