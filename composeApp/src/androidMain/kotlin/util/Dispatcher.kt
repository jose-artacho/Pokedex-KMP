package util

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AndroidDispatcher: Dispatcher {
    override val ioDispatcher: CoroutineDispatcher
        get() = Dispatchers.IO

}

actual fun provideDispatcher(): Dispatcher = AndroidDispatcher()

actual fun getApiEngine(): HttpClientEngine = OkHttp.create()