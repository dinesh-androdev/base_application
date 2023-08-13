package com.baseapplication.data.repository

import com.baseapplication.data.remote.RetrofitApi
import com.baseapplication.data.remote.dto.CharacterDto
import com.baseapplication.domine.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: RetrofitApi
): CharacterRepository {

    override suspend fun getCharacters(): List<CharacterDto> {
        return api.getCharacters()
    }
}