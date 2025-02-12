package ind.wl2d.domain.di

import ind.wl2d.domain.usecase.GetProductUseCase
import org.koin.dsl.module

val useCaseModule =
    module {
        factory { GetProductUseCase(get()) }
    }
