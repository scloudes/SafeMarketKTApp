package com.app.safemarket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.safemarket.model.Marker
import com.app.safemarket.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository):ViewModel() {

    val myResponse : MutableLiveData<Marker> = MutableLiveData()
    val myResponse2 : MutableLiveData<Response<Marker>> = MutableLiveData()

    fun getMarker(){
        viewModelScope.launch{
            val response:Marker = repository.getMarker()
            myResponse.value=response
        }
    }

    fun updateMarker(marker: Marker){
        viewModelScope.launch {
            val response = repository.updateMarker(marker)
            myResponse2.value = response
        }
    }
}