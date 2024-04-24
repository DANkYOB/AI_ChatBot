package com.example.chatbot

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import com.example.chatbot.databinding.ActivityMainBinding
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val search = findViewById<EditText>(R.id.search)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val tvResult = findViewById<TextView>(R.id.tvResult)


        btnSearch.setOnClickListener{
            val prompt = search.text.toString()

            val generativeModel = GenerativeModel(
                modelName = "gemini-pro",
                apiKey = "AIzaSyC8tMaxYerpa8sNyGP_CVesOqw4DjTj4CI"
            )

            runBlocking {
                val response = generativeModel.generateContent(prompt)
                tvResult.text= response.text
            }
            closeKeyboard(tvResult)
        }

    }

    private fun closeKeyboard(view: View) {

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken,0)

    }
}

