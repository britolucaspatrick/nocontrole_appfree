package com.insightapp.nocontrole.viewmodel.categoria

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.insightapp.nocontrole.R
import com.insightapp.nocontrole.model.entity.Categoria
import kotlinx.android.synthetic.main.item_categoria_crud.view.*

class CategoriaAdapter(
    private val categorias: List<Categoria>
) : RecyclerView.Adapter<CategoriaAdapter.CategoriasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriasViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_categoria_crud, parent, false)
        return CategoriasViewHolder(
            itemView
        )
    }

    override fun getItemCount() = categorias.count()

    override fun onBindViewHolder(holder: CategoriasViewHolder, position: Int) {
        holder.bindView(categorias[position])
    }

    class CategoriasViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val text_desc_categoria  = itemView.text_desc_categoria

        fun bindView(categoria: Categoria){
            text_desc_categoria.text = categoria.desc
        }
    }
}