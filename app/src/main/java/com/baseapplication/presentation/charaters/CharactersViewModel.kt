package com.baseapplication.presentation.charaters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baseapplication.common.Constant
import com.baseapplication.common.Result
import com.baseapplication.domine.use_case.get_characters.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
):ViewModel(){

    init {
        getCharacters()
    }

    private val _characters = MutableStateFlow(CharactersState())
    val characters: StateFlow<CharactersState> = _characters

    private fun getCharacters(){
        getCharactersUseCase().onEach {
            when(it){
                is Result.Success ->{
                    _characters.value = CharactersState(
                        characters = it.data ?: emptyList()
                    )
                }
                is Result.Error ->{
                    _characters.value = CharactersState(
                        error = it.message ?: Constant.commonErrorMessage
                    )
                }
                is Result.Loading ->{
                    _characters.value = CharactersState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}