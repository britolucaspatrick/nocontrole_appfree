package com.insightapp.nocontrole.view

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.insightapp.nocontrole.R
import com.insightapp.nocontrole.utils.MonthNameById
import com.insightapp.nocontrole.viewmodel.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.dialog_month_actual.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var barData: PieData
    private lateinit var listbar: ArrayList<PieEntry>
    private var monthCurrent = Calendar.getInstance().time.month
    private lateinit var barDataSet: PieDataSet
    private lateinit var root: View
    private val colorsBalanco: ArrayList<Int>
            = ArrayList(Arrays.asList(Color.rgb(255, 112, 67),Color.rgb(41, 182, 246)))


    /*The system calls this when it's time for the fragment to draw its user interface for the first time.
    To draw a UI for your fragment, you must return a View from this method that is the root
    of your fragment's layout. You can return null if the fragment does not provide a UI.*/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)


        root.btn_actual_month.text = MonthNameById.get(monthCurrent)
        root.btn_actual_month.setOnClickListener{
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_month_actual, null)
            val mBuilder = context?.let { it1 -> AlertDialog.Builder(it1).setView(mDialogView) }
            val  mAlertDialog = mBuilder!!.show()

            mDialogView.spinner_month_actual.setSelection(monthCurrent)

            mDialogView.btn_change_month.setOnClickListener{
                root.btn_actual_month.text = mDialogView.spinner_month_actual.selectedItem as String
                monthCurrent = mDialogView.spinner_month_actual.selectedItemPosition
                createChartBalanco(monthCurrent)
                mAlertDialog.dismiss()
            }
        }

        createChartBalanco(monthCurrent)
        createChartCategoriaDesp(monthCurrent)

        return root
    }

    private fun createChartCategoriaDesp(month: Int) {
        GlobalScope.launch(Dispatchers.Main){

            listbar = ArrayList()
            listbar.add(0, PieEntry(0f))
            listbar.add(1, PieEntry(0f))

            barDataSet = PieDataSet(listbar, "Categoria Despesa")
            barData = PieData(barDataSet)
            barData.setDrawValues(true)
            barData.setValueTextSize(10f)
            barData.setValueTextColor(Color.WHITE)
            //create dinamic - barDataSet.colors = colorsBalanco

            root.chartDespCategoria.setDrawCenterText(false)
            root.chartDespCategoria.setDrawEntryLabels(false)
            root.chartDespCategoria.setDrawRoundedSlices(false)
            root.chartDespCategoria.setDrawSlicesUnderHole(true)
            root.chartDespCategoria.description = null
            root.chartDespCategoria.setUsePercentValues(false)
            root.chartDespCategoria.setNoDataText("Nada encontrado")
            root.chartDespCategoria.setDrawHoleEnabled(true)
            root.chartDespCategoria.setHoleRadius(50f)
            root.chartDespCategoria.setTransparentCircleRadius(55f)
            root.chartDespCategoria.setRotationEnabled(false)
            root.chartDespCategoria.setHighlightPerTapEnabled(true);
            root.chartDespCategoria.getLegend().isEnabled = false
            root.chartDespCategoria.animateXY(2000, 2000)
            root.chartDespCategoria.data = barData
        }
    }

    private fun createChartBalanco(month: Int) {

        GlobalScope.launch(Dispatchers.Main) {
            var init = Calendar.getInstance()
            init.set(2020, month, 1, 0,0)
            var final = Calendar.getInstance()
            final.set(2020, month, 29, 0, 0)

            var descValue:Float? = homeViewModel.getDespByMonth(init.time, final.time)
            var recValue:Float? = homeViewModel.getRecByMonth(init.time, final.time)

            if (descValue == null)
                descValue = 0f
            if (recValue == null)
                recValue = 0f

            root.textBalReceita.setText("Receita \nR$ ${recValue}")
            root.textBalDespesa.setText("Despesa \nR$ ${descValue}")

            listbar = ArrayList()
            listbar.add(0, PieEntry(descValue))
            listbar.add(1, PieEntry(recValue))
            barDataSet = PieDataSet(listbar, "Balanço")
            barDataSet.colors = colorsBalanco
            barData = PieData(barDataSet)

            barData.setDrawValues(true)
            barData.setValueTextSize(10f)
            barData.setValueTextColor(Color.WHITE)
            barData.setValueFormatter(PercentFormatter(root.chartBalanco))
            root.chartBalanco.setDrawCenterText(false)
            root.chartBalanco.setDrawEntryLabels(false)
            root.chartBalanco.setDrawRoundedSlices(false)
            root.chartBalanco.setDrawSlicesUnderHole(true)
            root.chartBalanco.description = null
            root.chartBalanco.setUsePercentValues(true)
            root.chartBalanco.setNoDataText("Nada encontrado")
            root.chartBalanco.setDrawHoleEnabled(true)
            root.chartBalanco.setHoleRadius(50f)
            root.chartBalanco.setTransparentCircleRadius(55f)
            root.chartBalanco.setRotationEnabled(false)
            root.chartBalanco.setHighlightPerTapEnabled(true);
            root.chartBalanco.getLegend().isEnabled = false
            root.chartBalanco.animateXY(2000, 2000)

            root.chartBalanco.data = barData
        }
    }


    private fun findDataToCreateDashboardByMonth(month: Int) {




        //chartDespMes
//        val values: ArrayList<Entry> = ArrayList()
//        values.add(Entry(5f, 10f))
//        values.add(Entry(10f, 30f))
//        values.add(Entry(20f, 45f))
//        values.add(Entry(5f, 5f))
//
//        var linedataset = LineDataSet(values, "Despesa por mês")
//        var lineData = LineData(linedataset)

//
//        //chartRecCategoria
//        var list1 = ArrayList<PieEntry>()
//        list1.add(0, PieEntry(25f, "123"))
//        list1.add(1, PieEntry(25f, "123"))
//        list1.add(2, PieEntry(25f, "123"))
//        list1.add(3, PieEntry(25f, "123"))
//        var pieDataSet1 = PieDataSet(list, "Despesa por categoria")
//        var pieData1 = PieData(pieDataSet1)

//
//        var list = ArrayList<PieEntry>()
//        list.add(0, PieEntry(25f, "123"))
//        list.add(1, PieEntry(25f, "123"))
//        list.add(2, PieEntry(25f, "123"))
//        list.add(3, PieEntry(25f, "123"))
//        var pieDataSet = PieDataSet(list, "Despesa por categoria")
//        var pieData = PieData(pieDataSet)
    }


}

