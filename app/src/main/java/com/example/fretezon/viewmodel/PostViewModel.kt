package com.example.fretezon.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fretezon.models.Frete
import com.example.fretezon.models.Post
import com.example.fretezon.utils.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FreteViewModel: ViewModel() {
    private val _fretes = MutableStateFlow<List<Frete>>(emptyList())

    val fretes: StateFlow<List<Frete>> = _fretes

    fun fetchFrete(){
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getFrete()
                _fretes.value= response
                Log.i("RESPONSE", response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("RESPONSE", e.toString())

            }
        }
    }

}