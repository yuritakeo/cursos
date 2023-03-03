package br.com.igti.curso.yuri.dev_mobile.frases.ui.incluirfrase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.igti.curso.yuri.dev_mobile.frases.R
import br.com.igti.curso.yuri.dev_mobile.frases.data.Frase
import br.com.igti.curso.yuri.dev_mobile.frases.databinding.ActivityIncluirFraseBinding
import br.com.igti.curso.yuri.dev_mobile.frases.ui.main.MainActivity

class IncluirFraseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIncluirFraseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIncluirFraseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        configurarListeners()
    }

    private fun configurarListeners() {
        configurarListenerBtnCancelar()
        configurarListenerBtnSalvar()
    }

    private fun configurarListenerBtnSalvar() = binding.btnSalvar.setOnClickListener {
        salvarFrase()
    }

    private fun salvarFrase() {
        binding.apply {
            val autor = autorFraseED.text.toString()
            val frase = frasesET.text.toString()

            autoresFraseTIL.error = if (autor.isEmpty()) {
                getString(R.string.err_sem_autor)
            } else {
                null
            }

            fraseTIL.error = if (frase.isEmpty()) {
                getString(R.string.err_sem_frase)
            } else {
                null
            }

            if (autor.isNotEmpty() && frase.isNotEmpty()) {
                Intent().apply {
                    putExtra(
                        MainActivity.RETORNO, Frase(
                            autor = autor,
                            frase = frase
                        )
                    )
                    setResult(RESULT_OK, this)
                }
                Toast.makeText(applicationContext, "SALVO COM SUCESSO { autor: $autor frase: $frase  }", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(
                    applicationContext,
                    "PREENCHA TODOS OS CAMPOS OBRIGATÓRIO!!!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun configurarListenerBtnCancelar() = binding.btnCancelar.setOnClickListener {
        finish()
    }
}