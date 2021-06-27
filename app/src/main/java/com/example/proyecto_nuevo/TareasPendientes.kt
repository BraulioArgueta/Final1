package com.example.proyecto_nuevo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.android.synthetic.main.fragment_tareas_pendientes.*
import kotlinx.android.synthetic.main.fragment_tareas_pendientes.view.*


class TareasPendientes : Fragment() {
    private val db= FirebaseFirestore.getInstance()

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        val view = inflater.inflate(R.layout.fragment_tareas_pendientes, container, false)

        view.savebutton.setOnClickListener {
            db.collection("Tareas-Pendientes").document().set(hashMapOf(
                "Docente" to addressTextView.text.toString(),
                "Materia" to phonetextView.text.toString()

            ))
            val toast = Toast.makeText(context, "¡Los datos se han guardado correctamente!", Toast.LENGTH_SHORT).show()
        }
        view.getbutton.setOnClickListener {


            obtenerDatos()
            }

        view.deletebutton.setOnClickListener {


            db.collection("Usuarios").document("").delete()
        }

        return view
    }

    companion object {
        fun newInstance(): TareasPendientes {
            return TareasPendientes()
        }

    }

    private fun obtenerDatos(){

        db.collection("Tareas-Pendientes").document("eaCeBs2U6e61wbdOuPEQ").addSnapshotListener(
            object : EventListener<DocumentSnapshot?> {
                override fun onEvent(
                    @Nullable documentSnapshot: DocumentSnapshot?,
                    @Nullable e: FirebaseFirestoreException?
                ) {
                    if (documentSnapshot != null) {
                        if (documentSnapshot.exists()){

                            if (documentSnapshot.contains("Docente")){
                                text1.setText(documentSnapshot.get("Docente")as String?)
                            }else{
                                text1.setText("Default")
                            }
                            if (documentSnapshot.contains("Materia")) {
                                text2.setText(documentSnapshot.get("Materia") as String?)
                            }else{
                                text2.setText("Default")
                            }
                            }
                    }
                }
            } )

       // db.collection("Tareas-Pendientes").document("eaCeBs2U6e61wbdOuPEQ").get().addOnSuccessListener{
         //   text1.setText(it.get("Docente")as String?)
           // text2.setText(it.get("Materia")as String?)
      //  }

    }


}