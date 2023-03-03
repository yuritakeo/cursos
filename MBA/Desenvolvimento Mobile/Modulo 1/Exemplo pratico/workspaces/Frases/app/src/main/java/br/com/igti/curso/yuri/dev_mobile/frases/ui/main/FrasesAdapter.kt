package br.com.igti.curso.yuri.dev_mobile.frases.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.igti.curso.yuri.dev_mobile.frases.data.Frase
import br.com.igti.curso.yuri.dev_mobile.frases.databinding.ListItemFrasesBinding

class FrasesAdapter(private val listaDeFrases: List<Frase>): RecyclerView.Adapter<FrasesAdapter.ViewHolder>(){
    private lateinit var binding: ListItemFrasesBinding //Binding = Vinculativo

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(item: Frase){ //bind = vincular
            binding.apply {
                textViewAutor.text = item.autor
                textViewFrase.text = item.frase
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ListItemFrasesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(item = listaDeFrases[position])

    override fun getItemCount(): Int = listaDeFrases.size
}