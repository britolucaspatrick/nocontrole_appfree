package com.insightapp.nocontrole.model.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "lancto")
data class Lancto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "desc") var desc: String,
    @ColumnInfo(name = "tp_lancto") var tp_lancto: Int,
    @ColumnInfo(name = "tp_categoria") var tp_categoria: Int,
    @ColumnInfo(name = "dt_lancto") var dt_lancto: Date,
    @ColumnInfo(name = "st_registro") var st_registro: String,
    @ColumnInfo(name = "valor") var valor: Double

)