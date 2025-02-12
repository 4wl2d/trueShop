package ind.wl2d.data.di

import ind.wl2d.data.repository.ProductRepositoryImpl
import ind.wl2d.domain.repository.ProductRepository
import org.koin.dsl.module

val repositoryModule =
    module {
        single<ProductRepository> { ProductRepositoryImpl(get()) }
    }
