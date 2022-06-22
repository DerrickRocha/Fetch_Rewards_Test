package com.example.fetchrewardstest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchrewardstest.databinding.ActivityMainBinding
import com.example.fetchrewardstest.ui.adapters.HomeScreenAdapter
import com.example.fetchrewardstest.ui.viewmodels.HomeViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: HomeViewModel by viewModels()
    private val homeScreenAdapter = HomeScreenAdapter()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        setupObservers()
        viewModel.getItems()
    }

    private fun setupViews() {
        binding.recycler.apply {
            adapter = homeScreenAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        binding.retry.setOnClickListener {
            binding.errorScreen.visibility = View.GONE
            viewModel.getItems()
        }
    }

    private fun setupObservers() {
        viewModel.showLoading.observe(this) { show ->
            binding.loadingIcon.visibility = if (show) View.VISIBLE else View.GONE
        }
        viewModel.items.observe(this) { items ->
            homeScreenAdapter.hasItems(items)
        }
        viewModel.error.observe(this) { error ->
            binding.errorScreen.visibility = View.VISIBLE
        }
    }
}