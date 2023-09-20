package uz.ikhtidev.mvvmwithhilt.networking

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import uz.ikhtidev.mvvmwithhilt.models.GithubUser

interface GithubService {

    @GET("users")
    fun getGithubUsers(): Flow<List<GithubUser>>
}