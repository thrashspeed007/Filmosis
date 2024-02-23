package com.example.filmosis.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.filmosis.AuthActivity
import com.example.filmosis.R
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType {
    BASIC,
    GOOGLE
}

class UserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup
        val prefs = requireActivity().getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)
        setup(view, email ?: "", provider ?: "")
    }

    private fun setup(view: View, email: String, provider: String) {
        val emailTextView: TextView = view.findViewById(R.id.emailTextView)
        val providerTextView: TextView = view.findViewById(R.id.providerTextView)
        val logOutButton: Button = view.findViewById(R.id.logOutButton)

        emailTextView.text = email
        providerTextView.text = provider

        logOutButton.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Cerrar sesión")
                .setMessage("¿Estás seguro de que deseas cerrar sesión?")
                .setPositiveButton("Sí") { _, _ ->
                    // Borrado de datos
                    val prefs =
                        requireActivity().getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
                            .edit()
                    prefs.clear()
                    prefs.apply()

                    FirebaseAuth.getInstance().signOut()

                    showAuthActivity()
                }
                .setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.dismiss() // Cierra el diálogo sin hacer nada
                }
                .show()
        }
    }

    private fun showAuthActivity() {
        val authIntent = Intent(activity, AuthActivity::class.java)

        startActivity(authIntent)
    }
}