package com.insightapp.nocontrole.viewmodel.ui.categoria

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.insightapp.nocontrole.R
import com.insightapp.nocontrole.model.entity.Categoria
import kotlinx.android.synthetic.main.dialog_new_categ.view.*
import kotlinx.android.synthetic.main.fragment_categoria.view.*

class CategoriaFragment : Fragment() {

    private lateinit var viewModel: CategoriaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(CategoriaViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_categoria, container, false)

        val adapter = context?.let { CategoriaAdapter(it, object:CategoriaAdapter.ItemSelectedListener {
             override fun onItemSelectedDel(item: Categoria) {
                 val alertDialog = AlertDialog.Builder(context, 0)
                 alertDialog.setMessage("Confirma cancelar esta categoria?")
                 alertDialog.setPositiveButton("Sim", DialogInterface.OnClickListener { dialog, which ->
                     viewModel.cancel(item.id)
                 })
                 alertDialog.setNegativeButton("Não", null)

                 alertDialog.show()
            }

            override fun onItemSelectedAlt(item: Categoria) {
                val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_new_categ, null)
                val mBuilder = context?.let { it1 -> AlertDialog.Builder(it1).setView(mDialogView) }
                mDialogView.spinner_tp_lancto.setSelection(item.tp_lancto)
                mDialogView.desc_new_categ.setText(item.desc)
                val  mAlertDialog = mBuilder!!.show()

                mDialogView.btn_salvar.setOnClickListener {
                    item.tp_lancto = mDialogView.spinner_tp_lancto.selectedItemPosition
                    item.desc = mDialogView.desc_new_categ.text.toString()

                    viewModel.update(item)
                    mAlertDialog.dismiss()
                }
            }
        })}

        root.recyViewCategorias.adapter = adapter
        root.recyViewCategorias.layoutManager = LinearLayoutManager(context)

        viewModel.allCategoriasD.observe(viewLifecycleOwner, Observer { categorias ->
            categorias?.let {
                adapter!!.setCategorias(categorias)
            }
        })



        root.novoCategoria.setOnClickListener {
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_new_categ, null)
            val mBuilder = context?.let { it1 -> AlertDialog.Builder(it1).setView(mDialogView) }
            val  mAlertDialog = mBuilder!!.show()

            mDialogView.btn_salvar.setOnClickListener {
                val categoria = Categoria(0, mDialogView.desc_new_categ.text.toString().trim(), mDialogView.spinner_tp_lancto.selectedItemPosition, "FFFFFFF","A")
                viewModel.insert(categoria)
                mAlertDialog.dismiss()
            }
        }



        return root
    }


}

