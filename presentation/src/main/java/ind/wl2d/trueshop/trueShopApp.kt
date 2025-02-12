package ind.wl2d.trueshop

import android.app.Application
import ind.wl2d.data.di.dataModule
import ind.wl2d.domain.di.domainModule
import ind.wl2d.trueshop.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TrueShopApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TrueShopApp)
            modules(
                listOf(
                    presentationModule,
                    domainModule,
                    dataModule,
                ),
            )
        }
    }
}
