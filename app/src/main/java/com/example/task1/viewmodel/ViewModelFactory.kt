package com.example.task1.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.*
import com.example.task1.repository.CouponsListRepository

class ViewModelFactory(private val repository: CouponsListRepository) : NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}
