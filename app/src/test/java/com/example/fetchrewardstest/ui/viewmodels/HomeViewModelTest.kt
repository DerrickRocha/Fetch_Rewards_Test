package com.example.fetchrewardstest.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.fetchrewardstest.interactors.HomeInteractor
import com.example.fetchrewardstest.models.Item
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Test
    fun getItems_whenError_errorLiveDataShouldBeTriggered() = runTest{
        val mockInteractor = object : HomeInteractor {
            override suspend fun getItems(): List<Item> {
                throw Exception("Error getting items")
            }
        }
        val dispatcher = StandardTestDispatcher(testScheduler)
        val viewModel = HomeViewModel(mockInteractor, dispatcher)
        viewModel.getItems()
        advanceUntilIdle()
        val error = viewModel.error.value
        val items = viewModel.items.value
        assert(items.isNullOrEmpty() && error != null)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getItems_whenItemsAvailable_itemsLiveDataShouldTriggered() = runTest{
        val mockInteractor = object : HomeInteractor {
            override suspend fun getItems(): List<Item> {
                return listOf(
                    Item(1, 1, "a"),
                    Item(2, 2, "b"),
                    Item(3, 3, "c"),
                    Item(4, 4, "d"),
                )
            }
        }
        val dispatcher = StandardTestDispatcher(testScheduler)
        val viewModel = HomeViewModel(mockInteractor, dispatcher)
        viewModel.getItems()
        advanceUntilIdle()
        val error = viewModel.error.value
        val items = viewModel.items.value
        assert(!items.isNullOrEmpty() && error == null)
    }
}