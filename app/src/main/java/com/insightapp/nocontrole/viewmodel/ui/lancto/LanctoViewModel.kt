package com.insightapp.nocontrole.viewmodel.ui.lancto

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.insightapp.nocontrole.model.entity.Categoria
import com.insightapp.nocontrole.model.entity.Lancto
import com.insightapp.nocontrole.model.repository.CategoriaRepository
import com.insightapp.nocontrole.model.repository.LanctoRepository
import com.insightapp.nocontrole.model.room.AppRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LanctoViewModel(application: Application) : AndroidViewModel(application) {
    val allCategorias: LiveData<List<Categoria>>
    val allCategoriasD: LiveData<List<Categoria>>
    val allLanctos: LiveData<List<Lancto>>
    private val repository: LanctoRepository
    private val repositoryC: CategoriaRepository
    private val repositoryD: CategoriaRepository

    init {
        val lanctDao = AppRoomDatabase.getDatabase(application).lanctoDao()
        repository = LanctoRepository(lanctDao)
        allLanctos = repository.allLanctos


        val catDao = AppRoomDatabase.getDatabase(application).categoriaDao()
        repositoryC = CategoriaRepository(catDao)
        allCategorias = repositoryC.allCategorias

        repositoryD = CategoriaRepository(catDao)
        allCategoriasD = repositoryD.allCategoriasD

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
