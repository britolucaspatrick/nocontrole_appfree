package com.insightapp.nocontrole.viewmodel.categoria

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.insightapp.nocontrole.model.entity.Categoria

class CategoriaViewModel : ViewModel() {
    val categoriasLiveData: MutableLiveData<List<Categoria>> = MutableLiveData()

    fun getCategorias(){
        categoriasLiveData.value = createFakeBooks()
    }

    fun createFakeBooks(): List<Categoria>{
        return listOf(
            Categoria(
                0,
                "Alimentação",
                0,
                "#000000",
                "A"
            ),
            Categoria(
                0,
                "Moradia",
                0,
                "#000000",
                "A"
            ),
            Categoria(
                0,
                "Carro",
                0,
                "#000000",
                "A"
            )
        )
    }
}
