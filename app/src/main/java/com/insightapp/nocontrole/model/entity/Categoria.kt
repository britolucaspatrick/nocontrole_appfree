package com.insightapp.nocontrole.model.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categoria")
data class Categoria(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "desc") var desc: String,
    @ColumnInfo(name = "tp_lancto") var tp_lancto: Int,
    @ColumnInfo(name = "hexa_color") var hexa_color: String,
    @ColumnInfo(name = "st_registro") var st_registro: String
)