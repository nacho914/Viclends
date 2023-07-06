package victor.paez.usecases.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import victor.paez.account.repository.AccountRepository
import victor.paez.usecases.AddAccountUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AddAccountUseCaseModule {
    @Provides
    @Singleton
    fun provideAddAccountUseCase(
        accountRepository: AccountRepository,
    ):
        AddAccountUseCase =
        AddAccountUseCase(accountRepository)
}
