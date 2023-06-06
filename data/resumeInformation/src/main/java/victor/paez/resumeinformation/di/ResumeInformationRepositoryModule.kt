package victor.paez.resumeinformation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import victor.paez.resumeinformation.datasource.ResumeInformationDataSource
import victor.paez.resumeinformation.repository.ResumeInformationRepository
import victor.paez.resumeinformation.repository.ResumeInformationRepositoryImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ResumeInformationRepositoryModule {
    @Provides
    @Singleton
    fun provideResumeInformationRepository(resumeInformationDataSource: ResumeInformationDataSource):
        ResumeInformationRepository =
        ResumeInformationRepositoryImp(resumeInformationDataSource)
}
