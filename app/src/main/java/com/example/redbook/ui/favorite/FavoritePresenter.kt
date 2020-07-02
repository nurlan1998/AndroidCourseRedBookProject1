package com.example.redbook.ui.favorite

import com.example.redbook.data.dao.AnimalDao

class FavoritePresenter(private val dao: AnimalDao, private val view: FavoriteView) {
    fun getAllFavorite() {
            view.setFavorite(dao.getFavorite())
    }
}
//class FavoritePresenter(private val dao: AnimalDao) {
//
//    private var setDataFavorite : (models: List<Animal>) -> Unit = {
//        println("setFavorite ele realizatsiya qilinbadi")
//    }
//
//    fun setFunctionFavoriteBody(click: (a: List<Animal>) -> Unit){
//        this.setDataFavorite = click
//    }
//
//    fun getAllFavorite() {
//        setDataFavorite.invoke(dao.getFavorite())
//    }
//}