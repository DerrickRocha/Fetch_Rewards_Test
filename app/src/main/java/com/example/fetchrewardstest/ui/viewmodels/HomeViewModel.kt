package com.example.fetchrewardstest.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    val error: LiveData<Exception> get() = _error
    private val _error = SingleLiveEvent<Exception>()

    fun getItems() {
        viewModelScope.launch(dispatcher) {
            try {
                _items.value = interactor.getItems()
            } catch (exception: Exception) {
                _error.value = exception
            }
        }
    }
}