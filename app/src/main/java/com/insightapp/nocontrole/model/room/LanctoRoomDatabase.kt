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
import com.insightapp.nocontrole.view.MainActivity
import com.insightapp.nocontrole.viewmodel.ui.categoria.CategoriaViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Lancto::class), version = 1, exportSchema = false)
abstract class LanctoRoomDatabase : RoomDatabase() {

    abstract fun lanctoDao(): LanctoDao

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
        private var INSTANCE: LanctoRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): LanctoRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        LanctoRoomDatabase::class.java,
                        "LanctoRoomDatabase"
                    )
                        .addCallback(CategoriaDatabaseCallback(scope))
                        .build()
                    INSTANCE = instance
                    instance
                }
            }
        }

        fun getDatabase(context: Context): LanctoRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context,
                        LanctoRoomDatabase::class.java,
                        "LanctoRoomDatabase"
                    ).build()
                    INSTANCE = instance
                    instance
                }
            }
        }
    }
}