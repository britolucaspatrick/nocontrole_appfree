package com.insightapp.nocontrole.viewmodel.ui.categoria
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.insightapp.nocontrole.model.entity.Categoria
import com.insightapp.nocontrole.model.repository.CategoriaRepository
import com.insightapp.nocontrole.model.room.AppRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriaViewModel(application: Application) : AndroidViewModel(application) {

    val allCategoriasD: LiveData<List<Categoria>>
    private val repository: CategoriaRepository

    init {
        val catDao = AppRoomDatabase.getDatabase(application).categoriaDao()
        repository = CategoriaRepository(catDao)
        allCategoriasD = repository.allCategoriasD
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
