package com.gajanan.rocketapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gajanan.rocketapp.adapters.RocketAdapter
import com.gajanan.rocketapp.databinding.ActivityMainBinding
import com.gajanan.rocketapp.utils.ResultApi
import com.gajanan.rocketapp.viewModel.RocketViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val rocketViewModel by viewModels<RocketViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observers()
    }

    private fun observers(){
        rocketViewModel.getAllRockets.observe(this@MainActivity) { result ->
            binding.apply {

                flProgress.isVisible = result is ResultApi.Loading && result.data.isNullOrEmpty()
                val rocketAdapter = RocketAdapter(this@MainActivity) {
                    goRocketDetail(it)
                }
                rocketAdapter.submitList(result.data)
                binding.rvRocket.adapter = rocketAdapter
                rvRocket.addItemDecoration(DividerItemDecoration(this@MainActivity,LinearLayoutManager.VERTICAL))
            }
        }

    }
    private fun goRocketDetail(id: String) {
        startActivity(Intent(this, RocketDetailActivity::class.java).also {
            it.putExtra("RocketId", id)
        })
    }
}