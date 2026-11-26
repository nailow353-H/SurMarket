package com.example.surmarket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentCart : Fragment() {

    private lateinit var rcvCartItems: RecyclerView
    private lateinit var btnCheckout: Button
    private lateinit var adapterCart: AdapterCart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rcvCartItems = view.findViewById(R.id.rcv_frg_cart_items)
        btnCheckout = view.findViewById(R.id.btn_frg_cart_checkout)

        val sampleItems = mutableListOf(
            CartItem("Té Verde Mate", 4.99, 1),
            CartItem("Fideo", 2.50, 2),
            CartItem("Dulce de Leche", 3.75, 1)
        )

        adapterCart = AdapterCart(sampleItems)
        rcvCartItems.layoutManager = LinearLayoutManager(requireContext())
        rcvCartItems.adapter = adapterCart

        btnCheckout.setOnClickListener {
            Toast.makeText(requireContext(), "GRACIAS POR SU COMPRA", Toast.LENGTH_SHORT).show()
        }
    }
}