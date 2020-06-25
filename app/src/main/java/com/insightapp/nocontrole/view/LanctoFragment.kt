package com.insightapp.nocontrole.view
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.insightapp.nocontrole.R
import com.insightapp.nocontrole.model.entity.Categoria
import com.insightapp.nocontrole.model.entity.Lancto
import com.insightapp.nocontrole.viewmodel.ui.lancto.LanctoAdapter
import com.insightapp.nocontrole.viewmodel.ui.lancto.LanctoViewModel
import com.insightapp.nocontrole.viewmodel.ui.lancto.SpinnerAdapter
import kotlinx.android.synthetic.main.dialog_new_desrec.view.*
import kotlinx.android.synthetic.main.fragment_lancto.view.*
import java.text.SimpleDateFormat
import java.util.*


class LanctoFragment : Fragment() {

    private lateinit var viewModel: LanctoViewModel
    private lateinit var spinnerAdapterD: SpinnerAdapter
    private var cal = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(LanctoViewModel::class.java)
        var root = inflater.inflate(R.layout.fragment_lancto, container, false)

        val adapter = context?.let {
            LanctoAdapter(
                it,
                object :
                    LanctoAdapter.ItemSelectedListener {
                    override fun onItemSelectedDel(item: Lancto) {
                        val alertDialog = AlertDialog.Builder(context, 0)
                        alertDialog.setMessage("Confirma cancelar este lançamento?")
                        alertDialog.setPositiveButton("Sim") { dialog, which ->
                            viewModel.cancel(item.id)
                        }
                        alertDialog.setNegativeButton("Não", null)
                        alertDialog.show()
                    }

                    override fun onItemSelectedAlt(item: Lancto) {
                        val mDialogView =
                            LayoutInflater.from(context).inflate(R.layout.dialog_new_desrec, null)
                        val mBuilder =
                            context?.let { it1 -> AlertDialog.Builder(it1).setView(mDialogView) }
                        val mAlertDialog = mBuilder!!.show()

                        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
                            override fun onDateSet(
                                view: DatePicker, year: Int, monthOfYear: Int,
                                dayOfMonth: Int
                            ) {
                                cal.set(Calendar.YEAR, year)
                                cal.set(Calendar.MONTH, monthOfYear)
                                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                                val myFormat = "dd/MM/yyyy"
                                val sdf = SimpleDateFormat(myFormat, Locale.US)
                                mDialogView.btn_datelancto!!.text = sdf.format(cal.getTime())
                            }
                        }

                        mDialogView.btn_datelancto.setOnClickListener {
                            context?.let { it1 ->
                                DatePickerDialog(
                                    it1,
                                    dateSetListener,
                                    cal.get(Calendar.YEAR),
                                    cal.get(Calendar.MONTH),
                                    cal.get(Calendar.DAY_OF_MONTH)
                                ).show()
                            }
                        }

                        mDialogView.desc_lancto.setText(item.desc)

                        val myFormat = "dd/MM/yyyy"
                        val sdf = SimpleDateFormat(myFormat, Locale.US)
                        mDialogView.btn_datelancto.text = sdf.format(item.dt_lancto!!.getTime())
                        mDialogView.spinner_category.adapter = spinnerAdapterD

                        mDialogView.valor_lancto.setText(item.valor.toString())

                        mDialogView.btn_salvar.setOnClickListener {
                            item.valor = mDialogView.valor_lancto.text.toString().toDouble()
                            item.desc = mDialogView.desc_lancto.text.toString()
                            item.tp_categoria =
                                (mDialogView.spinner_category.selectedItem as Categoria).id
                            item.dt_lancto = cal.getTime()

                            viewModel.update(item)
                            mAlertDialog.dismiss()
                        }
                    }
                })
        }

        root.recyViewLancto.adapter = adapter
        root.recyViewLancto.layoutManager = LinearLayoutManager(context)

        viewModel.allLanctos.observe(viewLifecycleOwner, Observer { lanctos ->
            lanctos?.let {
                adapter!!.setLanctos(lanctos)
            }
        })

        viewModel.allCategoriasD.observe(viewLifecycleOwner, Observer { cat ->
            cat?.let {
                spinnerAdapterD = context?.let { it1 ->
                    SpinnerAdapter(
                        it1,
                        it
                    )
                }!!
            }
        })

        root.novoLancto.setOnClickListener {
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_new_desrec, null)
            val mBuilder = context?.let { it1 -> AlertDialog.Builder(it1).setView(mDialogView) }

            if (spinnerAdapterD != null)
                mDialogView.spinner_category.setAdapter(spinnerAdapterD)

            val  mAlertDialog = mBuilder!!.show()

            val dateSetListener = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                       dayOfMonth: Int) {
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, monthOfYear)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val myFormat = "dd/MM/yyyy"
                    val sdf = SimpleDateFormat(myFormat, Locale.US)
                    mDialogView.btn_datelancto!!.text = sdf.format(cal.getTime())
                }
            }

            mDialogView.btn_datelancto.setOnClickListener{
                context?.let { it1 ->
                    DatePickerDialog(
                        it1,
                        dateSetListener,
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show()
                }
            }

            mDialogView.btn_salvar.setOnClickListener {
                val lancto = Lancto(0,
                    mDialogView.desc_lancto.text.toString().trim(),
                    spinnerAdapterD!!.getItemId(mDialogView.spinner_category.selectedItemPosition).toInt(),
                    cal.getTime(),
                    "A",
                    mDialogView.valor_lancto.text.toString().toDouble())

                viewModel.insert(lancto)
                mAlertDialog.dismiss()
            }
        }



        return root
    }


}

