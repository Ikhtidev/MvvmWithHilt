package uz.ikhtidev.mvvmwithhilt.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "github_user_entity")
data class GithubUserEntity(
    @PrimaryKey
    val id: Int,
    val avatar: String,
    val login: String
)