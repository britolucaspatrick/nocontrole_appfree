package com.insightapp.nocontrole.view.categoria
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.DatePicker
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.insightapp.nocontrole.R
import com.insightapp.nocontrole.viewmodel.categoria.CategoriaAdapter
import com.insightapp.nocontrole.viewmodel.categoria.CategoriaViewModel
import kotlinx.android.synthetic.main.activity_categoria.*
import kotlinx.android.synthetic.main.activity_financeiro.*
import kotlinx.android.synthetic.main.dialog_new_desrec.view.*
import java.text.SimpleDateFormat
import java.util.*

class CategoriaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categoria)
        toolbar.title = getString(R.string.categoria)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val adapter = CategoriaAdapter(this)
        recyViewCategorias.adapter = adapter
        recyViewCategorias.layoutManager = LinearLayoutManager(this)



        novoCategoria.setOnClickListener {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_new_categ, null)
            val mBuilder = AlertDialog.Builder(this).setView(mDialogView)
            val  mAlertDialog = mBuilder.show()

            mDialogView.btn_salvar.setOnClickListener {
                mAlertDialog.dismiss()
            }

        }
    }

}
