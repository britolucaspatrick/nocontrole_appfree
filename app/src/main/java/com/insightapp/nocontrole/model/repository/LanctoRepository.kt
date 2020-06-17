package com.insightapp.nocontrole.model.repository

import androidx.lifecycle.LiveData
import com.insightapp.nocontrole.model.dao.LanctoDao
import com.insightapp.nocontrole.model.entity.Lancto

class LanctoRepository (private val lanctoDao: LanctoDao) {

    val allLanctos: LiveData<List<Lancto>> = lanctoDao.getAll()

    suspend fun insert(lancto: Lancto) {
        lanctoDao.insert(lancto)
    }

    suspend fun cancel(id: Int){
        lanctoDao.cancel(id)
    }

    suspend fun update(lancto: Lancto) {
        lanctoDao.update(lancto)
    }


}