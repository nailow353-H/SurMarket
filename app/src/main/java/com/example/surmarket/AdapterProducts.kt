package com.example.surmarket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.surmarket.models.Product

class AdapterProducts(
    private val productsList: List<Product>,
    private val clickOnBuy: (Product) -> Unit
) : RecyclerView.Adapter<AdapterProducts.ViewHolderProduct>() {

    // 1. Enlazamos los controles del layout item_product_list [00:03:04]
    class ViewHolderProduct(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val txvCategory: TextView = itemview.findViewById(R.id.txb_it_product_list_category)
        val txvName: TextView = itemview.findViewById(R.id.txb_it_product_list_product_name)
        val txvPrice: TextView = itemview.findViewById(R.id.txb_it_product_list_product_price)
        val btnBuy: Button = itemview.findViewById(R.id.btn_it_product_list_buy)
        val imgProduct: ImageView = itemview.findViewById(R.id.imb_it_product_list_product_imagen)
    }

    // 2. Inflamos la vista para cada ítem [00:08:12]
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderProduct {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_product_list, parent, false)
        return ViewHolderProduct(vista)
    }

    // 3. Devolvemos la cantidad de elementos en la lista [00:10:41]
    override fun getItemCount(): Int {
        return productsList.size
    }

    // 4. Asignamos los datos a los controles visuales
    override fun onBindViewHolder(holder: ViewHolderProduct, position: Int) {
        val product = productsList[position]

        // Asignamos el nombre
        holder.txvName.text = product.name

        // Obtenemos los textos base desde strings.xml usando el context
        val context = holder.itemView.context

        // Concatenamos y asignamos Categoría y Precio usando resources formateados
        holder.txvCategory.text = context.getString(R.string.category_format, product.category)
        holder.txvPrice.text = context.getString(R.string.price_format, product.price.toString())

        // Asignamos tu imagen por defecto
        holder.imgProduct.setImageResource(R.drawable.ic_surmarket)

        // Evento de clic en el botón comprar
        holder.btnBuy.setOnClickListener {
            clickOnBuy(product)
        }
    }
    // Agrega esto al final de tu AdapterProducts.kt, antes de la última llave
    @android.annotation.SuppressLint("NotifyDataSetChanged")
    fun notifyChange() {
        notifyDataSetChanged()
    }
}