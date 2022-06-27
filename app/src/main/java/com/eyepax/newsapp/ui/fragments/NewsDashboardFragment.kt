package com.eyepax.newsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.eyepax.newsapp.R
import com.eyepax.newsapp.adapters.BreakingNewsAdapter
import com.eyepax.newsapp.adapters.NewsAdapter
import com.eyepax.newsapp.databinding.FragmentNewsDashboardBinding
import com.eyepax.newsapp.ui.HomeActivity
import com.eyepax.newsapp.ui.NewsViewModel
import com.eyepax.newsapp.util.Constants
import com.eyepax.newsapp.util.Constants.Companion.QUERY_PAGE_SIZE
import com.eyepax.newsapp.util.Constants.Companion.SEARCH_NEWS_TIME_DELAY
import com.eyepax.newsapp.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class NewsDashboardFragment : Fragment(R.layout.fragment_news_dashboard) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    lateinit var breakingNewsAdapter: BreakingNewsAdapter

    private lateinit var binding: FragmentNewsDashboardBinding

    val TAG = "NewsDashboardFragment"


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = FragmentNewsDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as HomeActivity).viewModel

        setupRecyclerView()
        setupBreakingNewsRecyclerView()



        binding.btnClear.setOnClickListener{
            binding.etSearchNews.text.clear()
            binding.ivSearch.visibility = View.VISIBLE
            binding.btnClear.visibility = View.GONE
            binding.loLatestNewsSection.visibility = View.VISIBLE

        }

        var job: Job? = null
        binding.etSearchNews.addTextChangedListener { editable ->
            binding.ivSearch.visibility = View.GONE
            binding.btnClear.visibility = View.VISIBLE
            binding.loLatestNewsSection.visibility = View.GONE
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_TIME_DELAY)
                editable?.let {
                    if(editable.toString().isNotEmpty()) {
                        viewModel.searchNews(editable.toString())
                    }
                }
            }
        }

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
//                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        breakingNewsAdapter.differ.submitList(newsResponse.articles.toList())
//                        val totalPages = newsResponse.totalResults / QUERY_PAGE_SIZE + 2
//                        isLastPage = viewModel.breakingNewsPage == totalPages
                    }
                }
                is Resource.Error -> {
//                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
//                    showProgressBar()
                }
            }
        })

        viewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles.toList())
                        val totalPages = newsResponse.totalResults / Constants.QUERY_PAGE_SIZE + 2
                        isLastPage = viewModel.searchNewsPage == totalPages
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->

                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.GONE
        isLoading = false
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning &&
                    isTotalMoreThanVisible && isScrolling
            if(shouldPaginate) {

                viewModel.searchNews(binding.etSearchNews.text.toString())
                isScrolling = false
            } else {

                binding.rvGeneralNewsList.setPadding(0, 0, 0, 0)
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()

        binding.rvGeneralNewsList.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@NewsDashboardFragment.scrollListener)
        }
    }


    private fun setupBreakingNewsRecyclerView() {
        breakingNewsAdapter = BreakingNewsAdapter()

        binding.rvLatestNewsList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL ,false)
        binding.rvLatestNewsList.apply {
            adapter = breakingNewsAdapter
        }
    }

}