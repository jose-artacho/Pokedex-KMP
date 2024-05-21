package di

import data.datasource.cloud.PokemonDataSource
import data.network.PokemonService
import data.repository.PokemonRepositoryImpl
import domain.repository.PokemonRepository
import domain.use_cases.GetPokemonByIdUseCase
import domain.use_cases.GetPokemonsUseCase
import org.koin.dsl.module
import util.provideDispatcher

private val dataModule = module {
    factory { PokemonDataSource(get(), get()) }
    factory { PokemonService() }

}

private val utilsModule = module {
    factory { provideDispatcher() }
}

private val domainModule = module {
single<PokemonRepository> { PokemonRepositoryImpl(get()) }
    factory { GetPokemonsUseCase() }
    factory { GetPokemonByIdUseCase() }
}

private val sharedModule = listOf(domainModule, dataModule, utilsModule)

fun getSharedModules() = sharedModule