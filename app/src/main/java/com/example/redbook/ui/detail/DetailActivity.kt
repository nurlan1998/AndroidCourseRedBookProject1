package com.example.redbook.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.redbook.R
import com.example.redbook.data.RedBookDatabase
import com.example.redbook.data.dao.AnimalDao
import com.example.redbook.data.model.Animal
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.animal_item.view.*

class DetailActivity : AppCompatActivity(), DetailView {
    companion object {
        const val ANIMAL_ID = "animalId"
    }

    private var animalId: Int = 0
    private lateinit var dao: AnimalDao
    private lateinit var currentAnimal: Animal
    private var menuItem: MenuItem? = null
    private lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = "Detail"

        dao = RedBookDatabase.getInstance(this).data()
        presenter = DetailPresenter(dao,this)
        animalId = intent.getIntExtra(ANIMAL_ID, 0)
        presenter.getAnimalById(animalId)

        Glide
            .with(this)
            .load(resources.getIdentifier("picture${animalId}", "drawable", packageName))
            .into(ivDetail)
    }

    override fun setDetailInfo(animal: Animal) {
        currentAnimal = animal
        tvStatusContent.text = animal.status
        tvHabitatContent.text = animal.habitat
        tvPropagationContent.text = animal.propagation
        tvQuantityContent.text = animal.quantity
        tvLifeStyleContent.text = animal.lifestyle
        tvLimitingFactorsContent.text = animal.limitingFactors
        tvBreedingContent.text = animal.breeding
        tvSecurityContent.text = animal.security
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        menuItem = menu?.findItem(R.id.item_bookmark)
        setFavoriteIcon()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.item_bookmark -> setFavorite()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavorite() {
        if (currentAnimal.isFavorite == null) currentAnimal.isFavorite = 1
        else currentAnimal.isFavorite = 1 - currentAnimal.isFavorite!!
        setFavoriteIcon()
        presenter.updateAnimal(currentAnimal)
    }

    private fun setFavoriteIcon() {
        if (currentAnimal.isFavorite == 1) {
            menuItem?.setIcon(R.drawable.ic_bookmark_black_24dp)
        } else {
            menuItem?.setIcon(R.drawable.ic_bookmark_border_black_24dp)
        }
    }
}
