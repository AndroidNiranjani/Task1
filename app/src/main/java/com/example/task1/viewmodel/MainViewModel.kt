package com.example.task1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.task1.model.CouponsModel
import com.example.task1.repository.CouponsListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository:CouponsListRepository):ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCoupons(0, "2fe16d86-c4ac-4980-84e6-d49d72c5987b", 0)
        }
    }

    val coupons: LiveData<CouponsModel>
    get() = repository.coupons
}