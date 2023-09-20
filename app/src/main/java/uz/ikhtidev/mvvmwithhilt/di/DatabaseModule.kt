package uz.ikhtidev.mvvmwithhilt.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.ikhtidev.mvvmwithhilt.database.AppDatabase
import uz.ikhtidev.mvvmwithhilt.database.dao.GithubUserDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "my_db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideGithubUserDao(appDatabase: AppDatabase): GithubUserDao {
        return appDatabase.getGithubUserDao()
    }
}