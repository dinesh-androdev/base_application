package com.baseapplication.domine.repository

import com.baseapplication.data.remote.dto.CharacterDto
interface CharacterRepository {
    suspend fun getCharacters(): List<CharacterDto>
}