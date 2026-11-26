package com.example.surmarket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

data class CartItem(
    val name: String,
    val price: Double,
    var quantity: Int
)

class AdapterCart(
    private val cartItems: MutableList<CartItem>
) : RecyclerView.Adapter<AdapterCart.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProduct: ImageView = itemView.findViewById(R.id.img_item_cart_product)
        val txbName: TextView = itemView.findViewById(R.id.txb_item_cart_name)
        val txbPrice: TextView = itemView.findViewById(R.id.txb_item_cart_price)
        val txbSubtotal: TextView = itemView.findViewById(R.id.txb_item_cart_subtotal)
        val txbQuantity: TextView = itemView.findViewById(R.id.txb_item_cart_quantity)
        val btnMinus: Button = itemView.findViewById(R.id.btn_item_cart_minus)
        val btnPlus: Button = itemView.findViewById(R.id.btn_item_cart_plus)
        val imgDelete: ImageView = itemView.findViewById(R.id.img_item_cart_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartItems[position]

        holder.txbName.text = item.name
        holder.txbPrice.text = String.format(Locale.US, "Precio: %.2f Bs.", item.price)
        holder.txbQuantity.text = item.quantity.toString()
        holder.txbSubtotal.text = String.format(Locale.US, "%.2f Bs.", item.price * item.quantity)

        holder.btnPlus.setOnClickListener {
            Toast.makeText(holder.itemView.context, "MAS", Toast.LENGTH_SHORT).show()
        }

        holder.btnMinus.setOnClickListener {
            Toast.makeText(holder.itemView.context, "MENOS", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = cartItems.size
}