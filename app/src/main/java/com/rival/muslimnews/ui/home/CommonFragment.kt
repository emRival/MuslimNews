package com.rival.muslimnews.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rival.muslimnews.adapter.NewsAdapter
import com.rival.muslimnews.data.repository.NewsRepository
import com.rival.muslimnews.databinding.FragmentCommonBinding
import com.rival.muslimnews.ui.NewsViewModel
import com.rival.muslimnews.utils.NewsViewModelFactory


class CommonFragment : Fragment() {
    private lateinit var binding: FragmentCommonBinding
    private val commonNewsViewModel: NewsViewModel by viewModels {
        NewsViewModelFactory(NewsRepository())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        commonNewsViewModel.getCommonMuslimNews()
        val mAdapter = NewsAdapter()
        if (commonNewsViewModel.commonMuslimNews.value == null) {
            // Data belum ada, panggil metode untuk mendapatkannya
            commonNewsViewModel.getCommonMuslimNews()
        }

        commonNewsViewModel.commonMuslimNews.observe(viewLifecycleOwner) {
            mAdapter.setData(it.articles)
            binding.rvCommonNews.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

        commonNewsViewModel.isLoading.observe(viewLifecycleOwner) {
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