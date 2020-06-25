package com.insightapp.nocontrole

import androidx.test.core.app.ApplicationProvider
import com.insightapp.nocontrole.model.dao.CategoriaDao
import com.insightapp.nocontrole.model.dao.LanctoDao
import com.insightapp.nocontrole.model.entity.Categoria
import com.insightapp.nocontrole.model.entity.Lancto
import com.insightapp.nocontrole.model.room.AppRoomDatabase
import org.junit.After
import org.junit.Test
import org.junit.Assert
import org.junit.Before
import java.io.IOException
import java.util.*

class AppRoomDatabaseUnitTest {
    private var categoriaDao: CategoriaDao? = null
    private var lanctoDao: LanctoDao? = null
    private lateinit var db: AppRoomDatabase

    @Before
    fun setup() {
        AppRoomDatabase.TEST_MODE = true

        db = AppRoomDatabase.getDatabase(ApplicationProvider.getApplicationContext())

        categoriaDao = db.categoriaDao()
        lanctoDao = db.lanctoDao()
    }

    @Test
    fun signInTest() {
    }

    @Test
    suspend fun find_desc_total(){
        val categoria = Categoria(0, "Salário", 0, "000000", st_registro = "A")
        categoriaDao?.insert(categoria)
        Assert.assertTrue("categoria criada com sucesso",categoriaDao?.count() == 1)

        val lancto = Lancto(0, "Mercado", 0, Date(), "A", 150.0)
        lanctoDao?.insert(lancto)

    }

    @Test
    fun count_lancto(){
        Assert.assertTrue("count correto", lanctoDao?.count() == 0)

    }

    @Test
    fun count_categoria(){
        val c = categoriaDao?.count()
        Assert.assertTrue("count correto",  c == 0)

    }



    @Test
    suspend fun insert(){
        val categoria = Categoria(0, "Salário", 0, "000000", st_registro = "A")
        categoriaDao?.insert(categoria)
        Assert.assertEquals(categoriaDao?.count(), 1)
    }



    @Test
    fun getAll(){
        categoriaDao?.getAll()
        Assert.assertEquals(categoriaDao?.count(), 0)
    }


    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}