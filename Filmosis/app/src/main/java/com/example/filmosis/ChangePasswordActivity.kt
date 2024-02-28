package com.example.filmosis

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.filmosis.init.FirebaseInitializer
import com.google.firebase.auth.FirebaseAuth


class ChangePasswordActivity : AppCompatActivity() {
    private lateinit var btnChangePass : Button;
    private lateinit var btnCancel : Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        btnChangePass = findViewById(R.id.btnConfirm)
        btnCancel = findViewById(R.id.btnCancel)


        btnCancel.setOnClickListener{
            finish()
        }

        btnChangePass.setOnClickListener {
            val user = FirebaseInitializer.authInstance.currentUser

            val email = user?.email
            var confirmedEmail : String = ""
            if (email != null) {
                if (email.isNotEmpty()) {
                    confirmedEmail = email.toString()
                }
            }

            FirebaseInitializer.authInstance.sendPasswordResetEmail(confirmedEmail).addOnCompleteListener{
                task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@ChangePasswordActivity,"Revisa tu correo electrónico",Toast.LENGTH_SHORT).show()
                    finish()
                }else {
                    Toast.makeText(this@ChangePasswordActivity,"Error ${task.exception?.message}",Toast.LENGTH_LONG).show()
                }
            }
//            val newPassword = "NuevaContraseña123"
//
//            user?.updatePassword(newPassword)
//                ?.addOnCompleteListener { task ->
//                    if (task.isSuccessful) {
//                        Toast.makeText(applicationContext, "Contraseña cambiada correctamente", Toast.LENGTH_SHORT).show()
//                        finish()
//                    } else {
//                        Log.d("Cambio contra",task.exception?.message ?: "")
//                        Toast.makeText(applicationContext, "Error al cambiar la contraseña: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
//                    }
//                }
        }
    }
}