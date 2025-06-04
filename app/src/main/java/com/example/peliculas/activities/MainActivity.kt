package com.example.peliculas.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.peliculas.R
import com.example.peliculas.utils.RetrofitClient
import com.example.peliculas.adapters.MovieAdapter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MovieAdapter
    private val apiKey = "fb7aca4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val searchEditText = findViewById<EditText>(R.id.searchEditText)
        val searchButton = findViewById<Button>(R.id.searchButton)

        recyclerView.layoutManager = LinearLayoutManager(this)

        searchButton.setOnClickListener {
            val query = searchEditText.text.toString().trim()
            if (query.isNotEmpty()) {
                searchMovies(query)
            }
        }
    }

    private fun searchMovies(query: String) {
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.api.searchMovies(apiKey, query)
                if (response.Response == "True" && response.Search != null) {
                    adapter = MovieAdapter(response.Search) { movie ->
                        val intent = Intent(this@MainActivity, DetailActivity::class.java)
                        intent.putExtra("imdbID", movie.imdbID)
                        startActivity(intent)
                    }
                    findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter
                } else {
                    Toast.makeText(this@MainActivity, "No se encontraron películas", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}