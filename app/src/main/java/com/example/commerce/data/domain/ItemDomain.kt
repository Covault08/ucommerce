package com.example.commerce.data.domain

import com.example.commerce.data.model.ItemModel

data class ItemDomain (
    val costo: Double,
    val descripcion: String ?="",
    val estado: String? = "",
    val id_categoria: Int,
    val id_empresa: Int,
    val iditem: Int,
    val image_path: String? = "",
    val nombcategoria:String? = "",
    val nombre: String? = "",
    val precio_costo: Double
)


fun ItemModel.toListItemModel(): ItemDomain {
    return ItemDomain(
        costo = costo,
        descripcion = descripcion?: "Unknown",
        estado = estado ?: "Unknown",
        id_categoria = id_categoria,
        id_empresa = id_empresa,
        iditem = iditem,
        image_path = image_path,
        nombcategoria= nombcategoria ?: "Unknown",
        nombre = nombre ?: "Unknown",
        precio_costo =  precio_costo
    )
}


fun List<ItemModel>.toDomain(): List<ItemDomain> {
    return this.map { it.toListItemModel() }
}