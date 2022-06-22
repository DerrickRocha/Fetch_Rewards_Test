package com.example.fetchrewardstest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.fetchrewardstest.databinding.ActivityMainBinding
import com.example.fetchrewardstest.ui.viewmodels.HomeViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.items.observe(this) { items ->
            Log.d("MainActivity", items.toString())
        }
        viewModel.error.observe(this) { error ->
            Log.d("MainActivity", error.message?: "Unknown error")
        }
        viewModel.getItems()
    }
}