package victor.paez.delay.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import victor.paez.delay.datasource.DelayDataSource
import victor.paez.delay.repository.DelayRepository
import victor.paez.delay.repository.DelayRepositoryImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DelayRepositoryModule {
    @Provides
    @Singleton
    fun providePaymentRepository(
        paymentDataSource: DelayDataSource,
        dispatcher: CoroutineDispatcher,
    ): DelayRepository =
        DelayRepositoryImp(paymentDataSource, dispatcher)
}
