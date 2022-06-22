package com.example.fetchrewardstest.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchrewardstest.Dependencies
import com.example.fetchrewardstest.interactors.HomeInteractor
import com.example.fetchrewardstest.models.Item
import com.example.fetchrewardstest.utils.SingleLiveEvent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(
    private val interactor: HomeInteractor = Dependencies.homeInteractor,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    val items : LiveData<List<Item>> get() = _items
    private val _items = SingleLiveEvent<List<Item>>()

    val showLoading: LiveData<Boolean> get() = _showLoading
    private val _showLoading = SingleLiveEvent<Boolean>()

    val error: LiveData<Exception> get() = _error
    private val _error = SingleLiveEvent<Exception>()

    fun getItems() {
        _showLoading.value = true
        viewModelScope.launch(dispatcher) {
            try {
                val items = interactor.getItems()
                _showLoading.postValue(false)
                _items.postValue(items)
            } catch (exception: Exception) {
                _showLoading.postValue(false)
                _error.postValue(exception)
            }
        }
    }
}