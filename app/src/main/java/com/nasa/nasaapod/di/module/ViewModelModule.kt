package ApodService.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nasa.nasaapod.di.ViewModelKey
import com.nasa.nasaapod.ui.NasaApodViewModel
import com.nasa.nasaapod.ui.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NasaApodViewModel::class)
    abstract fun nasaApodListViewModel(viewModelNasa: NasaApodViewModel): ViewModel
}