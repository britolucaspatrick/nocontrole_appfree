package com.insightapp.nocontrole

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry
import com.insightapp.nocontrole.model.dao.CategoriaDao
import com.insightapp.nocontrole.model.dao.LanctoDao
import com.insightapp.nocontrole.model.entity.Categoria
import com.insightapp.nocontrole.model.entity.Lancto
import com.insightapp.nocontrole.model.room.AppRoomDatabase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Before
import java.util.*


@RunWith(AndroidJUnit4ClassRunner::class)
class ExampleInstrumentedTest {

    private lateinit var lanctoDao: LanctoDao
    private lateinit var categoriaDao: CategoriaDao
    private lateinit var db: AppRoomDatabase

    @Before
    fun start(){
        var appContext = InstrumentationRegistry.getInstrumentation().targetContext

        AppRoomDatabase.TEST_MODE = true
        db = AppRoomDatabase.getDatabase(appContext)
        categoriaDao = db.categoriaDao()
        lanctoDao = db.lanctoDao()
    }

    @Test
    fun count_categoria(){
        assertTrue("teste size",categoriaDao?.count() == 0)

    }

    @Test
     fun totByDescByMonth(){

//        val desc = lanctoDao.totByDescByMonth(init.time, final.time)
//        assertTrue("valor diferente ${desc}", desc == null)
    }

    @Test
    fun CategoriaWithTotal(){
        val cat1 = Categoria(0, "1", 1, "", "A")
        categoriaDao.insert(cat1)
        val cat2 = Categoria(0, "2", 1, "", "A")
        categoriaDao.insert(cat2)
        val cat3 = Categoria(0, "3", 1, "", "A")
        categoriaDao.insert(cat3)

        val lan1 = Lancto(0, "1", 1, Date(), "A", 100.0)
        lanctoDao.insert(lan1)
        val lan2 = Lancto(0, "2", 3, Date(), "A", 20.0)
        lanctoDao.insert(lan2)

        
    }


}
