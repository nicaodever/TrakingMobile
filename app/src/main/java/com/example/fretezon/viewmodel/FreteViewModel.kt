package com.example.fretezon.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fretezon.models.Frete
import com.example.fretezon.utils.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

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
    fun postFrete(frete: Frete){
       viewModelScope.launch {
           try{
               val teste = Frete(
               "MONTANA",
               "Sumauma Park Shopping",
               "Shopping Grande Circular",
               "https://firebasestorage.googleapis.com/v0/b/qualisafe-7f25b.appspot.com/o/images%2Fgeladeira.jpg?alt=media&token=46de4978-701f-4746-a192-2eaece94617a","2025-02-21",
               "14:00:00",
               "Geladeira Electroluz")
               Log.i("SUGESTION", teste.toString())
               RetrofitInstance.api.createFrete(frete)
               Log.i("RESPONSEPOST", frete.toString())
           }catch (e: HttpException){
               e.printStackTrace()
               Log.i("RESPONSE", e.toString())
           }
       }
    }

}