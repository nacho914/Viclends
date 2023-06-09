package victor.paez.usecases.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import victor.paez.client.repository.ClientRepository
import victor.paez.usecases.GetClientListUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GetClientListUseCaseModule {
    @Provides
    @Singleton
    fun provideGetClientListUseCase(clientRepository: ClientRepository):
        GetClientListUseCase =
        GetClientListUseCase(clientRepository)
}
