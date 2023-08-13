package com.baseapplication.di

import com.baseapplication.common.Constant
import com.baseapplication.data.remote.RetrofitApi
import com.baseapplication.data.repository.CharacterRepositoryImpl
import com.baseapplication.domine.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitApi():RetrofitApi{
        return Retrofit.Builder()
            .baseUrl(Constant.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCharactersRepository(api: RetrofitApi):CharacterRepository{
        return CharacterRepositoryImpl(api)
    }

}