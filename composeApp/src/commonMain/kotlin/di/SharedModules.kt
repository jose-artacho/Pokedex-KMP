package di

import data.datasource.PokemonDataSource
import data.datasource.cloud.PokemonDataSourceImpl
import data.network.KtorApi
import data.repository.PokemonRepositoryImpl
import domain.repository.PokemonRepository
import domain.use_cases.GetPokemonByIdUseCase
import domain.use_cases.GetPokemonsUseCase
import domain.use_cases.implementations.GetPokemonByIdUseCaseImpl
import domain.use_cases.implementations.GetPokemonsUseCaseImpl
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
    factory { PokemonDataSourceImpl(get()) }
    single<PokemonRepository> { PokemonRepositoryImpl() }

}

private val utilsModule = module {
    factory { provideDispatcher() }
}

private val domainModule = module {
    factory { KtorApi() }
    single { PokemonRepositoryImpl() }
    single<PokemonDataSource> { PokemonDataSourceImpl(get()) }
    single { GetPokemonsUseCaseImpl(get()) }
    single { GetPokemonByIdUseCaseImpl(get()) }
}

private val sharedModule = listOf(domainModule, dataModule, utilsModule)