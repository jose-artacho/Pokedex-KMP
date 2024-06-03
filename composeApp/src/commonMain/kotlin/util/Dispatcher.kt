package util

import io.ktor.client.engine.HttpClientEngine
import kotlinx.coroutines.CoroutineDispatcher

interface Dispatcher {
    val ioDispatcher: CoroutineDispatcher
}

expect fun provideDispatcher(): Dispatcher
expect fun getApiEngine(): HttpClientEngine