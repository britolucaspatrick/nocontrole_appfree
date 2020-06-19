package com.insightapp.nocontrole.viewmodel.ui.categoria
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.insightapp.nocontrole.R
import com.insightapp.nocontrole.model.entity.Categoria
import kotlinx.android.synthetic.main.item_categoria_crud.view.*

class CategoriaAdapter internal constructor(
    context: Context,
    itemSelectedListener:ItemSelectedListener
) : RecyclerView.Adapter<CategoriaAdapter.CategoriasViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var categorias = emptyList<Categoria>()
    private var itemSelectedListener: ItemSelectedListener
    private val context:Context

    init {
        this.context = context
        this.itemSelectedListener = itemSelectedListener
    }

    interface ItemSelectedListener {
        fun onItemSelectedDel(item: Categoria)
        fun onItemSelectedAlt(item: Categoria)
    }

    inner class CategoriasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text_desc_categoria  = itemView.text_desc_lancto
        val text_rec_or_des = itemView.text_rec_or_des
        var btn_edit_cat = itemView.btn_edit_lancto
        val btn_del_cat = itemView.btn_del_lancto

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriasViewHolder {
        val itemView = inflater.inflate(R.layout.item_categoria_crud, parent, false)
        return CategoriasViewHolder(itemView)
    }

    override fun getItemCount() = categorias.size

    override fun onBindViewHolder(holder: CategoriasViewHolder, position: Int) {
        val current = categorias[position]
        holder.text_desc_categoria.text = current.desc

        if (current.tp_lancto == 0){
            holder.text_rec_or_des.text = "Receita"
        }else{
            holder.text_rec_or_des.text = "Despesa"
        }

        holder.btn_del_cat.setOnClickListener {
            itemSelectedListener.onItemSelectedDel(current)
        }

        holder.btn_edit_cat.setOnClickListener {
            itemSelectedListener.onItemSelectedAlt(current)
        }
    }

    internal fun setCategorias(cat: List<Categoria>) {
        this.categorias = cat
        notifyDataSetChanged()
    }

}