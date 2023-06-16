package victor.paez.payment.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import victor.paez.payment.datasource.PaymentDataSource
import victor.paez.payment.datasource.PaymentDataSourceImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PaymentDataSourceModule {
    @Provides
    @Singleton
    fun providePaymentDataSource(firestore: FirebaseFirestore): PaymentDataSource =
        PaymentDataSourceImp(firestore)
}
