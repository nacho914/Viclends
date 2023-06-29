package victor.paez.usecases.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import victor.paez.client.repository.ClientRepository
import victor.paez.image.repository.ImageRepository
import victor.paez.resumeinformation.repository.ResumeInformationRepository
import victor.paez.usecases.AddClientUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AddClientUseCaseModule {
    @Provides
    @Singleton
    fun provideAddClientUseCase(
        clientRepository: ClientRepository,
        imageRepository: ImageRepository,
        resumeInformationRepository: ResumeInformationRepository,
    ):
        AddClientUseCase =
        AddClientUseCase(clientRepository, imageRepository, resumeInformationRepository)
}
