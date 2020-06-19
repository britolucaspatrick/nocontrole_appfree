package com.insightapp.nocontrole.viewmodel.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.github.mikephil.charting.data.*
import com.insightapp.nocontrole.R
import com.insightapp.nocontrole.utils.MonthNameById
import com.insightapp.nocontrole.viewmodel.ui.lancto.LanctoViewModel
import kotlinx.android.synthetic.main.dialog_month_actual.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var month = Calendar.getInstance().time.month

    /*The system calls this when it's time for the fragment to draw its user interface for the first time.
    To draw a UI for your fragment, you must return a View from this method that is the root
    of your fragment's layout. You can return null if the fragment does not provide a UI.*/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.month = month
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        root.btn_actual_month.text = MonthNameById.get(month)
        root.btn_actual_month.setOnClickListener{
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_month_actual, null)
            val mBuilder = context?.let { it1 -> AlertDialog.Builder(it1).setView(mDialogView) }
            val  mAlertDialog = mBuilder!!.show()

            mDialogView.spinner_month_actual.setSelection(month)

            mDialogView.btn_change_month.setOnClickListener{
                root.btn_actual_month.text = mDialogView.spinner_month_actual.selectedItem as String
                findDadosToCreateDashboardByMonth(month)
                mAlertDialog.dismiss()
            }
        }

        //CONFIG CHARTS
        root.chartBalanco.data = barData
//        root.chartDespCategoria.data = pieData;
//        root.chartRecCategoria.data = pieData1;
//        root.chartDespMes.data = lineData

        return root
    }



    private fun findDadosToCreateDashboardByMonth(month: Int) {
//        var listbar = ArrayList<PieEntry>()
//        listbar.add(0, PieEntry(receita.toFloat(), "Despesa"))
//        listbar.add(1, PieEntry(despesa.toFloat(), "Receita"))
//        var barDataSet = PieDataSet(listbar, "Balanço")
//        var barData = PieData(barDataSet)


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
