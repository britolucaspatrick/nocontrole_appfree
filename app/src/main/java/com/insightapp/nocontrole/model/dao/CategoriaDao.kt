package com.insightapp.nocontrole.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.insightapp.nocontrole.model.entity.Categoria

/*Interface de objeto de acesso a dados através das anotações
* disponibilizadas pela biblioteca room*/
@Dao
interface CategoriaDao {

    @Query("SELECT * from categoria")
    fun getAll(): LiveData<List<Categoria>>

    @Query("SELECT * from categoria WHERE st_registro != 'C'")
    fun getAllD(): LiveData<List<Categoria>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insert(categoria: Categoria)

    @Query("DELETE FROM categoria")
    suspend fun deleteAll()

    @Query("UPDATE categoria SET st_registro = ${"'C'"} WHERE id = :id")
    suspend fun cancel(id: Int)

    @Update
    suspend fun update(categoria: Categoria)

    @Query("SELECT * from categoria WHERE id = :id LIMIT 1")
     fun first(id: Int): LiveData<Categoria>

    @Query("SELECT count(*) FROM categoria")
    fun count(): Int

}