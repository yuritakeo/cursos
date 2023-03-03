package br.com.igti.curso.yuri.dev_mobile.frases.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import br.com.igti.curso.yuri.dev_mobile.frases.R
import br.com.igti.curso.yuri.dev_mobile.frases.data.Frase

import br.com.igti.curso.yuri.dev_mobile.frases.databinding.ActivityMainBinding
import br.com.igti.curso.yuri.dev_mobile.frases.ui.incluirfrase.IncluirFraseActivity
//TODO: Muitos metodos dentro da classe
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    private val retornoFrase = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ activityResult ->
        if ( activityResult.resultCode == RESULT_OK){
            activityResult.data?.let {
                if (it.hasExtra(RETORNO)){
                    //TODO: METODO COM PARAMETRO SOMENTE STRING DEPRECATED
                    viewModel.salvarFrase(it.getParcelableExtra(RETORNO)!!)
                }
            }
        }else{
            Log.e("TAG-ERRO(YURI)","Nao foi possivel obter os dados da activity")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        iniciarDados()
        configurarListeners()
        configurarObservers()
    }

    private fun configurarObservers() = configurarRecyclerView()

    private fun configurarRecyclerView() = viewModel.listaDeFrases.observe(this){ lista ->
        atualizarLista(lista)
    }

    private fun atualizarLista(lista: List<Frase>?) {
        if (lista.isNullOrEmpty()){
            binding.recyclerViewListaFrases.visibility = View.GONE
            binding.textViewMensagemListaVazia.visibility = View.VISIBLE
        }else{
            binding.textViewMensagemListaVazia.visibility = View.GONE
            binding.recyclerViewListaFrases.visibility = View.VISIBLE
            binding.recyclerViewListaFrases.adapter = FrasesAdapter(listaDeFrases = lista)
        }
    }

    private fun iniciarDados() = viewModel.iniciarDados()

    private fun configurarListeners() = configurarFabListener()

    private fun configurarFabListener() {
        //TODO: FUNÇÃO COM MUITA FUNCIONALIDADES?
        binding.fabAddNovaFrase.setOnClickListener {
            Intent(this, IncluirFraseActivity::class.java).let {
                retornoFrase.launch(it)
            }
        }

        binding.fabRemoverFrases.setOnLongClickListener {
            viewModel.limparListaDeFrase()
            Toast.makeText(
                this, R.string.lista_limpa_sucesso,
                Toast.LENGTH_LONG
            ).show()
            it.isLongClickable
        }
    }

    override fun onStart() = super.onStart()

    override fun onPause() = super.onPause()

    override fun onResume() = super.onResume()

    companion object{
        const val RETORNO = "retorno_da_frases"
    }
}