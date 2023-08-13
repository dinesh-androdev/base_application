package com.baseapplication.presentation.charaters

import com.baseapplication.domine.model.Character

data class CharactersState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val error: String = ""
)
