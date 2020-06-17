package com.insightapp.nocontrole.model.dao
import androidx.lifecycle.LiveData
import androidx.room.*
import com.insightapp.nocontrole.model.entity.Categoria

@Dao
interface CategoriaDao {

    @Query("SELECT * from categoria WHERE st_registro != 'C'")
    fun getAll(): LiveData<List<Categoria>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(categoria: Categoria)

    @Query("DELETE FROM categoria")
    suspend fun deleteAll()

    @Query("UPDATE categoria SET st_registro = ${"'C'"} WHERE id = :id")
    suspend fun cancel(id: Int)

    @Update
    suspend fun update(categoria: Categoria)

}