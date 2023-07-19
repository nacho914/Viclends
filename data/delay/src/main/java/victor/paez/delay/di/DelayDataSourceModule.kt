package victor.paez.delay.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import victor.paez.delay.datasource.DelayDataSource
import victor.paez.delay.datasource.DelayDataSourceImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DelayDataSourceModule {
    @Provides
    @Singleton
    fun provideDelayDataSource(firestore: FirebaseFirestore): DelayDataSource =
        DelayDataSourceImp(firestore)
}
