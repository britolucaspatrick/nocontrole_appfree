package com.insightapp.nocontrole.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.insightapp.nocontrole.model.dao.CategoriaDao
import com.insightapp.nocontrole.model.dao.LanctoDao
import com.insightapp.nocontrole.model.entity.Categoria
import com.insightapp.nocontrole.model.entity.Lancto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/*
* Classe responsável pela instância do banco de dados (armazenado do dispositivo)
* compondo o conjunto de classes de acesso aos objetos
* */
@Database(entities = arrayOf(Lancto::class, Categoria::class), version = 1, exportSchema = false)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun lanctoDao(): LanctoDao
    abstract fun categoriaDao(): CategoriaDao

    private class CategoriaDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {

                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppRoomDatabase::class.java,
                        "database"
                    )
                        .addCallback(CategoriaDatabaseCallback(scope))
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                    instance
                }
            }
        }

        fun getDatabase(context: Context): AppRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context,
                        AppRoomDatabase::class.java,
                        "database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                    instance
                }
            }
        }
    }
}