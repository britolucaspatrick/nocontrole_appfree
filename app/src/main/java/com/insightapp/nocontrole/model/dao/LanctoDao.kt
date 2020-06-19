package com.insightapp.nocontrole.model.dao
import androidx.lifecycle.LiveData
import androidx.room.*
import com.insightapp.nocontrole.model.entity.Lancto
import java.time.Month

@Dao
interface LanctoDao {

    @Query("SELECT * from lancto WHERE st_registro != 'C'")
    fun getAll(): LiveData<List<Lancto>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(lancto: Lancto)

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
            "AND strftime('%m', lancto.dt_lancto) = :month " +
            "AND categoria.tp_lancto = 0")
     fun totByRecByMonth(month: Int) : LiveData<Double>

    @Query("SELECT SUM(lancto.valor) " +
            "FROM lancto " +
            "INNER JOIN categoria ON lancto.tp_categoria = categoria.id " +
            "WHERE lancto.st_registro != 'C' " +
            "AND strftime('%m', lancto.dt_lancto) = :month " +
            "AND categoria.tp_lancto = 1")
     fun totByDescByMonth(month: Int): LiveData<Double>

}