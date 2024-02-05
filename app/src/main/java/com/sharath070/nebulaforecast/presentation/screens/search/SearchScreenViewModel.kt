package com.sharath070.nebulaforecast.presentation.screens.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sharath070.nebulaforecast.data.repository.Repository
import com.sharath070.nebulaforecast.domain.models.addressAutoComplete.AddressList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _addressList = MutableStateFlow<List<AddressList>?>(listOf())
    val addressList = _addressList.asStateFlow()


    fun onSearchTextChange(text: String) {
        _searchText.value = text
        onTextChangeObserved()
    }

    @OptIn(FlowPreview::class)
    private fun onTextChangeObserved() {
        viewModelScope.launch(Dispatchers.IO) {
            searchText.debounce(1000)
                .collect {
                    getAddress(it)
                }
        }
    }

    private suspend fun getAddress(text: String) {
        Log.d("Address", "GetAddress called -> $text")
        _addressList.value = repository.remote.getAddress(text).data
    }

}