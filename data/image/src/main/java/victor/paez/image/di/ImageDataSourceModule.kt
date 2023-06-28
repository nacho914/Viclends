package victor.paez.image.di

import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import victor.paez.image.datasource.ImageDataSource
import victor.paez.image.datasource.ImageDataSourceImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ImageDataSourceModule {
    @Provides
    @Singleton
    fun provideImageDataSource(storageReference: StorageReference): ImageDataSource =
        ImageDataSourceImp(storageReference)
}
