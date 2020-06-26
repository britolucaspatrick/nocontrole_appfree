package com.insightapp.nocontrole.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.insightapp.nocontrole.model.dao.CategoriaDao
import com.insightapp.nocontrole.model.dao.LanctoDao
import com.insightapp.nocontrole.model.entity.Categoria
import com.insightapp.nocontrole.model.entity.Lancto
import com.insightapp.nocontrole.utils.Converters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/*
* Classe responsável pela instância do banco de dados (armazenado do dispositivo)
* compondo o conjunto de classes de acesso aos objetos
* */
@Database(entities = arrayOf(Lancto::class, Categoria::class), version = 1, exportSchema = false)
@TypeConverters(Converters::class)
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

        var TEST_MODE = false
        private val databaseName = "database"



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
                if (INSTANCE == null) {
                    if (TEST_MODE){
                        synchronized(this) {
                            INSTANCE = Room.inMemoryDatabaseBuilder(context, AppRoomDatabase::class.java)
                                .allowMainThreadQueries()
                                .build()
                            return INSTANCE!!
                        }
                    }else{
                        synchronized(this) {
                            INSTANCE = Room.databaseBuilder(
                                context,
                                AppRoomDatabase::class.java,
                                databaseName
                            )
                                .fallbackToDestructiveMigration()
                                .build()
                            return INSTANCE!!
                        }
                    }
                }


                return INSTANCE!!
            }
        }

        private fun close() {
            INSTANCE?.close()
        }
    }
}