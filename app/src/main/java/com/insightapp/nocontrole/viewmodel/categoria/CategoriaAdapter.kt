package com.insightapp.nocontrole.viewmodel.categoria

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.insightapp.nocontrole.R
import com.insightapp.nocontrole.model.entity.Categoria
import kotlinx.android.synthetic.main.item_categoria_crud.view.*

class CategoriaAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<CategoriaAdapter.CategoriasViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var categorias = emptyList<Categoria>() // Cached copy of categorias

    inner class CategoriasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text_desc_categoria  = itemView.text_desc_categoria

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriasViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_categoria_crud, parent, false)
        return CategoriasViewHolder(itemView)
    }

    override fun getItemCount() = categorias.size

    override fun onBindViewHolder(holder: CategoriasViewHolder, position: Int) {
        val current = categorias[position]
        holder.text_desc_categoria.text = current.desc
    }

    internal fun setWords(cat: List<Categoria>) {
        this.categorias = cat
        notifyDataSetChanged()
    }

}