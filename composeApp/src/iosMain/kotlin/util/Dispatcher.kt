package util

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

class IOSDispatcher: Dispatcher {
    override val ioDispatcher: CoroutineDispatcher
        get() = Dispatchers.IO
}

actual fun provideDispatcher(): Dispatcher = IOSDispatcher()

actual fun getApiEngine(): HttpClientEngine = Darwin.create()