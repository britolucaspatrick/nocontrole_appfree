package com.insightapp.nocontrole.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.insightapp.nocontrole.model.dao.LanctoDao
import com.insightapp.nocontrole.model.entity.Lancto
import java.util.*

/*Classe intermediária responsável por disponibilizar e/ou manutenção de dados,
* através da biblioteca room ou retrofit*/
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

    suspend fun getDespMonth(init: Date, final: Date): Float = lanctoDao.totByDescByMonth(init, final)
    suspend fun getRecMonth(init: Date, final: Date): Float = lanctoDao.totByRecByMonth(init, final)

}