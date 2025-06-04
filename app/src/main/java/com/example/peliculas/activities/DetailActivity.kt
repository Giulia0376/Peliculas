package com.example.peliculas.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.peliculas.R
import com.example.peliculas.utils.RetrofitClient
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private val apiKey = "fb7aca4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imdbID = intent.getStringExtra("imdbID")
        if (imdbID != null) {
            loadMovieDetail(imdbID)
        } else {
            Toast.makeText(this, "No se encontró el ID de la película", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun loadMovieDetail(imdbID: String) {
        lifecycleScope.launch {
            try {
                val detail = RetrofitClient.api.getMovieDetail(apiKey, imdbID)
                if (detail.Response == "True") {
                    findViewById<TextView>(R.id.titleDetailTextView).text = detail.Title
                    findViewById<TextView>(R.id.yearDetailTextView).text = "Año: ${detail.Year}"
                    findViewById<TextView>(R.id.runtimeTextView).text = "Duración: ${detail.Runtime}"
                    findViewById<TextView>(R.id.directorTextView).text = "Director: ${detail.Director}"
                    findViewById<TextView>(R.id.genreTextView).text = "Género: ${detail.Genre}"
                    findViewById<TextView>(R.id.countryTextView).text = "País: ${detail.Country}"
                    findViewById<TextView>(R.id.plotTextView).text = "Sinopsis: ${detail.Plot}"

                    Picasso.get()
                        .load(detail.Poster)
                        .into(findViewById<ImageView>(R.id.posterDetailImageView))
                } else {
                    Toast.makeText(this@DetailActivity, "Detalles no encontrados", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@DetailActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}