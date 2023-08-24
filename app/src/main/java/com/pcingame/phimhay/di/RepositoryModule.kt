package com.pcingame.phimhay.di

import com.pcingame.phimhay.data.repository.MovieRepositoryImpl
import com.pcingame.phimhay.domain.repository.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRepository> {
        MovieRepositoryImpl(movieApi = get(), database = get())
    }
}
