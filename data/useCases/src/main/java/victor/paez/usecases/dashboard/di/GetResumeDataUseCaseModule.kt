package victor.paez.usecases.dashboard.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import victor.paez.resumeinformation.repository.ResumeInformationRepository
import victor.paez.usecases.dashboard.GetResumeDataUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GetResumeDataUseCaseModule {
    @Provides
    @Singleton
    fun provideResumeGetResumeDataUseCase(resumeInformationRepository: ResumeInformationRepository):
        GetResumeDataUseCase =
        GetResumeDataUseCase(resumeInformationRepository)
}
