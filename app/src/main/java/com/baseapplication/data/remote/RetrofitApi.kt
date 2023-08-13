package com.baseapplication.data.remote

import com.baseapplication.data.remote.dto.CharacterDto
import retrofit2.http.GET

interface RetrofitApi {

    @GET("Characters")
    suspend fun getCharacters(): List<CharacterDto>
}