package com.example.proyecto_nuevo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class EditarActivity extends AppCompatActivity {

    EditText medEnunciado;
    EditText medFechaEntrega;
    EditText medDocente;
    EditText medMateria;
    Button mbtnActualizar;

    private String tareaId;
    private FirebaseFirestore miFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        medEnunciado=findViewById(R.id.edEnunciado);
        medDocente=findViewById(R.id.edDocente);
        medMateria=findViewById(R.id.edMateria);
        medFechaEntrega=findViewById(R.id.edFecEn);
        mbtnActualizar=findViewById(R.id.btnActualizar);

        miFirestore = FirebaseFirestore.getInstance();
        tareaId=getIntent().getStringExtra("tareaId");


        obtenerDatos();
        mbtnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarDatos();

            }
        });

    }


    private void obtenerDatos(){

        miFirestore.collection("Tareas-Pendientes").document(tareaId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
           if (documentSnapshot.exists()){
               String Enunciado= documentSnapshot.getString("Enunciado");
            String Docente= documentSnapshot.getString("Docente");
            String Materia= documentSnapshot.getString("Materia");
               String Fecha_Entrega= documentSnapshot.getString("Fecha_Entrega");

            medEnunciado.setText(Enunciado);
            medDocente.setText(Docente);
            medMateria.setText(Materia);
            medFechaEntrega.setText(Fecha_Entrega);
           }
            }
        });
    }

    private void actualizarDatos(){
        String Enunciado=medEnunciado.getText().toString();
        String Docente=medDocente.getText().toString();
        String Materia=medMateria.getText().toString();
        String Fecha_Entrega=medFechaEntrega.getText().toString();

        Map<String,Object> map= new HashMap<>();
        map.put("Enunciado", Enunciado);
        map.put("Docente", Docente);
        map.put("Materia", Materia);
        map.put("Fecha_Entrega", Fecha_Entrega);
       miFirestore.collection("Tareas-Pendientes").document(tareaId).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void unused) {
               Toast toast1 =
                       Toast.makeText(getApplicationContext(),
                               "Los datos se han actualizado correctamente", Toast.LENGTH_SHORT);

               toast1.show();
           }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull @NotNull Exception e) {
               Toast toast1 =
                       Toast.makeText(getApplicationContext(),
                               "Los datos no se han actualizado correctamente", Toast.LENGTH_SHORT);

               toast1.show();
           }
       });

    }
}