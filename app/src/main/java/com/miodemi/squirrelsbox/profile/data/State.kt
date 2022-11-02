package com.miodemi.squirrelsbox.profile.data

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

sealed class State<T> {
    /*class Loading<T>(val flag: T) : State<T>()
    data class Success<T>(val data: T) : State<T>()
    data class Failed<T>(val error: String) : State<T>()

    companion object {
        fun <T> loading(flag: T) = Loading(flag)
        fun <T> success(data: T) = Success(data)
        fun <T> failed(error: String) = Failed<T>(error)
    }*/

    class Loading<T> : State<T>()
    data class Success<T>(val data: T) : State<T>()
    data class Failed<T>(val message: String) : State<T>()

    companion object {
        fun <T> loading() = Loading<T>()
        fun <T> success(data: T) = Success(data)
        fun <T> failed(message: String) = Failed<T>(message)
    }
}