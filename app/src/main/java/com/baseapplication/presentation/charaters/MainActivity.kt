package com.baseapplication.presentation.charaters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.baseapplication.R
import com.baseapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val charactersViewModel by viewModels<CharactersViewModel>()
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        initCollector()
    }

    private fun initCollector() {
        lifecycleScope.launch {
            charactersViewModel.characters.collectLatest {
                val value = if (it.isLoading){
                    "Loading"
                }else if (it.error.isNotEmpty()){
                    it.error
                }else{
                    it.characters.size.toString()
                }
                updateUi(value)
            }
        }
    }

    private fun updateUi(value: String) {
        runOnUiThread {
            activityMainBinding.charactersTv.text = value
        }
    }
}