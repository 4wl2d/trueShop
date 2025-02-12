package ind.wl2d.trueshop.di

import ind.wl2d.trueshop.ui.feature.home.screen.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule =
    module {
        viewModel {
            HomeViewModel(get())
        }
    }
