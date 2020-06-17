package com.insightapp.nocontrole.viewmodel.ui.lancto

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SpinnerAdapter
import android.widget.TextView
import com.insightapp.nocontrole.R
import com.insightapp.nocontrole.model.entity.Categoria
import kotlinx.android.synthetic.main.dialog_new_categ.view.*

class SpinnerAdapter(
    private val context: Context,
    private val items: List<Categoria>
) : BaseAdapter() {

    private class ViewHolder(row: View?) {
        var desc: TextView? = null
        init {
            this.desc = row?.findViewById(R.id.desc_categoria)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.item_categoria, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var categoria = items[position]
        viewHolder.desc?.text = categoria.desc

        return view as View
    }

    override fun getItem(position: Int): Categoria {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return items[position].id.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

}