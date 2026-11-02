package com.example.surmarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.fragment.findNavController
import com.example.surmarket.models.Product

class FragmentProductList : Fragment() {

    // Controles de la vista
    private lateinit var imbShoppingCart: ImageView
    private lateinit var imbSearch: ImageView
    private lateinit var etxSearch: EditText
    private lateinit var btnAddProduct: Button
    private lateinit var rcvProductList: RecyclerView

    // 1. Variables para el adaptador y la lista mutable
    private lateinit var productsAdapter: AdapterProducts
    private var listaProductos = mutableListOf<Product>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inicialiceViews(view)

        // 2. Ejecutar la configuración y carga de datos al crear la vista
        configureRecyclerView()
        loadSampleProducts()

        configureListeners()
    }

    private fun inicialiceViews(view: View) {
        imbShoppingCart = view.findViewById(R.id.imb_frg_products_list_shopping_cart)
        imbSearch = view.findViewById(R.id.imb_frg_products_list_search)
        etxSearch = view.findViewById(R.id.etx_frg_products_list_search)
        btnAddProduct = view.findViewById(R.id.btn_frg_products_list_add_product)
        rcvProductList = view.findViewById(R.id.rcv_frg_product_list)
    }

    // 3. Configurar el RecyclerView y el evento del botón Comprar[cite: 6]
    private fun configureRecyclerView() {
        productsAdapter = AdapterProducts(listaProductos) { product ->
            // Muestra el mensaje al hacer clic
            Toast.makeText(
                requireContext(),
                getString(R.string.add_cart) + ": " + product.name,
                Toast.LENGTH_SHORT
            ).show()
        }

        // Asignar LayoutManager y el adaptador al RecyclerView
        rcvProductList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = productsAdapter
        }
    }

    // 4. Crear los datos de prueba y actualizar el adaptador[cite: 6]
    private fun loadSampleProducts() {
        val productSample = listOf(
            Product(1, "Camisa", "Ropa de varón", 180.0, "", "Camisa de varón Tommy roja", true, 1),
            Product(2, "Pantalón", "Ropa de varón", 210.0, "", "Pantalón de jean azul", true, 1),
            Product(3, "Polera", "Ropa de varón", 160.0, "", "Polera Polo azul", true, 1),
            Product(4, "Zapatillas", "Ropa de varón", 530.0, "", "Zapatillas Nike blanca", true, 1),
            Product(5, "Zapatos", "Ropa de varón", 330.0, "", "Zapatos negros", true, 1)
        )

        listaProductos.clear()
        listaProductos.addAll(productSample)

        // Notifica al adaptador que hay nuevos datos
        productsAdapter.notifyChange()
    }

    private fun configureListeners() {
        // Evento clic del botón "Añadir producto"
        btnAddProduct.setOnClickListener {
            // Muestra el mensaje
            Toast.makeText(requireContext(), "Añadir nuevo producto", Toast.LENGTH_SHORT).show()
            
            // Navega a la pantalla del formulario
            findNavController().navigate(R.id.action_products_list_to_add_product)
        }
    }
}