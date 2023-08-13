package com.baseapplication.domine.use_case.get_characters

import com.baseapplication.common.Constant
import com.baseapplication.common.Result
import com.baseapplication.data.remote.dto.toCharacter
import com.baseapplication.domine.model.Character
import com.baseapplication.domine.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(): Flow<Result<List<Character>>> = flow {
        try {
            emit(Result.Loading())
            val characters = repository.getCharacters().map { it.toCharacter() }
            emit(Result.Success(characters))
        }catch (ex:Exception){
            emit(Result.Error(ex.localizedMessage ?: Constant.commonErrorMessage))
        }
    }
}