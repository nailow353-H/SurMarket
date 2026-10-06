package com.example.surmarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class FragmentLogin : Fragment() {

    private lateinit var etxUsername: EditText
    private lateinit var etxPassword: EditText
    private lateinit var txbRecoverPassword: TextView
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inicialiceViews(view)
        configureListeners()
    }

    private fun inicialiceViews(view: View) {
        etxUsername = view.findViewById(R.id.etx_frg_login_username)
        etxPassword = view.findViewById(R.id.etx_frg_login_password)
        txbRecoverPassword = view.findViewById(R.id.txb_frg_login_recover_password)
        btnLogin = view.findViewById(R.id.btn_frg_login_login)
        btnRegister = view.findViewById(R.id.btn_frg_login_register)
    }

    private fun configureListeners() {
        txbRecoverPassword.setOnClickListener {
            //aqui ira codigo de contraseña
        }

        btnLogin.setOnClickListener {
            sigin() // valida al hacer click
        }

        btnRegister.setOnClickListener {
            // aqui tiene q ir registro
        }
    }


    // 1. Función de login
    private fun sigin() {
        // cudia que no haya espacio ni lugar en blanco
        val user = etxUsername.text.toString().trim()
        val password = etxPassword.text.toString().trim()

        // verifica que tenga algo escrito
        if (!verifyIntegrity(user, password)) {
            return
        }

        // Verificamos credenciales
        if (verifyCredentials(user, password)) {
            Toast.makeText(requireContext(), getString(R.string.welcome_login), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), getString(R.string.login_error), Toast.LENGTH_SHORT).show()
        }
    }

    // 2. verificar que los campos no estén vacíos
    private fun verifyIntegrity(user: String, password: String): Boolean {
        var res = true

        if (user.isEmpty()) {
            etxUsername.error = getString(R.string.user_empty)
            res = false
        } else {
            etxUsername.error = null // Limpia el error si ya se escribió algo
        }

        if (password.isEmpty()) {
            etxPassword.error = getString(R.string.password_empty)
            res = false
        } else {
            etxPassword.error = null
        }

        return res
    }
    //base de datos
    private fun verifyCredentials(user: String, password: String): Boolean {

        return user == "admin" && password == "123456"
    }
}