package com.insightapp.nocontrole.viewmodel.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.insightapp.nocontrole.model.repository.LanctoRepository
import com.insightapp.nocontrole.model.room.AppRoomDatabase
import java.util.*

class HomeViewModel (application: Application) : AndroidViewModel(application){
    private val repository: LanctoRepository

    var month = 0

    init {
        val add = AppRoomDatabase.getDatabase(application).lanctoDao()
        repository = LanctoRepository(add)
    }

    suspend fun getDespByMonth(init: Date, final: Date) = repository.getDespMonth(init, final)
    suspend fun getRecByMonth(init: Date, final: Date) = repository.getRecMonth(init, final)


}

