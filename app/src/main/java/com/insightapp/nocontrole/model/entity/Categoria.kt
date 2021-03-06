package com.insightapp.nocontrole.model.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/*Entidade mapeada com anotações da biblioteca Room, para criar objeto
* no banco de dados*/
@Entity(tableName = "categoria")
data class Categoria(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "desc") var desc: String,
    @ColumnInfo(name = "tp_lancto") var tp_lancto: Int,
    @ColumnInfo(name = "hexa_color") var hexa_color: String,
    @ColumnInfo(name = "st_registro") var st_registro: String
)

data class CategoriaWithTotal(
    @Embedded val categoria: Categoria,
    val Total: Int
)