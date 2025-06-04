package com.example.peliculas.utils
import com.example.peliculas.data.MovieDetail
import com.example.peliculas.data.SearchResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {
    @GET(".")
    suspend fun searchMovies(
        @Query("apikey") apiKey: String,
        @Query("s") title: String
    ): SearchResponse

    @GET(".")
    suspend fun getMovieDetail(
        @Query("apikey") apiKey: String,
        @Query("i") imdbID: String,
        @Query("plot") plot: String = "full"
    ): MovieDetail
}


object RetrofitClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.omdbapi.com/?apikey=fb7aca4")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: OmdbApi = retrofit.create(OmdbApi::class.java)
}

