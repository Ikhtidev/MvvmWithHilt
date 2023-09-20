package uz.ikhtidev.mvvmwithhilt.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.launch
import uz.ikhtidev.mvvmwithhilt.database.entity.GithubUserEntity
import uz.ikhtidev.mvvmwithhilt.repository.UserRepository
import uz.ikhtidev.mvvmwithhilt.utils.mapper.mapToEntity
import uz.ikhtidev.mvvmwithhilt.utils.network.NetworkHelper
import uz.ikhtidev.mvvmwithhilt.utils.network.Resource
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _stateFlow = MutableStateFlow<Resource<List<GithubUserEntity>>>(Resource.Loading())
    val stateFlow get() = _stateFlow

    init {
        fetchData()
    }

    @OptIn(FlowPreview::class)
    private fun fetchData() {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                userRepository.getGithubUsers()
                    .catch {
                        _stateFlow.emit(Resource.Failure(it))
                    }
                    .flatMapConcat {
                        val list1 = ArrayList<GithubUserEntity>()
                        it.forEach { githubUser ->
                            list1.add(githubUser.mapToEntity(githubUser))
                        }
                        userRepository.addUsers(list1)
                    }
                    .collect {
                        _stateFlow.emit(Resource.Success(userRepository.getDatabaseUsers()))
                    }
            } else {
                if (userRepository.getUserCount() > 0) {
                    stateFlow.emit(Resource.Success(userRepository.getDatabaseUsers()))
                } else {
                    stateFlow.emit(Resource.Failure(Throwable("Internet not connection")))
                }
            }
        }
    }
}