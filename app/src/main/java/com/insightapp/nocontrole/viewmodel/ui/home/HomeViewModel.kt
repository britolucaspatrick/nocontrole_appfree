package com.insightapp.nocontrole.viewmodel.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.insightapp.nocontrole.model.repository.LanctoRepository
import com.insightapp.nocontrole.model.room.AppRoomDatabase

class HomeViewModel (application: Application) : AndroidViewModel(application){
    private val repository: LanctoRepository
    val desp: LiveData<Float>
    val rece: LiveData<Float>

    var month = 0

    init {
        val add = AppRoomDatabase.getDatabase(application).lanctoDao()
        repository = LanctoRepository(add, month)
        desp = repository.totByDescByMonth
        rece = repository.totByRecByMonth
    }


}

