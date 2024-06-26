package hr.markopetricevic.autodiygarage.Repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hr.markopetricevic.autodiygarage.RepairRepository.RepairRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SingeltonRepository {

    @Provides
    @Singleton
    fun provideRepairRepository() = RepairRepository()

}