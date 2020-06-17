package com.insightapp.nocontrole.viewmodel.ui.lancto
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.insightapp.nocontrole.R
import com.insightapp.nocontrole.model.entity.Lancto
import kotlinx.android.synthetic.main.dialog_new_desrec.view.*
import kotlinx.android.synthetic.main.fragment_lancto.view.*
import java.text.SimpleDateFormat
import java.util.*


class LanctoFragment : Fragment() {

    private lateinit var viewModel: LanctoViewModel
    private lateinit var spinnerAdapter: SpinnerAdapter
    private var cal = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(LanctoViewModel::class.java)
        var root = inflater.inflate(R.layout.fragment_lancto, container, false)
        updateDateview(root)

        val adapter = context?.let { LanctoAdapter(it, object:LanctoAdapter.ItemSelectedListener {
            override fun onItemSelectedDel(item: Lancto) {
                val alertDialog = AlertDialog.Builder(context, 0)
                alertDialog.setMessage("Confirma cancelar este lançamento?")
                alertDialog.setPositiveButton("Sim", DialogInterface.OnClickListener { dialog, which ->
                    viewModel.cancel(item.id)
                })
                alertDialog.setNegativeButton("Não", null)
                alertDialog.show()
            }

            override fun onItemSelectedAlt(item: Lancto) {
                val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_new_desrec, null)
                val mBuilder = context?.let { it1 -> AlertDialog.Builder(it1).setView(mDialogView) }
                val  mAlertDialog = mBuilder!!.show()

                mDialogView.btn_salvar.setOnClickListener {
                    viewModel.update(item)
                    mAlertDialog.dismiss()
                }
            }
        })}

        root.recyViewLancto.adapter = adapter
        root.recyViewLancto.layoutManager = LinearLayoutManager(context)

        viewModel.allLanctos.observe(viewLifecycleOwner, Observer { lanctos ->
            lanctos?.let {
                adapter!!.setLanctos(lanctos)
            }
        })

        viewModel.allCategorias.observe(viewLifecycleOwner, Observer { cat ->
            cat?.let {
                spinnerAdapter = context?.let { it1 -> SpinnerAdapter(it1, it) }!!
            }
        })

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateview(root)
            }
        }

        root.btn_datelancto.setOnClickListener{
            context?.let { it1 ->
                DatePickerDialog(
                    it1,
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }
        }

        root.novoLancto.setOnClickListener {
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_new_desrec, null)
            val mBuilder = context?.let { it1 -> AlertDialog.Builder(it1).setView(mDialogView) }

            if (spinnerAdapter != null)
                mDialogView.spinner_category.setAdapter(spinnerAdapter)

            val  mAlertDialog = mBuilder!!.show()

            mDialogView.btn_salvar.setOnClickListener {
                val lancto = Lancto(0,
                    mDialogView.desc_lancto.text.toString().trim(),
                    mDialogView.spinner_tp_lancto.selectedItemPosition,
                    spinnerAdapter!!.getItemId(mDialogView.spinner_category.selectedItemPosition).toInt(),
                    Date(),
                    "A",
                    mDialogView.valor_lancto.text.toString().toDouble())

                viewModel.insert(lancto)
                mAlertDialog.dismiss()
            }
        }
        return root
    }

    fun updateDateview(view: View){
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        view.btn_datelancto.text = sdf.format(cal.getTime())
    }

}
