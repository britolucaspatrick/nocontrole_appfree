package com.insightapp.nocontrole

import android.util.Log
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
    fun total_balanco_despesa(){
        val categoria = Categoria(0, "Salário", 1, "000000", "A")
        categoriaDao.insert(categoria)

        var count:Int = categoriaDao.count()
        assertTrue("erro categoria", count == 1)

        val date = Calendar.getInstance()

        val lancto = Lancto(0, "Mercado", 0, date.time, "A", 150.0)
        lanctoDao.insert(lancto)
        count = lanctoDao.count()
        assertTrue("erro lancamento", count == 1)

        var init = Calendar.getInstance()
        init.set(2020, 1, 1)

        var final = Calendar.getInstance()
        final.set(2020, 12, 31)

        val desc = lanctoDao.sumValor(init.time, final.time)
        assertTrue("valor diferente ${desc}", desc == null)
    }



}
