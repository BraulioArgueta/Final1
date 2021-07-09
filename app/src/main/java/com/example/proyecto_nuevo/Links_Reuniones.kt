package com.example.proyecto_nuevo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_links__reuniones.*
import kotlinx.android.synthetic.main.fragment_links__reuniones.view.*
import kotlinx.android.synthetic.main.fragment_tareas_pendientes.*
import kotlinx.android.synthetic.main.fragment_tareas_pendientes.view.*

class Links_Reuniones : Fragment() {
    var recyclerViewTareas1: RecyclerView? = null
    var mAdapter: LinksAdapter? = null
    private val db= FirebaseFirestore.getInstance()

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        val view = inflater.inflate(R.layout.fragment_links__reuniones, container, false)

        view.guardarButton1.setOnClickListener {
            db.collection("Links-Reuniones").document().set(hashMapOf(
                "Materia1" to edMateria1.text.toString(),
                "Docente1" to edDocente1.text.toString(),
                "Horario1" to edHorario1.text.toString(),
                "Link1" to edLink1.text.toString()

            ))
            val toast = Toast.makeText(context, "¡Los datos se han guardado correctamente!", Toast.LENGTH_SHORT).show()
        }


        view.mostrartbutton1.setOnClickListener {
            val intent1 = Intent(activity, MostrarDatosActivity1::class.java)
            startActivity(intent1)

        }



        return view
    }

    companion object {
        fun newInstance(): Links_Reuniones {
            return Links_Reuniones()
        }

    }





}