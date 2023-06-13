package victor.paez.client.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import victor.paez.client.datasource.ClientDataSourceImp
import victor.paez.client.datasource.ClientDatasource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ClientDataSourceModule {
    @Provides
    @Singleton
    fun provideClientDataSource(firestore: FirebaseFirestore): ClientDatasource =
        ClientDataSourceImp(firestore)
}
