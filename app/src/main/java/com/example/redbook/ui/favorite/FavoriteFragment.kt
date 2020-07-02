package com.example.redbook.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.redbook.R
import com.example.redbook.data.RedBookDatabase
import com.example.redbook.data.dao.AnimalDao
import com.example.redbook.data.model.Animal
import com.example.redbook.ui.animal.AnimalListAdapter
import com.example.redbook.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.favorite_fragment.*

class FavoriteFragment : Fragment(R.layout.favorite_fragment), FavoriteView {
    private val adapter = AnimalListAdapter()
    lateinit var dao: AnimalDao
    lateinit var favoritePresenter: FavoritePresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rvFavorite.adapter = adapter
        adapter.setOnItemClickListener {
            var mIntent = Intent(requireActivity(), DetailActivity::class.java)
            mIntent.putExtra(DetailActivity.ANIMAL_ID, it)
            startActivity(mIntent)
        }
        dao = RedBookDatabase.getInstance(requireContext()).data()
        favoritePresenter = FavoritePresenter(dao, this)
        favoritePresenter.getAllFavorite()
    }

    override fun onStart() {
        favoritePresenter.getAllFavorite()
        super.onStart()
    }

    override fun setFavorite(models: List<Animal>) {
        adapter.models = models
    }
}