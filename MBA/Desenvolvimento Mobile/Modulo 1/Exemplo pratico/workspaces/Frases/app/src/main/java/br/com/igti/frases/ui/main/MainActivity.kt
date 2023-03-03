package br.com.igti.frases.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.igti.frases.R
import br.com.igti.frases.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(R.layout.activity_main)
    }
}