package di

import data.datasource.cloud.PokemonDataSource
import data.network.PokemonService
import data.repository.PokemonRepositoryImpl
import domain.repository.PokemonRepository
import domain.use_cases.GetPokemonByIdUseCase
import domain.use_cases.GetPokemonsUseCase
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import util.provideDispatcher


fun initKoin(appDeclaration: KoinAppDeclaration) = startKoin {
    appDeclaration()
    modules(
        dataModule,
        utilsModule,
        domainModule
    )
}

fun initKoin() = initKoin {}

private val dataModule = module {
    factory { PokemonDataSource(get(), get()) }
    factory { PokemonService() }

}

private val utilsModule = module {
    factory { provideDispatcher() }
}

private val domainModule = module {
single<PokemonRepository> { PokemonRepositoryImpl() }
    factory { PokemonDataSource(get(), get()) }
    factory { GetPokemonsUseCase() }
    factory { GetPokemonByIdUseCase() }
}

private val sharedModule = listOf(domainModule, dataModule, utilsModule)

fun getSharedModules() = sharedModule