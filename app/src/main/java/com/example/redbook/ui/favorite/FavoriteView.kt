package com.example.redbook.ui.favorite

import com.example.redbook.data.model.Animal

interface FavoriteView {
    fun setFavorite(models: List<Animal>)
}