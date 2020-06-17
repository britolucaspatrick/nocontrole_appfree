package com.insightapp.nocontrole.viewmodel.ui.lancto

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.insightapp.nocontrole.model.entity.Categoria
import com.insightapp.nocontrole.model.entity.Lancto
import com.insightapp.nocontrole.model.repository.CategoriaRepository
import com.insightapp.nocontrole.model.repository.LanctoRepository
import com.insightapp.nocontrole.model.room.CategoriaRoomDatabase
import com.insightapp.nocontrole.model.room.LanctoRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LanctoViewModel(application: Application) : AndroidViewModel(application) {
    val allCategorias: LiveData<List<Categoria>>
    val allLanctos: LiveData<List<Lancto>>
    private val repository: LanctoRepository
    private val repositoryC: CategoriaRepository

    init {
        val lanctDao = LanctoRoomDatabase.getDatabase(application).lanctoDao()
        repository = LanctoRepository(lanctDao)
        allLanctos = repository.allLanctos


        val catDao = CategoriaRoomDatabase.getDatabase(application).categoriaDao()
        repositoryC = CategoriaRepository(catDao)
        allCategorias = repositoryC.allCategorias

    }

    fun insert(lancto: Lancto) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(lancto)
    }

    fun update(lancto: Lancto) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(lancto)
    }


    fun cancel(id: Int) = viewModelScope.launch(Dispatchers.IO){
        repository.cancel(id)
    }
}
