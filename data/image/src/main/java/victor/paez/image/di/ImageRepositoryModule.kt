package victor.paez.image.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import victor.paez.image.datasource.ImageDataSource
import victor.paez.image.repository.ImageRepository
import victor.paez.image.repository.ImageRepositoryImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ImageRepositoryModule {
    @Provides
    @Singleton
    fun provideImageRepository(
        imageDataSource: ImageDataSource,
        dispatcher: CoroutineDispatcher,
    ): ImageRepository =
        ImageRepositoryImp(imageDataSource, dispatcher)
}
