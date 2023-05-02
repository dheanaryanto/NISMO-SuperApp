package edu.uksw.fti.pam.pam_activityintent.repositories

import edu.uksw.fti.pam.pam_activityintent.models.Berita
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface JSONPlaceholderTypicodeRepository {
    @GET("berita")
    suspend fun getBerita(): List<Berita>

    companion object {
        var _apiClient: JSONPlaceholderTypicodeRepository? = null

        fun getClient(): JSONPlaceholderTypicodeRepository {
            if (_apiClient == null) {
                _apiClient = Retrofit.Builder()
                    .baseUrl("https://santikaw.github.io/nismocart/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(JSONPlaceholderTypicodeRepository::class.java)
            }

            return _apiClient!!
        }
    }
}