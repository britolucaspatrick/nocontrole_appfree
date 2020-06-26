package com.insightapp.nocontrole.model.entity
import androidx.room.*
import com.insightapp.nocontrole.utils.Converters
import java.util.*

/*Entidade mapeada com anotações da biblioteca Room, para criar objeto
* no banco de dados*/
@Entity(tableName = "lancto")
data class Lancto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "desc") var desc: String,
    @ColumnInfo(name = "tp_categoria") var tp_categoria: Int,
    @ColumnInfo(name = "dt_lancto") var dt_lancto: Date?,
    @ColumnInfo(name = "st_registro") var st_registro: String = "A",
    @ColumnInfo(name = "valor") var valor: Double = 0.0

)