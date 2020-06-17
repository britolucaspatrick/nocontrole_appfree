package com.insightapp.nocontrole.viewmodel.ui.categoria
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.insightapp.nocontrole.model.entity.Categoria
import com.insightapp.nocontrole.model.repository.CategoriaRepository
import com.insightapp.nocontrole.model.room.CategoriaRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriaViewModel(application: Application) : AndroidViewModel(application) {

    val allCategorias: LiveData<List<Categoria>>
    private val repository: CategoriaRepository

    init {
        val catDao = CategoriaRoomDatabase.getDatabase(application).categoriaDao()
        repository = CategoriaRepository(catDao)
        allCategorias = repository.allCategorias
    }

    fun insert(categoria: Categoria) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(categoria)
    }

    fun update(categoria: Categoria) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(categoria)
    }

    fun cancel(id: Int) = viewModelScope.launch(Dispatchers.IO){
        repository.cancel(id)
    }
}
