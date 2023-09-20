package uz.ikhtidev.mvvmwithhilt.utils.network

sealed class Resource<T>{
    class Loading<T>: Resource<T>()
    class Success<T: Any>(val data:T): Resource<T>()
    class Failure<T: Any>(val throwable: Throwable): Resource<T>()
}