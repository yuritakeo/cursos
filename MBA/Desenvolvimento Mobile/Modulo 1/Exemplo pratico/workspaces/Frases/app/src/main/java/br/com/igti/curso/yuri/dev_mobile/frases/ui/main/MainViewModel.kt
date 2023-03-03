package br.com.igti.curso.yuri.dev_mobile.frases.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.igti.curso.yuri.dev_mobile.frases.data.Frase
import br.com.igti.curso.yuri.dev_mobile.frases.data.repository.MemoryRepository

class MainViewModel: ViewModel() {
    private var memoryRepository: MemoryRepository = MemoryRepository(mutableListOf())
    private val _listaDeFrases = MutableLiveData<List<Frase>>()

    val listaDeFrases: LiveData<List<Frase>> = _listaDeFrases

    fun iniciarDados(){
        _listaDeFrases.value = memoryRepository.retornarLista()
    }

    fun salvarFrase(frase: Frase){
        memoryRepository.salvar(frase)
        atualizarListaDeFrase()
    }

    fun limparListaDeFrase(){
        memoryRepository.limparLista()
        atualizarListaDeFrase()
    }

    fun atualizarListaDeFrase(){
        _listaDeFrases.value = memoryRepository.retornarLista()
    }
}