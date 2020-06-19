package com.insightapp.nocontrole.model.repository

import androidx.lifecycle.LiveData
import com.insightapp.nocontrole.model.dao.CategoriaDao
import com.insightapp.nocontrole.model.entity.Categoria

class CategoriaRepository (private val categoriaDao: CategoriaDao) {

    val allCategoriasD: LiveData<List<Categoria>> = categoriaDao.getAllD()
    val allCategorias: LiveData<List<Categoria>> = categoriaDao.getAll()

    suspend fun insert(categoria: Categoria) {
        categoriaDao.insert(categoria)
    }

    suspend fun cancel(id: Int){
        categoriaDao.cancel(id)
    }

    suspend fun update(categoria: Categoria) {
        categoriaDao.update(categoria)
    }


}