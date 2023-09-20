package uz.ikhtidev.mvvmwithhilt.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.ikhtidev.mvvmwithhilt.database.entity.GithubUserEntity

@Dao
interface GithubUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUsers(users: List<GithubUserEntity>)

    @Query("select * from github_user_entity")
    fun getGithubUsers(): List<GithubUserEntity>

    @Query("select count(*) from github_user_entity")
    fun getGithubUserCount(): Int
}