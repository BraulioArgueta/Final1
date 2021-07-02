package com.example.proyecto_nuevo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.android.synthetic.main.fragment_tareas_pendientes.*
import kotlinx.android.synthetic.main.fragment_tareas_pendientes.view.*


class TareasPendientes : Fragment() {
    var recyclerViewTareas: RecyclerView? = null
    var mAdapter: TareasAdapter? = null
    private val db= FirebaseFirestore.getInstance()

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        val view = inflater.inflate(R.layout.fragment_tareas_pendientes, container, false)

        view.guardarButton.setOnClickListener {
            db.collection("Tareas-Pendientes").document().set(hashMapOf(
                "Enunciado" to edEnunciado.text.toString(),
                "Docente" to edDocente.text.toString(),
                "Materia" to edMateria.text.toString(),
                "Fecha_Entrega" to edFecEn.text.toString()

            ))
            val toast = Toast.makeText(context, "¡Los datos se han guardado correctamente!", Toast.LENGTH_SHORT).show()
        }


        view.mostrartbutton.setOnClickListener {
            val intent = Intent(activity, MostrarDatosActivity::class.java)
            startActivity(intent)

            }



        return view
    }

    companion object {
        fun newInstance(): TareasPendientes {
            return TareasPendientes()
        }

    }





}