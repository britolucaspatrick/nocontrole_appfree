package com.insightapp.nocontrole.model.room
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.insightapp.nocontrole.model.dao.CategoriaDao
import com.insightapp.nocontrole.model.entity.Categoria

@Database(entities = arrayOf(Categoria::class), version = 1, exportSchema = false)
abstract class CategoriaRoomDatabase : RoomDatabase() {
    abstract fun categoriaDao(): CategoriaDao

    companion object {
        @Volatile
        private var INSTANCE: CategoriaRoomDatabase? = null

        fun getDatabase(context: Context): CategoriaRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CategoriaRoomDatabase::class.java,
                    "categoria_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}