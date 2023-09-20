package uz.ikhtidev.mvvmwithhilt.utils.mapper

import uz.ikhtidev.mvvmwithhilt.database.entity.GithubUserEntity
import uz.ikhtidev.mvvmwithhilt.models.GithubUser

fun GithubUser.mapToEntity(githubUser: GithubUser): GithubUserEntity {
    return GithubUserEntity(githubUser.id, githubUser.login, githubUser.avatar_url)
}