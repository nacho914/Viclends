package victor.paez.client.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import victor.paez.client.datasource.ClientDatasource
import victor.paez.client.repository.ClientRepository
import victor.paez.client.repository.ClientRepositoryImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ClientRepositoryModule {
    @Provides
    @Singleton
    fun provideClientRepository(
        clientDatasource: ClientDatasource,
        dispatcher: CoroutineDispatcher,
    ):
        ClientRepository =
        ClientRepositoryImp(clientDatasource, dispatcher)
}
