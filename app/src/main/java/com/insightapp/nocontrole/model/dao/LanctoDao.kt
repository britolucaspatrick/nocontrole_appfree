package com.insightapp.nocontrole.model.dao
import androidx.lifecycle.LiveData
import androidx.room.*
import com.insightapp.nocontrole.model.entity.Lancto

@Dao
interface LanctoDao {

    @Query("SELECT * from lancto WHERE st_registro != 'C'")
    fun getAll(): LiveData<List<Lancto>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(categoria: Lancto)

    @Query("DELETE FROM lancto")
    suspend fun deleteAll()

    @Query("UPDATE lancto SET st_registro = ${"'C'"} WHERE id = :id")
    suspend fun cancel(id: Int)

    @Update
    suspend fun update(lancto: Lancto)

}