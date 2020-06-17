package com.insightapp.nocontrole.viewmodel.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.github.mikephil.charting.data.*
import com.insightapp.nocontrole.R
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)


        var list = ArrayList<PieEntry>()
        list.add(0, PieEntry(25f, "123"))
        list.add(1, PieEntry(25f, "123"))
        list.add(2, PieEntry(25f, "123"))
        list.add(3, PieEntry(25f, "123"))
        var pieDataSet = PieDataSet(list, "Despesa por categoria")
        var pieData = PieData(pieDataSet)
        root.chartDespCategoria.data = pieData;

        //chartRecCategoria
        var list1 = ArrayList<PieEntry>()
        list1.add(0, PieEntry(25f, "123"))
        list1.add(1, PieEntry(25f, "123"))
        list1.add(2, PieEntry(25f, "123"))
        list1.add(3, PieEntry(25f, "123"))
        var pieDataSet1 = PieDataSet(list, "Despesa por categoria")
        var pieData1 = PieData(pieDataSet1)
        root.chartRecCategoria.data = pieData1;


        var listbar = ArrayList<BarEntry>()
        listbar.add(BarEntry(5f,30f))
        listbar.add(BarEntry(10f,20f))
        var barDataSet = BarDataSet(listbar, "Balanço")
        var barData = BarData(barDataSet)
        root.chartBalanco.data = barData

        //chartDespMes
        val values: ArrayList<Entry> = ArrayList()
        values.add(Entry(5f, 10f))
        values.add(Entry(10f, 30f))
        values.add(Entry(20f, 45f))
        values.add(Entry(5f, 5f))

        var linedataset = LineDataSet(values, "Despesa por mês")
        var lineData = LineData(linedataset)
        root.chartDespMes.data = lineData

        return root
    }
}
