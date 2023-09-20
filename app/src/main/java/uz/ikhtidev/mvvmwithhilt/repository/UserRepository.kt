package uz.ikhtidev.mvvmwithhilt.repository

import kotlinx.coroutines.flow.flow
import uz.ikhtidev.mvvmwithhilt.database.dao.GithubUserDao
import uz.ikhtidev.mvvmwithhilt.database.entity.GithubUserEntity
import uz.ikhtidev.mvvmwithhilt.networking.GithubService
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val githubService: GithubService,
    private val githubUserDao: GithubUserDao
) {

    fun getGithubUsers() = githubService.getGithubUsers()

    fun addUsers(users:List<GithubUserEntity>) = flow { emit(githubUserDao.addUsers(users)) }

    fun getDatabaseUsers() = githubUserDao.getGithubUsers()

    fun getUserCount() = githubUserDao.getGithubUserCount()
}