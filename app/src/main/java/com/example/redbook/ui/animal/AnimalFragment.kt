package com.example.redbook.ui.animal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.redbook.R
import com.example.redbook.data.RedBookDatabase
import com.example.redbook.data.dao.AnimalDao
import com.example.redbook.ui.MainActivity.Companion.TYPE_ID
import com.example.redbook.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_animal.*

class AnimalFragment : Fragment(R.layout.fragment_animal), AnimalItemClickListener {

    private val adapter = AnimalListAdapter(this)
    private lateinit var dao: AnimalDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter
        val type = arguments?.getInt(TYPE_ID) ?: 1
        dao = RedBookDatabase.getInstance(requireContext()).data()
        setData(type)
    }

    fun setData(type: Int) {
        adapter.models = dao.getAnimals(type)
    }

    override fun onAnimalItemClick(id: Int) {
        var mIntent = Intent(requireActivity(), DetailActivity::class.java)
        mIntent.putExtra(DetailActivity.ANIMAL_ID, id)
        startActivity(mIntent)
    }
}