package victor.paez.di

import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirestoreStorageReferenceModule {
    @Provides
    @Singleton
    fun provideFirestoreStorageReference(): StorageReference =
        Firebase.storage("gs://viclends-a84ca.appspot.com").reference.child("client_images")
}
