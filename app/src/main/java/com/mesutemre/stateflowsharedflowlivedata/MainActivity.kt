package com.mesutemre.stateflowsharedflowlivedata

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.mesutemre.stateflowsharedflowlivedata.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscribeToLiveData()
        subscribeToStateFlow()
        subscribeToSharedFlow()

        binding.liveDataButton.setOnClickListener {
            viewModel.runLiveData()
        }

        binding.stateFlowButton.setOnClickListener {
            viewModel.runStateFlow()
        }

        binding.sharedFlowButton.setOnClickListener {
            viewModel.runSharedFlow()
        }
    }

    private fun subscribeToLiveData() {
        viewModel.liveData.observe(this) {
            binding.liveDataText.text = it
        }
    }

    private fun subscribeToStateFlow() {
        lifecycleScope.launchWhenStarted {
            viewModel.stateFlow.collectLatest {
                binding.stateFlowText.text = it
                /* Snackbar.make(
                    binding.root,
                    it,
                    Snackbar.LENGTH_LONG
                ).show() */
            }
        }
    }

    private fun subscribeToSharedFlow() {
        lifecycleScope.launchWhenStarted {
            viewModel.sharedFlow.collectLatest {
                binding.sharedFlowText.text = it
                Snackbar.make(
                    binding.root,
                    it,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
}