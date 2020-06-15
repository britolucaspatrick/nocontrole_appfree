package com.insightapp.nocontrole.model.repository

import androidx.lifecycle.LiveData
import com.insightapp.nocontrole.model.dao.CategoriaDao
import com.insightapp.nocontrole.model.entity.Categoria

class CategoriaRepository (private val categoriaDao: CategoriaDao) {

    val allCategorias: LiveData<List<Categoria>> = categoriaDao.getAll()

    suspend fun insert(categoria: Categoria) {
        categoriaDao.insert(categoria)
    }
}