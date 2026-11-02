package com.example.surmarket.models

// Definimos el modelo de datos para el producto
data class Product(
    val id: Int,
    val name: String,
    val category: String,
    val price: Double,
    val imageUrl: String,
    val description: String,
    val available: Boolean,
    val stock: Int
)