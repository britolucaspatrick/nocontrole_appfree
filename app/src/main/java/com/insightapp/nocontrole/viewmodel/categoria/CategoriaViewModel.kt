package com.insightapp.nocontrole.viewmodel.categoria

import android.app.Application
import androidx.lifecycle.*
import com.insightapp.nocontrole.model.entity.Categoria
import com.insightapp.nocontrole.model.repository.CategoriaRepository
import com.insightapp.nocontrole.model.room.CategoriaRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriaViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CategoriaRepository

    val allCategorias: LiveData<List<Categoria>>

    init {
        val catDao = CategoriaRoomDatabase.getDatabase(application, viewModelScope).categoriaDao()
        repository = CategoriaRepository(catDao)
        allCategorias = repository.allCategorias
    }

    fun insert(categoria: Categoria) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(categoria)
    }

}
