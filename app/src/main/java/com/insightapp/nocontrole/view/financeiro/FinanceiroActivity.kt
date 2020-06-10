package com.insightapp.nocontrole.view.financeiro
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.DatePicker
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.*
import com.insightapp.nocontrole.R
import com.insightapp.nocontrole.view.categoria.CategoriaActivity
import kotlinx.android.synthetic.main.activity_financeiro.*
import kotlinx.android.synthetic.main.dialog_new_desrec.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class FinanceiroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_financeiro)

        var list = ArrayList<PieEntry>()
        list.add(0, PieEntry(25f, "123"))
        list.add(1, PieEntry(25f, "123"))
        list.add(2, PieEntry(25f, "123"))
        list.add(3, PieEntry(25f, "123"))
        var pieDataSet = PieDataSet(list, "Despesa por categoria")
        var pieData = PieData(pieDataSet)
        chartDespCategoria.data = pieData;

        //chartRecCategoria
        var list1 = ArrayList<PieEntry>()
        list1.add(0, PieEntry(25f, "123"))
        list1.add(1, PieEntry(25f, "123"))
        list1.add(2, PieEntry(25f, "123"))
        list1.add(3, PieEntry(25f, "123"))
        var pieDataSet1 = PieDataSet(list, "Despesa por categoria")
        var pieData1 = PieData(pieDataSet1)
        chartRecCategoria.data = pieData1;


        var listbar = ArrayList<BarEntry>()
        listbar.add(BarEntry(5f,30f))
        listbar.add(BarEntry(10f,20f))
        var barDataSet = BarDataSet(listbar, "Balanço")
        var barData = BarData(barDataSet)
        chartBalanco.data = barData


        //chartDespMes
        val values: ArrayList<Entry> = ArrayList()
        values.add(Entry(5f, 10f))
        values.add(Entry(10f, 30f))
        values.add(Entry(20f, 45f))
        values.add(Entry(5f, 5f))

        var linedataset = LineDataSet(values, "Despesa por mês")
        var lineData = LineData(linedataset)
        chartDespMes.data = lineData

        novoLanctoButton.setOnClickListener {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_new_desrec, null)
            val mBuilder = AlertDialog.Builder(this).setView(mDialogView)
            val  mAlertDialog = mBuilder.show()
            var cal = Calendar.getInstance()
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

            mDialogView.btn_datelancto.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View) {
                    DatePickerDialog(this@FinanceiroActivity,
                        dateSetListener,
                        // set DatePickerDialog to point to today's date when it loads up
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show()
                }
            })
            mDialogView.btn_salvar.setOnClickListener {
                mAlertDialog.dismiss()
            }

            mDialogView.btn_new_category.setOnClickListener{
                val intent = Intent(this@FinanceiroActivity, CategoriaActivity::class.java)
                startActivityFromChild(this@FinanceiroActivity, intent, 0)
            }

        }


    }


}
