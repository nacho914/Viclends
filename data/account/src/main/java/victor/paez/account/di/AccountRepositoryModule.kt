package victor.paez.account.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import victor.paez.account.datasource.AccountDataSource
import victor.paez.account.repository.AccountRepository
import victor.paez.account.repository.AccountRepositoryImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AccountRepositoryModule {
    @Provides
    @Singleton
    fun provideAccountRepository(
        accountDatasource: AccountDataSource,
        dispatcher: CoroutineDispatcher,
    ): AccountRepository =
        AccountRepositoryImp(accountDatasource, dispatcher)
}
