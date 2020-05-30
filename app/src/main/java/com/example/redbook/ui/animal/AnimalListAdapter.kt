package com.example.redbook.ui.animal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.redbook.R
import com.example.redbook.data.model.Animal
import kotlinx.android.synthetic.main.animal_item.view.*

class AnimalListAdapter : RecyclerView.Adapter<AnimalListAdapter.AnimalListViewHolder>() {

    var models:List<Animal> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalListViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.animal_item,parent,false)
        return AnimalListViewHolder(view)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(holder: AnimalListViewHolder, position: Int) {
        holder.populate(models[position])
    }

    inner class AnimalListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun populate(animal: Animal){
            itemView.tvUzbName.text = animal.nameUzb
            itemView.tvRusName.text = animal.nameRus
            itemView.tvEngName.text = animal.nameEng
        }
    }
}