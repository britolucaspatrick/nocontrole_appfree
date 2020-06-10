package com.insightapp.nocontrole.model.dao
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.insightapp.nocontrole.model.entity.Categoria

@Dao
interface CategoriaDao {

    @Query("SELECT * from categoria")
    fun getAll(): LiveData<List<Categoria>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(categoria: Categoria)

    @Query("DELETE FROM categoria")
    suspend fun deleteAll()
}