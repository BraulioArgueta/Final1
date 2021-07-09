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

public class Editar1 extends AppCompatActivity {

    EditText medMateria1;
    EditText medDocente1;
    EditText medHorario1;
    EditText medLink1;
    Button mbtnActualizar1;

    private String tareaId1;
    private FirebaseFirestore miFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar1);

        medMateria1=findViewById(R.id.edMateria1);
        medDocente1=findViewById(R.id.edDocente1);
        medHorario1=findViewById(R.id.edHorario1);
        medLink1=findViewById(R.id.edLink1);
        mbtnActualizar1=findViewById(R.id.btnActualizar1);

        miFirestore = FirebaseFirestore.getInstance();
        tareaId1=getIntent().getStringExtra("tareaId");


        obtenerDatos();
        mbtnActualizar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarDatos();

            }
        });

    }


    private void obtenerDatos(){

        miFirestore.collection("Links-Reuniones").document(tareaId1).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    String Materia1= documentSnapshot.getString("Materia1");
                    String Docente1= documentSnapshot.getString("Docente1");
                    String Horario1= documentSnapshot.getString("Horario1");
                    String Link1= documentSnapshot.getString("Link1");

                    medMateria1.setText(Materia1);
                    medDocente1.setText(Docente1);
                    medHorario1.setText(Horario1);
                    medLink1.setText(Link1);
                }
            }
        });
    }

    private void actualizarDatos(){
        String Materia1=medMateria1.getText().toString();
        String Docente1=medDocente1.getText().toString();
        String Horario1=medHorario1.getText().toString();
        String Link1=medLink1.getText().toString();

        Map<String,Object> map= new HashMap<>();
        map.put("Materia1", Materia1);
        map.put("Docente1", Docente1);
        map.put("Horario1", Horario1);
        map.put("Link1", Link1);
        miFirestore.collection("Links-Reuniones").document(tareaId1).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
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