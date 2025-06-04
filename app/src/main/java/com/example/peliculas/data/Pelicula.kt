package com.example.peliculas.data


data class MovieItem(
    val Title: String,
    val Year: String,
    val imdbID: String,
    val Poster: String
)

// Respuesta de búsqueda
data class SearchResponse(
    val Search: List<MovieItem>?,
    val totalResults: String?,
    val Response: String,
    val Error: String?
)

// Detalle de película
data class MovieDetail(
    val Title: String,
    val Year: String,
    val Poster: String,
    val Plot: String,
    val Runtime: String,
    val Director: String,
    val Genre: String,
    val Country: String,
    val Response: String
)
