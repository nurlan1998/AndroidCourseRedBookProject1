package com.example.redbook.ui.animal

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.redbook.R
import com.example.redbook.data.RedBookDatabase
import com.example.redbook.data.dao.AnimalDao
import com.example.redbook.data.model.Animal
import com.example.redbook.ui.MainActivity
import com.example.redbook.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_animal.*

class AnimalFragment : Fragment(R.layout.fragment_animal) {

    private val adapter = AnimalListAdapter()
    private lateinit var dao: AnimalDao
    private lateinit var presenter: AnimalPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.setOnItemClickListener {
            var mIntent = Intent(requireActivity(), DetailActivity::class.java)
            mIntent.putExtra(DetailActivity.ANIMAL_ID, it)
            startActivity(mIntent)
        }
        recyclerView.adapter = adapter
        val type = requireArguments().getInt(MainActivity.TYPE_ID)
        dao = RedBookDatabase.getInstance(requireContext()).data()
        presenter = AnimalPresenter(dao)
        presenter.setFunctionBody {
            adapter.models = it
        }
        presenter.getAllAnimals(type)
        etSearch.addTextChangedListener {
            val result: List<Animal> = dao.searchAnimalByName(type, "${it.toString()}%")
            adapter.models = result
        }
    }
}