package com.insightapp.nocontrole.model.room
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.insightapp.nocontrole.model.dao.CategoriaDao
import com.insightapp.nocontrole.model.entity.Categoria
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Categoria::class), version = 1, exportSchema = false)
abstract class CategoriaRoomDatabase : RoomDatabase() {

    abstract fun categoriaDao(): CategoriaDao

    private class CategoriaDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.categoriaDao())
                }
            }
        }

        suspend fun populateDatabase(categoriaDao: CategoriaDao) {
            categoriaDao.deleteAll()

            var categoria = Categoria(0, "Alimentação", 0, "FFFFFF", "A")
            categoriaDao.insert(categoria)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: CategoriaRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): CategoriaRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        CategoriaRoomDatabase::class.java,
                        "word_database"
                    )
                        .addCallback(CategoriaDatabaseCallback(scope))
                        .build()
                    INSTANCE = instance
                    instance
                }
            }
        }
    }
}