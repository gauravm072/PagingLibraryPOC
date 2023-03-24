package com.example.paginglibrarypoc.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paginglibrarypoc.R
import com.example.paginglibrarypoc.adapter.RepoListAdapter
import com.example.paginglibrarypoc.adapter.RepoLoadStateAdapter
import com.example.paginglibrarypoc.viewmodel.DummyViewModel
import com.example.paginglibrarypoc.viewmodel.RepositoryViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


private const val REPO_NAME = "google"
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: RepositoryViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var repoListAdapter: RepoListAdapter
    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(modelClass = RepositoryViewModel::class.java)
        repoListAdapter = RepoListAdapter()
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = repoListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        search(REPO_NAME)
    }


    private fun search(username: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchRepos(username)?.collect{
                Log.d("PAGINGGGG", "search: $it")
                repoListAdapter.submitData(it)
            }
//            DummyViewModel.searchRepos(username)
        }
    }

}