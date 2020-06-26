package com.insightapp.nocontrole.model.dao
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.insightapp.nocontrole.model.entity.Lancto
import java.time.Month
import java.util.*

/*Interface de objeto de acesso a dados através das anotações
* disponibilizadas pela biblioteca room*/
@Dao
interface LanctoDao {

    @Query("SELECT * from lancto WHERE st_registro != 'C'")
    fun getAll(): LiveData<List<Lancto>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(lancto: Lancto)

    @Query("DELETE FROM lancto")
    suspend fun deleteAll()

    @Query("UPDATE lancto SET st_registro = ${"'C'"} WHERE id = :id")
    suspend fun cancel(id: Int)

    @Update
    suspend fun update(lancto: Lancto)

    @Query("SELECT SUM(lancto.valor) " +
            "FROM lancto " +
            "INNER JOIN categoria ON lancto.tp_categoria = categoria.id " +
            "WHERE lancto.st_registro != 'C' " +
            "AND categoria.tp_lancto = 0 " +
            "AND lancto.dt_lancto BETWEEN :init AND :final ")
    suspend fun totByRecByMonth(init: Date, final: Date) : Float

    @Query("SELECT SUM(lancto.valor) " +
            "FROM lancto " +
            "INNER JOIN categoria ON lancto.tp_categoria = categoria.id " +
            "WHERE lancto.st_registro != 'C' " +
            "AND categoria.tp_lancto = 1 " +
            "AND lancto.dt_lancto BETWEEN :init AND :final ")
    suspend fun totByDescByMonth(init: Date, final: Date): Float

    @Query("SELECT COUNT(*) FROM lancto")
    fun count(): Int
}