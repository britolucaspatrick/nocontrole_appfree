package com.insightapp.nocontrole.viewmodel.ui.lancto
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.insightapp.nocontrole.R
import com.insightapp.nocontrole.model.entity.Lancto

class LanctoAdapter internal constructor(
    context: Context,
    itemSelectedListener:ItemSelectedListener
) : RecyclerView.Adapter<LanctoAdapter.LanctosViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var lanctos = emptyList<Lancto>()
    private lateinit var data: Lancto
    private var itemSelectedListener: ItemSelectedListener
    private val context: Context

    init {
        this.context = context
        this.itemSelectedListener = itemSelectedListener
    }

    interface ItemSelectedListener {
        fun onItemSelectedDel(item: Lancto)
        fun onItemSelectedAlt(item: Lancto)
    }

    inner class LanctosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanctosViewHolder {
        val itemView = inflater.inflate(R.layout.item_lancto, parent, false)
        return LanctosViewHolder(itemView)
    }

    override fun getItemCount() = lanctos.size

    override fun onBindViewHolder(holder: LanctosViewHolder, position: Int) {
        val current = lanctos[position]

    }

    internal fun setLanctos(cat: List<Lancto>) {
        this.lanctos = cat
        notifyDataSetChanged()
    }

}