package com.example.redbook.ui.animal.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.redbook.R
import com.example.redbook.data.RedBookDatabase
import com.example.redbook.data.dao.AnimalDao
import com.example.redbook.ui.animal.AnimalItemClickListener
import com.example.redbook.ui.animal.AnimalListAdapter
import com.example.redbook.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.favorite_fragment.*

class FavoriteFragment : Fragment(R.layout.favorite_fragment),
    AnimalItemClickListener {
    private val adapter = AnimalListAdapter(this)
    lateinit var dao:AnimalDao

    override fun onAnimalItemClick(id: Int) {
        var mIntent = Intent(requireActivity(), DetailActivity::class.java)
        mIntent.putExtra(DetailActivity.ANIMAL_ID, id)
        startActivity(mIntent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rvFavorite.adapter = adapter
        dao = RedBookDatabase.getInstance(requireContext()).data()
        setFavorite()
    }

    override fun onStart() {
        setFavorite()
        super.onStart()
    }

    fun setFavorite(){
        adapter.models = dao.getFavorite()
    }
}