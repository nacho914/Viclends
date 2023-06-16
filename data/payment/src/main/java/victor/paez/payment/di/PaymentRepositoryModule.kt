package victor.paez.payment.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import victor.paez.payment.datasource.PaymentDataSource
import victor.paez.payment.repository.PaymentRepository
import victor.paez.payment.repository.PaymentRepositoryImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PaymentRepositoryModule {
    @Provides
    @Singleton
    fun providePaymentRepository(
        paymentDataSource: PaymentDataSource,
        dispatcher: CoroutineDispatcher,
    ): PaymentRepository =
        PaymentRepositoryImp(paymentDataSource, dispatcher)
}
