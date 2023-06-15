package victor.paez.account.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import victor.paez.account.datasource.AccountDataSource
import victor.paez.account.datasource.AccountDataSourceImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AccountDataSourceModule {
    @Provides
    @Singleton
    fun provideAccountDataSource(firestore: FirebaseFirestore): AccountDataSource =
        AccountDataSourceImp(firestore)
}
