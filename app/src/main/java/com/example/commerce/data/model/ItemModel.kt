package com.example.commerce.data.model

import com.google.gson.annotations.SerializedName

data class ItemModel(
    @SerializedName("costo")
    val costo: Double,
    @SerializedName("descripcion")
    val descripcion: String? = "",
    @SerializedName("estado")
    val estado: String? = "",
    @SerializedName("id_categoria")
    val id_categoria: Int,
    @SerializedName("id_empresa")
    val id_empresa: Int,
    @SerializedName("iditem")
    val iditem: Int,
    @SerializedName("image_path")
    val image_path: String? = "",
    @SerializedName("nombcategoria")
    val nombcategoria: String? = "",
    @SerializedName("nombre")
    val nombre: String? = "",
    @SerializedName("precio_costo")
    val precio_costo: Double
)
