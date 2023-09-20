package uz.ikhtidev.mvvmwithhilt.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.ikhtidev.mvvmwithhilt.database.dao.GithubUserDao
import uz.ikhtidev.mvvmwithhilt.database.entity.GithubUserEntity

@Database(entities = [GithubUserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getGithubUserDao(): GithubUserDao

}