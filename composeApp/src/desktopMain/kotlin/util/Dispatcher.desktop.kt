package util

import io.ktor.client.engine.HttpClientEngine
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DesktopDispatcher: Dispatcher {
    override val ioDispatcher: CoroutineDispatcher
        get() = Dispatchers.IO

}

actual fun provideDispatcher(): Dispatcher = DesktopDispatcher()