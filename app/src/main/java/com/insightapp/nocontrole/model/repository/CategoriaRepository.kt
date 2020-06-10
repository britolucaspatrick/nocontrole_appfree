package com.insightapp.nocontrole.model.repository

import androidx.lifecycle.LiveData
import com.insightapp.nocontrole.model.dao.CategoriaDao
import com.insightapp.nocontrole.model.entity.Categoria

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class CategoriaRepository (private val categoriaDao: CategoriaDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allCategorias: LiveData<List<Categoria>> = categoriaDao.getAll()

    suspend fun insert(categoria: Categoria) {
        categoriaDao.insert(categoria)
    }
}