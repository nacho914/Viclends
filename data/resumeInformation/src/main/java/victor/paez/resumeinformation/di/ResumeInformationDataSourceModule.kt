package victor.paez.resumeinformation.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import victor.paez.resumeinformation.datasource.ResumeInformationDataSource
import victor.paez.resumeinformation.datasource.ResumeInformationDataSourceImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ResumeInformationDataSourceModule {
    @Provides
    @Singleton
    fun provideResumeInformationDataSource(firestore: FirebaseFirestore): ResumeInformationDataSource =
        ResumeInformationDataSourceImp(firestore)
}
